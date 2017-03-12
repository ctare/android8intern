package com.example.nekonoha.youtubeapp;

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
    @Column("search_limit")
    public Integer searchLimit;

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
