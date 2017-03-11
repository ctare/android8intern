package com.example.nekonoha.youtubeapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by c0115114 on 2017/03/09.
 */

public class TopFragment extends Fragment {
    private View created = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(created != null){
            return created;
        }
        created = inOnCreateView(R.id.thumbnails, this, inflater.inflate(R.layout.fragment_top, null));
        return created;
    }

    public static View inOnCreateView(int targetLayout, Fragment fragment, View view){
        VideoList videoList = null;
        if(fragment.getArguments() != null){
            Serializable arg = fragment.getArguments().getSerializable("videos");
            if(arg != null){
                videoList = (VideoList) arg;
            }
        }

        if(videoList == null) {
            videoList = new NormalVideoList(new ArrayList<Video>());
        }

        LinearLayout.LayoutParams outer = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams inner = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        LinearLayout thumbnails_wrap = (LinearLayout) view.findViewById(targetLayout);
        int n = 2;

        int col = (int) Math.floor(videoList.videos().size() / (float) n);
        int row = n - (videoList.videos().size() % n);
        FragmentTransaction transaction = fragment.getChildFragmentManager().beginTransaction();

        for (int j = 0; j < col; j++) {
            LinearLayout thumbnails = new LinearLayout(fragment.getActivity());
            thumbnails.setLayoutParams(inner);
            thumbnails.setOrientation(LinearLayout.HORIZONTAL);
            thumbnails.setBackgroundColor(Color.BLACK);
            thumbnails.setGravity(Gravity.CENTER);

            for (int i = 0; i < row; i++) {
                Fragment t_fragment = new ThumbnailFragment();
                Bundle args = new Bundle();
                args.putSerializable("video", videoList.videos().get(i + j*n));
                t_fragment.setArguments(args);

                FrameLayout frameLayout = new FrameLayout(fragment.getActivity());
                thumbnails.addView(frameLayout);
                frameLayout.setId(10000 + i + j*n);
                frameLayout.setLayoutParams(inner);
                transaction.replace(frameLayout.getId(), t_fragment);
            }
            thumbnails_wrap.addView(thumbnails, outer);
        }
        transaction.commit();
        return view;
    }
}
