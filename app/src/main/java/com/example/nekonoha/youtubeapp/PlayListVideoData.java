package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.PrimaryKey;
import ollie.annotation.Table;
import ollie.query.Select;

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

    public AsVideo asVideo(){
        return new AsVideo(this.title, this.videoId, this.thumbnail, "description...");
    }

    static class AsVideo implements Video, Serializable{
        String title, id, thumbnail, description;

        public AsVideo(String title, String id, String thumbnail, String description) {
            this.title = title;
            this.id = id;
            this.thumbnail = thumbnail;
            this.description = description;
        }

        @Override
        public String title() {
            return this.title;
        }

        @Override
        public String id() {
            return this.id;
        }

        @Override
        public String thumbnail() {
            return this.thumbnail;
        }

        @Override
        public String description() {
            return this.description;
        }
    }
}
