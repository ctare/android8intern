package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.PrimaryKey;
import ollie.annotation.Table;
import ollie.query.Select;

/**
 * Created by c0115114 on 2017/03/10.
 */

@Table("folder_data")
public class PlayListFolderData extends PlayList{
    @Column("name")
    public String name;

    @Override
    public void tap(final LinearLayout linearLayout, final Activity activity) {
        View view = activity.findViewById(R.id.play_list_title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
                PlayList parent = PlayListFolderData.this.getParent();
                if(parent != null){
                    parent.tap(linearLayout, activity);
                }
            }
        });
        linearLayout.removeAllViews();
        for (PlayList playList: Select.from(PlayListFolderData.class).where("parent == ?", PlayListFolderData.this.id).fetch()){
            playList.create(linearLayout, activity);
        }
        for (PlayList playList: Select.from(PlayListVideoData.class).where("parent == ?", PlayListFolderData.this.id).fetch()){
            playList.create(linearLayout, activity);
        }
    }

    @Override
    public void create(final LinearLayout linearLayout, final Activity activity) {
        TextView textView = new TextView(activity);
        textView.setText("play list");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(30);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayListFolderData.this.tap(linearLayout, activity);
            }
        });
        linearLayout.addView(textView);
    }
}
