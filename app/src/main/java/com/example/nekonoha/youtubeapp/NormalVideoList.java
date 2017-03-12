package com.example.nekonoha.youtubeapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c0115114 on 2017/03/09.
 */

public class NormalVideoList implements Serializable, VideoList{
    List<Video> videos;
    public String query = null;
    public String nextToken = null;

    public NormalVideoList(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public List<Video> videos() {
        return videos;
    }

    public void setNextPage(String query, String nextToken){
        this.query = query;
        this.nextToken = nextToken;
    }
}
