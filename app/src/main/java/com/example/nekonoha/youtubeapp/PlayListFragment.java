package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        View view = inflater.inflate(R.layout.fragment_play_list, container);
        passedView = view;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) passedView.findViewById(R.id.playlist_print);
        super.onActivityCreated(savedInstanceState);
        PlayList playList = PlayList.sample;
        Toast.makeText(getActivity(), getActivity().toString(), Toast.LENGTH_SHORT).show();
        playList.tap(linearLayout, getActivity());
    }
}
