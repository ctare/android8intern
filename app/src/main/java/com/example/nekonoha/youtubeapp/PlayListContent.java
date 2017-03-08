package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        linearLayout.addView(textView);
    }
}
