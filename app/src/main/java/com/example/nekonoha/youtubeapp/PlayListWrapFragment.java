package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ollie.query.Select;

/**
 * Created by c0115114 on 2017/03/11.
 */

public class PlayListWrapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_list_wrap, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PlayListFolderData playList = Select.from(PlayListFolderData.class).fetchSingle();
        PlayList.viewPlayList(this.getActivity(), playList, (TextView) getActivity().findViewById(R.id.play_list_title));
    }
}
