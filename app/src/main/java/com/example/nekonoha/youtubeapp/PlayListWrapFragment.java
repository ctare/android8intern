package com.example.nekonoha.youtubeapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
        PlayList.viewPlayList(this.getActivity(), playList);

        FloatingActionButton add_playlist = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton);
        add_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText editView = new EditText(getActivity());
                editView.setHint("プレイリストタイトル");
                editView.setText("無題");


                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("プレイリストを作成")
                        //setViewにてビューを設定します。。
                        .setView(editView)
                        .setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                    // TODO: 2017/03/12 エンターしたときの動作
                                    dialog.dismiss();
                                    Toast.makeText(getActivity(),
                                            editView.getText().toString(),
                                            Toast.LENGTH_LONG).show();
                                    return true;
                                }
                                return false;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // TODO: 2017/03/12 OKしたときの動作
                                Toast.makeText(getActivity(),
                                        editView.getText().toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
            }
        });
    }

}
