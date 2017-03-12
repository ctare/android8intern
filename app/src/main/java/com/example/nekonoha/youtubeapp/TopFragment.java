package com.example.nekonoha.youtubeapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
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
    NestedScrollView scrollView;
    LinearLayout thumbnails;

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

        int row = (int)Math.ceil(videoList.videos().size() / (float) n);
        int col = n ;


        FragmentTransaction transaction = fragment.getChildFragmentManager().beginTransaction();

        for (int j = 0; j < row; j++) {
            LinearLayout thumbnails = new LinearLayout(fragment.getActivity());
            thumbnails.setLayoutParams(inner);
            thumbnails.setOrientation(LinearLayout.HORIZONTAL);
            thumbnails.setBackgroundColor(Color.BLACK);
            thumbnails.setGravity(Gravity.CENTER);

            if(j == row - 1 && videoList.videos().size()%n != 0){
                col = videoList.videos().size()% n;
                Log.d("row,col", row + "," + col + "," + j);
            }


            for (int i = 0; i < col; i++) {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        scrollView = (NestedScrollView) getActivity().findViewById(R.id.scrollView);
        thumbnails = (LinearLayout) getActivity().findViewById(R.id.thumbnails);


        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int scrollHeight = thumbnails.getHeight() - scrollView.getHeight();
                if(scrollY >= scrollHeight){
                    Log.d("OK","OK");
                    // TODO: 2017/03/12 ここでタスク

                }
                Log.d("OK",scrollHeight + "," + scrollY);

            }
        });
    }

    public void addNextPage(final String nextPageToken){
        NextPageTask task = new NextPageTask(this);
        task.execute(nextPageToken);
    }
}
