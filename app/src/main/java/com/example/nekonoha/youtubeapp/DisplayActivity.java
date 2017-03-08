package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView title = (TextView)findViewById(R.id.title);
        TextView caption = (TextView)findViewById(R.id.caption);

        title.setText("Title");
        caption.setText("Caption");
    }




}
