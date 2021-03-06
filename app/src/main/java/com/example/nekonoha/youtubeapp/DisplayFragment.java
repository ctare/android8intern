package com.example.nekonoha.youtubeapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import ollie.query.Select;
import ollie.util.QueryUtils;


public class DisplayFragment extends Fragment {
    YoutubeFragment fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_display, null);

        Video video = null;
        if(getArguments() != null){
            Serializable arg = getArguments().getSerializable("video");
            if(arg != null){
                video = (Video) arg;
            }
        }

        if(video == null) {
            video = new NormalVideo(null);
        }

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView description = (TextView) view.findViewById(R.id.description);

        title.setText(video.title());
//        description.setText(video.description());

        // フラグメント起動 （v4の作法で）
        Bundle bundle = new Bundle();
        bundle.putSerializable("video", video);
        fragment = new YoutubeFragment();
        fragment.setArguments(bundle);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.main, fragment)
                .commit();

        DescriptionGetTask d_task = new DescriptionGetTask(this);
        d_task.execute(video.id());
        Log.d("ids",video.id());

        final Button button = (Button) view.findViewById(R.id.like);
        final Video finalVideo = video;
        if(Select.from(PlayListVideoData.class).where("video_id == ?", finalVideo.id()).fetchSingle() != null){
            button.setText("♡");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayListFolderData top = PlayListFolderData.getSelected();
                PlayListVideoData old = Select.from(PlayListVideoData.class).where("video_id == ?", finalVideo.id()).fetchSingle();
                if(old != null){
                    QueryUtils.execSQL("delete from video_data where video_id == ?", new String[]{finalVideo.id()});
                    button.setText("♥");
                } else {
                    PlayListVideoData data = new PlayListVideoData();
                    data.videoId = finalVideo.id();
                    data.title = finalVideo.title();
                    data.thumbnail = finalVideo.thumbnail();
                    top.add(data);
                    top.save();
                    button.setText("♡");
                }
            }
        });

        return view;
    }
}
