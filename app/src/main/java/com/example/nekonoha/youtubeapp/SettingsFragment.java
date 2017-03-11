package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class SettingsFragment extends Fragment {
    SeekBar seekBar;
    TextView results;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        seekBar = (SeekBar)getActivity().findViewById(R.id.resultSeekBar);
        results = (TextView)getActivity().findViewById(R.id.results);

        //初期値
        results.setText((seekBar.getProgress() + 1)*2 + "件");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //ドラッグしたとき
                results.setText((progress + 1)*2 + "件");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //触ったとき

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO: 2017/03/11 データベースに保存する
                Log.d("SEEK",(seekBar.getProgress() + 1)*2 + "");

            }
        });

    }
}
