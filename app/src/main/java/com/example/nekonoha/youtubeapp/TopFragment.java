package com.example.nekonoha.youtubeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.nekonoha.youtubeapp.R.id.thumbnails;

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
        View view = inflater.inflate(R.layout.fragment_top, null);

        VideoList videoList = null;
        if(getArguments() != null){
            Serializable arg = getArguments().getSerializable("videos");
            if(arg != null){
                videoList = (VideoList) arg;
            }
        }

        if(videoList == null) {
            videoList = new VideoList(new ArrayList<Video>());
        }

        LinearLayout.LayoutParams outer = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams inner = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        LinearLayout thumbnails_wrap = (LinearLayout) view.findViewById(thumbnails);
        int n = 2;
        int col = (int) Math.floor(videoList.videos.size() / (float) n);
        int row = n - (videoList.videos.size() % n);
        Toast.makeText(getActivity(), String.format("%d, %d, %d", videoList.videos.size(), col, row), Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        for (int j = 0; j < col; j++) {
            LinearLayout thumbnails = new LinearLayout(getActivity());
            thumbnails.setLayoutParams(inner);
            thumbnails.setOrientation(LinearLayout.HORIZONTAL);

            for (int i = 0; i < row; i++) {
                Fragment t_fragment = new ThumbnailFragment();
                Bundle args = new Bundle();
                args.putSerializable("video", videoList.videos.get(i + j*n));
                t_fragment.setArguments(args);

                FrameLayout frameLayout = new FrameLayout(getActivity());
                thumbnails.addView(frameLayout);
                frameLayout.setId(10000 + i + j*n);
                frameLayout.setLayoutParams(inner);
                transaction.replace(frameLayout.getId(), t_fragment);
//                TextView tv = new TextView(getActivity());
//                tv.setText(videoList.videos.get(i + j*n).id());
//                thumbnails.addView(tv);

                //View thumbnail = new ThumbnailFragment().onCreateView(inflater, container, savedInstanceState);
//                thumbnail.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//                thumbnail.setWidth(0);
                //thumbnails.addView(thumbnail, inner);
            }
            thumbnails_wrap.addView(thumbnails, outer);
        }
        transaction.commit();
        created = view;
        return view;
    }
}
