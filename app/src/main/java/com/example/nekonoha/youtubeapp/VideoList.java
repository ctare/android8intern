package com.example.nekonoha.youtubeapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c0115114 on 2017/03/09.
 */

public class VideoList implements Serializable{
    List<Video> videos;

    public VideoList(List<Video> videos) {
        this.videos = videos;
    }
}
