package com.example.nekonoha.youtubeapp;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;

import ollie.query.Select;

/**
 * Created by c0115114 on 2017/03/12.
 */

public class SettingsDataStatic {
    public static String[] sortEn = {"date", "rating", "relevance", "title", "viewCount"};
    public static String[] sortJa = {"日付", "評価", "関連度", "タイトル", "再生数"};
    public static HashMap<String, String> toEn = new HashMap<String, String>(){{
        for (int i = 0; i < sortEn.length; i++) {
            put(sortJa[i], sortEn[i]);
        }
    }};
    public static int getItemPosition(String en){
        int position = Arrays.asList(sortEn).indexOf(en);
        return position == -1 ? getItemPosition(DEFAULT_SORT_TYPE) : position;
    }

    public static Integer DEFAULT_SEARCH_LIMIT = 2;
    public static String DEFAULT_SORT_TYPE = "date";
    public static Integer DEFAULT_GYRO = 3;
    public static Integer DEFAULT_GYRO_ON = 1;

    public static SettingsData getInstance(){
        SettingsData settingsData = Select.from(SettingsData.class).fetchSingle();
        if(settingsData == null){
            settingsData = new SettingsData();
            settingsData.save();
        }
        return settingsData;
    }
}
