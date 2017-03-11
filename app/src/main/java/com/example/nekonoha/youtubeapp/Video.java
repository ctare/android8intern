package com.example.nekonoha.youtubeapp;

import java.io.Serializable;

/**
 * Created by c0115114 on 2017/03/11.
 */

public interface Video extends Serializable{
    String title();
    String id();
    String thumbnail();
    String description();
}
