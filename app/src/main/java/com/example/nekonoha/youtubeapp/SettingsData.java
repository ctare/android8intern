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
    @Column("search_limit")
    public Integer searchLimit;
    public Integer getSearchLimit(){
        return this.searchLimit == null ? SettingsDataStatic.DEFAULT_SEARCH_LIMIT : this.searchLimit;
    }

    @Column("sort_type")
    public String sortType;
    public String getSortType(){
        return this.sortType == null ? SettingsDataStatic.DEFAULT_SORT_TYPE : this.sortType;
    }
}
