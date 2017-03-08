package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by c0115114 on 2017/03/08.
 */

public class PlayListContent extends PlayList{
    public Video video;

    public PlayListContent(Video video) {
        this.video = video;
    }

    @Override
    public void create(LinearLayout linearLayout, Activity activity) {
        TextView textView = new TextView(activity);
        textView.setText("content item");
        textView.setTextSize(30);
        linearLayout.addView(textView);
    }
}
