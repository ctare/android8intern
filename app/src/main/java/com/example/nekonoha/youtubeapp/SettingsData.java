package com.example.nekonoha.youtubeapp;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;
import ollie.query.Select;

/**
 * Created by c0115114 on 2017/03/12.
 */

@Table("settings_data")
public class SettingsData extends Model{
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
    @Column("search_limit")
    public Integer searchLimit;

    public static String DEFAULT_SORT_TYPE = "date";
    @Column("sort_type")
    public String sortType;

    public static SettingsData getInstance(){
        SettingsData settingsData = Select.from(SettingsData.class).fetchSingle();
        if(settingsData == null){
            settingsData = new SettingsData();
            settingsData.save();
        }
        return settingsData;
    }
}
