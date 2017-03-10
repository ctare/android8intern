package com.example.nekonoha.youtubeapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by c0115114 on 2017/03/08.
 */

public class Video implements Serializable{
    String id, title, thumbnail;

    public Video(JSONObject video) {
        this.id = Video.id(video);
        this.title = Video.title(video);
        this.thumbnail = Video.thumbnail(video);
    }

    private static String id(JSONObject video) {
        try {
            return video == null ? "cbP2N1BQdYc" : video.getJSONObject("id").getString("videoId");
        } catch (JSONException e) {
            // TODO: 2017/03/09 最終的に削除
            return "cbP2N1BQdYc";
        }
    }

    public String id(){
        return this.id;
    }

    private static String thumbnail(JSONObject video){
        try {
            if(video == null) return "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
            return video.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");
        } catch (JSONException e) {
            return "https://i.ytimg.com/vi/b2IZDKG0k6M/hqdefault.jpg";
        }
    }

    public String thumbnail(){
        return this.thumbnail;
    }

    private static String title(JSONObject video){
        // TODO: 2017/03/10 タイトルを取得する
        try {
            if(video == null) return "none";
            return video.getJSONObject("snippet").getString("title");
        } catch (JSONException e) {
            return "none";
        }
    }

    public String title(){
        return this.title;
    }
}
