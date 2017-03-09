package com.example.nekonoha.youtubeapp;

import org.json.JSONObject;

/**
 * Created by c0115114 on 2017/03/08.
 */

public class Video {
    JSONObject video;

    public Video(JSONObject video) {
        this.video = video;
    }

    public String id() {
        return "cbP2N1BQdYc";
    }

    public String thumbnail() {
        return "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
    }

}
