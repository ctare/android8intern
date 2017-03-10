package com.example.nekonoha.youtubeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.Serializable;


public class YoutubeFragment extends Fragment {
    private static final String API_KEY = "AIzaSyCg-nDKbwKIzh0W43xRHSV0HnLD-SVhR4w";
    private static String VIDEO_ID = "N4mEzFDjqtA";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  = inflater.inflate(R.layout.fragment_youtube, container, false);

        // YouTubeフラグメントインスタンスを取得
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        // レイアウトにYouTubeフラグメントを追加
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        Video video = null;
        if(getArguments() != null){
            Serializable arg = getArguments().getSerializable("video");
            if(arg != null){
                video = (Video) arg;
            }
        }

        if(video == null) {
            video = new Video(null);
        }

        // YouTubeフラグメントのプレーヤーを初期化する
        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            private Video video;
            public YouTubePlayer.OnInitializedListener setVideo(Video video){
                this.video = video;
                return this;
            }

            // YouTubeプレーヤーの初期化成功
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.loadVideo(video.id());

                    player.play();
                }
            }

            // YouTubeプレーヤーの初期化失敗
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        }.setVideo(video));

        return rootView;
    }


}