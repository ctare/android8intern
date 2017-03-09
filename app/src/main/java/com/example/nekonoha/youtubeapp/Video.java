package com.example.nekonoha.youtubeapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by c0115114 on 2017/03/08.
 */

public class Video implements Serializable{
    JSONObject video;

    public Video(JSONObject video) {
        this.video = video;
    }

    public String id() {
        try {
            return video == null ? "cbP2N1BQdYc" : video.getJSONObject("id").getString("videoId");
        } catch (JSONException e) {
            // TODO: 2017/03/09 最終的に削除
            return "cbP2N1BQdYc";
        }
    }

    public String thumbnail() {
        try {
            if(video == null) return "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
            return video.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");
        } catch (JSONException e) {
            return "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
        }
    }

}
