package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_display, null);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView caption = (TextView) view.findViewById(R.id.caption);

        title.setText("Title");
        caption.setText("Caption");

        // フラグメント起動 （v4の作法で）
        YoutubeFragment fragment = new YoutubeFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.main, fragment)
                .addToBackStack(null)
                .commit();

        return view;
    }
}
