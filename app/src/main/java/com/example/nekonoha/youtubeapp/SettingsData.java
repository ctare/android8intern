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
public class SettingsData extends PlayList{
    @Column("search_limit")
    public Integer searchLimit;
    public Integer getSearchLimit(){
        return (this.searchLimit == null || this.searchLimit == 0) ? SettingsDataStatic.DEFAULT_SEARCH_LIMIT : this.searchLimit;
    }

    @Column("sort_type")
    public String sortType;
    public String getSortType(){
        return this.sortType == null ? SettingsDataStatic.DEFAULT_SORT_TYPE : this.sortType;
    }

    @Column("gyro")
    public Integer gyro;
    public Integer getGyro(){
        return this.gyro == null ? SettingsDataStatic.DEFAULT_GYRO : this.gyro;
    }

    @Column("gyro_on")
    public Integer gyroOn;
    public Boolean getGyroOn(){
        return (this.gyroOn == null ? SettingsDataStatic.DEFAULT_GYRO_ON : this.gyroOn) == 1;
    }
}
