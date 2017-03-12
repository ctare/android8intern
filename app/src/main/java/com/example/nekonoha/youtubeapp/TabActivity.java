package com.example.nekonoha.youtubeapp;

import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LoopViewPager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import ollie.Ollie;
import ollie.query.Select;

public class TabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, PageFragment.OnFragmentInteractionListener {
    LoopViewPager loopViewPager;
    TextView[] tab = new TextView[3];
    ColorStateList[] defaultColors = new ColorStateList[3];

    SearchView search;

    final String[] pageTitle = {"Settings", "Search", "PlayList"};

    // --------------------------- debug
    public static boolean flg = false;

    public void deleteDB(AppCompatActivity a) {
        if (flg) {
            a.deleteDatabase("mytube");
            Log.d("dbdebug", "delete db");
        }
    }

    public void initDB(AppCompatActivity a) {
        if (flg) {
            PlayList.createSampleData();
            Log.d("dbdebug", "init db");
        }
        flg = false;
    }
    // --------------------------- debug


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        deleteDB(this); // debug
        Ollie.with(getApplicationContext())
                .setName("mytube")
                .setVersion(1)
                .setLogLevel(Ollie.LogLevel.FULL)
                .init();
        initDB(this); // debug
        if(Select.from(PlayListFolderData.class).where("name == 'play list'").fetchSingle() == null){
            PlayListFolderData playListFolderData = new PlayListFolderData();
            playListFolderData.name = "play list";
            playListFolderData.save();
        }

        for (PlayListVideoData playListVideoData : Select.from(PlayListVideoData.class).fetch()) {
            Log.d("video id", String.format("%d, %d, %s", playListVideoData.id, playListVideoData.parent, playListVideoData.videoId));
        }

        final LoopViewPager viewPager = (LoopViewPager) findViewById(R.id.pager);

        search = (SearchView) findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //開いた状態にする　フォーカスもする
                search.setIconified(false);
            }
        });

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //閉じないでフォーカスだけ外す
                search.clearFocus();
                return false;
            }
        });


        tab[0] = (TextView) findViewById(R.id.test_setting);
        tab[1] = (TextView) findViewById(R.id.test_top);
        tab[2] = (TextView) findViewById(R.id.test_playlist);

        tab[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        tab[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);

                if (viewPager.getCurrentItem() == 1) {
                    NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.scrollView);
                    if (scrollView != null) {
                        scrollView.fullScroll(View.FOCUS_UP);
                    }
                }
            }
        });

        tab[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });


        for (int i = 0; i < tab.length; i++) {
            defaultColors[i] = tab[i].getTextColors();
            tab[i].setText(pageTitle[i]);
        }


        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new SettingsFragment();
                    case 1:
//                        Fragment topPage = SearchTask.oldResult();
//                        return topPage == null ? new TopFragment() : topPage;

//                        return new TopFragment();
                        return new TopWrapFragment();
                    case 2:
//                        PlayListFragment playListFragment = new PlayListFragment();
//                        Bundle bundle = new Bundle();
//                        PlayListFolderData playList = Select.from(PlayListFolderData.class).fetchSingle();
//                        bundle.putSerializable("videos", playList.asVideoList());
//                        playListFragment.setArguments(bundle);
                        return new PlayListWrapFragment();
                    default:
                        return PageFragment.newInstance(position + 1);
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageTitle[position];
            }

            @Override
            public int getCount() {
                return pageTitle.length;
            }

        };

        // ViewPagerにページを設定
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(1);
        loopViewPager = viewPager;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        search.clearFocus();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onPageSelected(int position) {
        if(position == 2) {
            PlayListFolderData playList = PlayListFolderData.getSelected();
            PlayList.viewPlayList(this, playList);
        }

        for (int i = 0; i < 3; i++) {
            if (i == position) {
                tab[i].setTextColor(getResources().getColor(R.color.colorFont, getTheme()));
            } else {
                tab[i].setTextColor(defaultColors[i]);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }


}
