package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ollie.query.Select;

/**
 * Created by c0115114 on 2017/03/08.
 */

public class PlayListFragment extends Fragment{
    View passedView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_list, null);
        passedView = view;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout linearLayout = (LinearLayout) passedView.findViewById(R.id.playlist_print);
        PlayList playList = Select.from(PlayListFolderData.class).fetchSingle();
        playList.tap(linearLayout, getActivity());
    }
}
