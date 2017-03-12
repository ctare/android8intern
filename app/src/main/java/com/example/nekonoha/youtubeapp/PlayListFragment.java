package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        return TopFragment.inOnCreateView(R.id.playlist_print, this, inflater.inflate(R.layout.fragment_play_list, null), null);
    }
}
