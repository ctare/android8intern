package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.PrimaryKey;
import ollie.annotation.Table;

/**
 * Created by c0115114 on 2017/03/10.
 */

@Table("video_data")
public class PlayListVideoData extends PlayList implements Serializable{
    @Column("video_id")
    public String videoId;

    @Column("title")
    public String title;

    @Column("thumbnail")
    public String thumbnail;

    @Override
    public void create(LinearLayout linearLayout, Activity activity) {
        TextView textView = new TextView(activity);
        textView.setText(asVideo().id());
        textView.setTextSize(30);
        linearLayout.addView(textView);
    }

    public AsVideo asVideo(){
        return new AsVideo();
    }

    private class AsVideo implements Video{
        @Override
        public String title() {
            return PlayListVideoData.this.title;
        }

        @Override
        public String id() {
            return PlayListVideoData.this.videoId;
        }

        @Override
        public String thumbnail() {
            return PlayListVideoData.this.thumbnail;
        }

        @Override
        public String description() {
            return "todo description...";
        }
    }
}
