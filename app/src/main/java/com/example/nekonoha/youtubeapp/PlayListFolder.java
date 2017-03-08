package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by c0115114 on 2017/03/08.
 */

public class PlayListFolder extends PlayList{
    public Set<PlayList> playLists = new LinkedHashSet<>();
    public ViewGroup.LayoutParams wp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private final PlayListFolder self = this;

    @Override
    public void addItem(PlayList playList) {
        playLists.add(playList);
    }

    @Override
    public void tap(final LinearLayout linearLayout, final Activity activity) {
        View view = activity.findViewById(R.id.playlist_title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();
                self.getParent().tap(linearLayout, activity);
            }
        });
        linearLayout.removeAllViews();
        for (PlayList playList: playLists){
            playList.create(linearLayout, activity);
        }
    }

    @Override
    public void create(final LinearLayout linearLayout, final Activity activity) {
        TextView textView = new TextView(activity);
        textView.setText("play list");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.tap(linearLayout, activity);
            }
        });
        linearLayout.addView(textView);
    }
}
