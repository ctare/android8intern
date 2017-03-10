package com.example.nekonoha.youtubeapp;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.PrimaryKey;
import ollie.annotation.Table;

/**
 * Created by c0115114 on 2017/03/10.
 */

@Table("folder_data")
public class PlayListFolderData extends Model{
    @PrimaryKey
    public Long id;

    @Column("folder_id")
    public Long folderId;

    @Column("name")
    public String name;
}
