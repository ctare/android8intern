package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;
import ollie.query.Select;
import ollie.util.QueryUtils;

/**
 * Created by c0115114 on 2017/03/08.
 */

@Table("abstract_playlist")
public abstract class PlayList extends Model {
    @Column("parent")
    public Long parent;

    public static void createSampleData() {
        QueryUtils.execSQL("delete from folder_data where name like 'sample%'");
        QueryUtils.execSQL("delete from video_data where video_id like 'sample%'");
        PlayListFolderData playListFolderData = new PlayListFolderData();
        playListFolderData.name = "sample1";
        playListFolderData.save();

        PlayListFolderData inner = new PlayListFolderData();
        inner.name = "sample inner";
        inner.save();

        PlayListFolderData inner2 = new PlayListFolderData();
        inner2.name = "sample inner2";
        inner2.save();

        PlayListVideoData video1 = new PlayListVideoData();
        video1.videoId = "cbP2N1BQdYc";
        video1.title = "sample video id 1";
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

        PlayListVideoData video4 = new PlayListVideoData();
        video4.videoId = "cbP2N1BQdYc";
        video4.title = "sample video id 4";
        video4.thumbnail = "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
        inner.add(video4);

        playListFolderData.add(inner);
        playListFolderData.add(inner2);
    }

    public final void add(PlayList playList) {
        addItem(playList);
        playList.setParent(this);
        playList.save();
    }

    public void addItem(PlayList playList) {
    }

    public PlayList getParent() {
        return Select.from(PlayListFolderData.class).where(PlayListFolderData._ID + " == ?", this.parent).fetchSingle();
    }

    public void setParent(PlayList parent) {
        this.parent = parent.save();
    }

    public static void viewPlayList(FragmentActivity activity, PlayListFolderData playListFolderData, TextView textView) {
        VideoList videos = playListFolderData.asVideoList();
        Bundle args = new Bundle();
        args.putSerializable("videos", videos);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment playListFragment = new PlayListFragment();
        playListFragment.setArguments(args);
        fragmentManager
                .beginTransaction()
                .replace(R.id.play_list_wrap, playListFragment)
                .commit();

        Log.d("textview", playListFolderData.name);
        Log.d("textview", textView == null ? "null" : textView.getText().toString());

        //textView.setText(playListFolderData.name);
    }
}
