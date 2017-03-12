package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.PrimaryKey;
import ollie.annotation.Table;
import ollie.query.Select;

/**
 * Created by c0115114 on 2017/03/10.
 */

@Table("folder_data")
public class PlayListFolderData extends PlayList implements Serializable{
    public final static String IDENTIFICATION = "this is play list";
    private static PlayListFolderData selected = null;
    public static PlayListFolderData getSelected(){
        return selected == null ? Select.from(PlayListFolderData.class).where("name == 'play list'").fetchSingle() : selected;
    }

    public static void select(PlayListFolderData playListFolderData){
        selected = playListFolderData;
    }


    @Column("name")
    public String name;
    public PlayListVideoData.AsVideo asVideo(){
        return new PlayListFolderData.AsVideo(this.name, "", IDENTIFICATION, "playlist description");
    }

    public AsVideoList asVideoList(){
        return new AsVideoList();
    }

    public class AsVideoList implements VideoList, Serializable{
        @Override
        public List<Video> videos() {
            List<Video> videos = new ArrayList<>();
            for(PlayListFolderData videoData: Select.from(PlayListFolderData.class).where("parent == ?", PlayListFolderData.this.id).fetch()){
                videos.add(videoData.asVideo());
            }
            for(PlayListVideoData videoData: Select.from(PlayListVideoData.class).where("parent == ?", PlayListFolderData.this.id).fetch()){
                videos.add(videoData.asVideo());
            }
            return videos;
        }

        public PlayListFolderData asData(){
            return PlayListFolderData.this;
        }
    }

    public static boolean isPlayList(Video video){
        return video.thumbnail().equals(IDENTIFICATION);
    }

    public class AsVideo extends PlayListVideoData.AsVideo{
        public AsVideo(String title, String id, String thumbnail, String description) {
            super(title, id, thumbnail, description);
        }

        public PlayListFolderData asData(){
            return PlayListFolderData.this;
        }
    }
}
