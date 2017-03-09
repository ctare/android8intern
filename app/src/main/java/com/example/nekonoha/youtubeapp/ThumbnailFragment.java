package com.example.nekonoha.youtubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by c0115114 on 2017/03/09.
 */

public class ThumbnailFragment extends Fragment{
    View view;
    Video video;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thumbnail, null);
        ProgressBar p = (ProgressBar)view.findViewById(R.id.progressBar);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        //画像取得スレッド起動
        Video video = new Video(null);
        ImageGetTask task = new ImageGetTask(image,p);
        task.execute(video.thumbnail());

        FrameLayout frame = (FrameLayout)view.findViewById(R.id.frame);
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}