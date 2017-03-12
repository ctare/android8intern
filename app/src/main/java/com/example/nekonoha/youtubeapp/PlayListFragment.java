package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import ollie.query.Select;


/**
 * Created by c0115114 on 2017/03/08.
 */

public class PlayListFragment extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = TopFragment.inOnCreateView(R.id.playlist_print, this, inflater.inflate(R.layout.fragment_play_list, null), null);

        PlayListFolderData playListFolderData = null;
        if(getArguments() != null){
            Serializable arg = getArguments().getSerializable("videos");
            if(arg != null && arg instanceof PlayListFolderData.AsVideoList){
                playListFolderData = ((PlayListFolderData.AsVideoList) arg).asData();
            }
        }

        Log.d("activ", playListFolderData == null ? "null" : "not null");
        if(playListFolderData != null){
            TextView textView = (TextView) view.findViewById(R.id.play_list_title);
            final PlayListFolderData parent = playListFolderData.getParent();
            if(parent != null) {
                textView.setText("../" + parent.name);
            } else {
                textView.setText(playListFolderData.name);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(parent != null){
                        PlayList.viewPlayList(getActivity(), parent);
                    }
                }
            });
        }

        return view;
    }


}
