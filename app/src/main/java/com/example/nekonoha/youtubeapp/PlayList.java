package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        PlayListFolderData playListFolderData = new PlayListFolderData();
        playListFolderData.name = "sample1";
        playListFolderData.save();

        PlayListFolderData inner = new PlayListFolderData();
        inner.name = "inner";
        inner.save();

        PlayListVideoData video1 = new PlayListVideoData();
        video1.videoId = "video id 1";
        video1.folderId = 1L;

        PlayListVideoData video2 = new PlayListVideoData();
        video2.videoId = "video id 2";
        video2.folderId = 1L;

        PlayListVideoData video3 = new PlayListVideoData();
        video3.videoId = "video id 3";
        video3.folderId = 2L;
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
