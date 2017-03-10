package com.example.nekonoha.youtubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LoopViewPager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class DisplayActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private final int INITIAL_PAGE = 1;
    LoopViewPager viewPager;
    Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        SearchTask.reset();

        video = null;
        Intent selfIntent = getIntent();
        Serializable arg = selfIntent.getSerializableExtra("video");
        if(arg != null){
            video = (Video) arg;
        }

        if(video == null) {
            video = new Video(null);
        }
        Log.d("display activity", "init success");

        viewPager = (LoopViewPager) findViewById(R.id.pager);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return new Fragment();
                    case 1:
                        DisplayFragment displayFragment = new DisplayFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("video", video);
                        displayFragment.setArguments(bundle);
                        return displayFragment;
                    case 2:
                        return new Fragment();
                    default:
                        return new Fragment();
                }
                //switchでposition=0のときと2のときを空フラグメント
                //1のときをYoutubeとかいろいろ表示するフラグメントにすればいい気がする
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "turai";
            }

            @Override
            public int getCount() {
                return 3;
            }

        };


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(INITIAL_PAGE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setCurrentItem(INITIAL_PAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position != INITIAL_PAGE){
            Intent intent = new Intent(this, TabActivity.class);
            intent.setFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
