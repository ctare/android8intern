package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by c0115114 on 2017/03/08.
 */

abstract public class PlayList {
    public static PlayList sample = new PlayListFolder(){{
        this.addItem(new PlayListFolder());
        this.addItem(new PlayListFolder(){{
            this.addItem(new PlayListContent(new Video(null)));
            this.addItem(new PlayListContent(new Video(null)));
            this.addItem(new PlayListContent(new Video(null)));
        }});
        this.addItem(new PlayListContent(new Video(null)));
        this.addItem(new PlayListContent(new Video(null)));
    }};

    public void addItem(PlayList playList){
    }

    public void tap(LinearLayout linearLayout, Activity activity){
    }
    abstract public void create(LinearLayout linearLayout, Activity activity);
}
