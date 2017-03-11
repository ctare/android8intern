package com.example.nekonoha.youtubeapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by c0115114 on 2017/03/08.
 */

public class NormalVideo implements Serializable, Video{
    private String id, title, thumbnail,description;

    public NormalVideo(JSONObject video) {
        this.id = NormalVideo.id(video);
        this.title = NormalVideo.title(video);
        this.thumbnail = NormalVideo.thumbnail(video);
        this.description = NormalVideo.description(video);
    }

    private static String id(JSONObject video) {
        try {
            return video == null ? "cbP2N1BQdYc" : video.getJSONObject("id").getString("videoId");
        } catch (JSONException e) {
            // TODO: 2017/03/09 最終的に削除
            return "cbP2N1BQdYc";
        }
    }

    @Override
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

    @Override
    public String thumbnail(){
        return this.thumbnail;
    }

    private static String title(JSONObject video){
        try {
            if(video == null) return "none";
            return video.getJSONObject("snippet").getString("title");
        } catch (JSONException e) {
            return "none";
        }
    }

    @Override
    public String title(){
        return this.title;
    }

    private static String description(JSONObject video){
        try {
            if(video == null) return "none";
            return video.getJSONObject("snippet").getString("description");
        } catch (JSONException e) {
            return "none";
        }
    }

    @Override
    public String description(){
        return this.description;
    }
}
