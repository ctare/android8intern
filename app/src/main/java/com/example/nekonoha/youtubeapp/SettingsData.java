package com.example.nekonoha.youtubeapp;

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
    public static String[] sortEn = {"data", "rating", "relevance", "title", "viewCount"};
    private static String[] sortJa = {"日付", "評価", "関連度", "タイトル", "再生数"};
    public static HashMap<String, String> toJa = new HashMap<String, String>(){{
        for (int i = 0; i < sortEn.length; i++) {
            put(sortEn[i], sortJa[i]);
        }
    }};
    public static String[] toJa(){
        String[] result = new String[sortEn.length];
        for (int i = 0; i < sortEn.length; i++) {
            result[i] = sortJa[i];
        }
        return result;
    }

    public static Integer DEFAULT_SEARCH_LIMIT = 2;
    @Column("search_limit")
    public Integer searchLimit;

    public static String DEFAULT_SORT_TYPE = "";
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
