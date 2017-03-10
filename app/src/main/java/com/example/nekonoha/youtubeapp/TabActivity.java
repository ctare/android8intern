package com.example.nekonoha.youtubeapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LoopViewPager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, PageFragment.OnFragmentInteractionListener {
    LoopViewPager loopViewPager;
    TextView[] tab = new TextView[3];
    ColorStateList[] defaultColors = new ColorStateList[3];



    final String[] pageTitle = {"Settings", "Search", "PlayLists"};
    private ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final LoopViewPager viewPager = (LoopViewPager) findViewById(R.id.pager);
        final String[] pageTitle = {"Settings", "Search", "PlayLists"};


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
                    case 1:
                        Fragment topPage = SearchTask.oldResult();
                        return topPage == null ? new TopFragment() : topPage;
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

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < 3; i++) {
            if(i == position) {
                tab[i].setTextColor(Color.RED);
            } else {
                tab[i].setTextColor(defaultColors[i]);

            }
        }
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }



}
