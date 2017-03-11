package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.PrimaryKey;
import ollie.annotation.Table;

/**
 * Created by c0115114 on 2017/03/10.
 */

@Table("video_data")
public class PlayListVideoData extends PlayList {
    @Column("video_id")
    public String videoId;

    @Override
    public void create(LinearLayout linearLayout, Activity activity) {
        TextView textView = new TextView(activity);
        textView.setText(this.videoId);
        textView.setTextSize(30);
        linearLayout.addView(textView);
    }
}
