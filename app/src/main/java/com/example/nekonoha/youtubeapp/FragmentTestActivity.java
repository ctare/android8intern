package com.example.nekonoha.youtubeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentTestActivity extends AppCompatActivity {
    public static FragmentTestActivity created;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        created = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
    }

}
