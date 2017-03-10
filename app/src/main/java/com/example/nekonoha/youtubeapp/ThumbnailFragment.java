package com.example.nekonoha.youtubeapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;

import static com.example.nekonoha.youtubeapp.R.id.imageView;

/**
 * Created by c0115114 on 2017/03/09.
 */

public class ThumbnailFragment extends Fragment{
    View view;
    Video video;
    FrameLayout frame;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thumbnail, null);

        video = null;
        if(getArguments() != null){
            Serializable arg = getArguments().getSerializable("video");
            if(arg != null){
                video = (Video) arg;
            }
        }

        if(video == null) {
            video = new Video(null);
        }

        ProgressBar p = (ProgressBar)view.findViewById(R.id.progressBar);
        ImageView image = (ImageView) view.findViewById(imageView);
        TextView title = (TextView) view.findViewById(R.id.title);
        //画像取得スレッド起動
        ImageGetTask task = new ImageGetTask(image,p);
        task.execute(video.thumbnail());

        title.setText(video.title());

        frame = (FrameLayout)view.findViewById(R.id.frame);
        frame.setOnClickListener(new View.OnClickListener() {
            private Video video;
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra("video", this.video);
                startActivity(intent);
            }

            View.OnClickListener setVideo(Video v){
                this.video = v;
                return this;
            }
        }.setVideo(video));

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);


        frame.setMinimumWidth((int) (point.x * 0.5));
        frame.setMinimumHeight((int) (point.x * 0.3));

    }

}
