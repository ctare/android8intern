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
        Long topKey = playListFolderData.save();

        PlayListFolderData inner = new PlayListFolderData();
        inner.name = "sample inner";
        Long innerKey = inner.save();

        PlayListVideoData video1 = new PlayListVideoData();
        video1.videoId = "sample video id 1";
        video1.parent = topKey;
        video1.save();

        PlayListVideoData video2 = new PlayListVideoData();
        video2.videoId = "sample video id 2";
        video2.parent = topKey;
        video2.save();

        PlayListVideoData video3 = new PlayListVideoData();
        video3.videoId = "sample video id 3";
        video3.parent = innerKey;
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
        return Select.from(PlayListFolderData.class).where(PlayListFolderData._ID + " == ?", this.parent).fetchSingle();
    }

    public void setParent(PlayList parent) {
        this.parent = parent.save();
    }
}
