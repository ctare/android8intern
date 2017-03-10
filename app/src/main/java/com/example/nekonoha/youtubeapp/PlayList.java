package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ollie.annotation.NotNull;
import ollie.query.Delete;
import ollie.query.Select;
import ollie.util.QueryUtils;

/**
 * Created by c0115114 on 2017/03/08.
 */

abstract public class PlayList {
    private PlayList parent;
    public static PlayList sample = new PlayListFolder(){{
        this.add(new PlayListFolder());
        this.add(new PlayListFolder(){{
            this.add(new PlayListContent(new Video(null)));
            this.add(new PlayListContent(new Video(null)));
            this.add(new PlayListContent(new Video(null)));
        }});
        this.add(new PlayListContent(new Video(null)));
        this.add(new PlayListContent(new Video(null)));
    }};

    public static void createSampleData(){
        QueryUtils.execSQL("delete from folder_data where name like 'sample%'");
        QueryUtils.execSQL("delete from video_data where video_id like 'sample%'");
        PlayListFolderData playListFolderData = new PlayListFolderData();
        playListFolderData.name = "sample1";
        Long topKey = playListFolderData.save();

        PlayListFolderData inner = new PlayListFolderData();
        inner.name = "sample inner";
        Long innerKey = inner.save();

        PlayListVideoData video1 = new PlayListVideoData();
        video1.videoId = "sample video id 1";
        video1.folderId = topKey;
        video1.save();

        PlayListVideoData video2 = new PlayListVideoData();
        video2.videoId = "sample video id 2";
        video2.folderId = topKey;
        video2.save();

        PlayListVideoData video3 = new PlayListVideoData();
        video3.videoId = "sample video id 3";
        video3.folderId = innerKey;
        video3.save();
    }

    public final void add(PlayList playList){
        addItem(playList);
        playList.setParent(this);
    }

    public void addItem(PlayList playList){
    }

    public void tap(LinearLayout linearLayout, Activity activity){
    }
    abstract public void create(LinearLayout linearLayout, Activity activity);

    public PlayList getParent() {
        return parent;
    }

    public void setParent(PlayList parent) {
        this.parent = parent;
    }
}
