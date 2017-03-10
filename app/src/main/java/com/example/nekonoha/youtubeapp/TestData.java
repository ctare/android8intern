package com.example.nekonoha.youtubeapp;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

/**
 * Created by c0115114 on 2017/03/10.
 */

@Table("test_data")
public class TestData extends Model{
    @Column("name")
    public String name;

    @Column("email")
    public String email;

    @Column("age")
    public Integer age;
}
