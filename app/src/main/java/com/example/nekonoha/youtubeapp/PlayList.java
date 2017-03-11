package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.NotNull;
import ollie.annotation.Table;
import ollie.query.Delete;
import ollie.query.Select;
import ollie.util.QueryUtils;

/**
 * Created by c0115114 on 2017/03/08.
 */

@Table("abstract_playlist")
public abstract class PlayList extends Model{
    @Column("parent")
    public Long parent;

    public static void createSampleData(){
        QueryUtils.execSQL("delete from folder_data where name like 'sample%'");
        QueryUtils.execSQL("delete from video_data where video_id like 'sample%'");
        PlayListFolderData playListFolderData = new PlayListFolderData();
        playListFolderData.name = "sample1";
        playListFolderData.save();

        PlayListFolderData inner = new PlayListFolderData();
        inner.name = "sample inner";
        inner.save();

        PlayListFolderData inner2 = new PlayListFolderData();
        inner.name = "sample inner2";
        inner.save();

        PlayListVideoData video1 = new PlayListVideoData();
        video1.videoId = "cbP2N1BQdYc";
        video1.title = "sample video id 3";
        video1.thumbnail = "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
        playListFolderData.add(video1);

        PlayListVideoData video2 = new PlayListVideoData();
        video2.videoId = "cbP2N1BQdYc";
        video2.title = "sample video id 2";
        video2.thumbnail = "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
        playListFolderData.add(video2);

        PlayListVideoData video3 = new PlayListVideoData();
        video3.videoId = "cbP2N1BQdYc";
        video3.title = "sample video id 3";
        video3.thumbnail = "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
        inner.add(video3);

        playListFolderData.add(inner);
        playListFolderData.add(inner2);
    }

    public final void add(PlayList playList){
        addItem(playList);
        playList.setParent(this);
        playList.save();
    }

    public void addItem(PlayList playList){
    }

    public void tap(LinearLayout linearLayout, Activity activity){
    }

    abstract public void create(LinearLayout linearLayout, Activity activity);

    public PlayList getParent() {
        return Select.from(PlayListFolderData.class).where(PlayListFolderData._ID + " == ?", this.parent).fetchSingle();
    }

    public void setParent(PlayList parent) {
        this.parent = parent.save();
    }
}
