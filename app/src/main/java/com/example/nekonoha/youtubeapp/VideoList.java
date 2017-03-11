package com.example.nekonoha.youtubeapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by c0115114 on 2017/03/11.
 */

public interface VideoList extends Serializable{
    List<Video> videos();
}
