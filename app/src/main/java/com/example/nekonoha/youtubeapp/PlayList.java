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
