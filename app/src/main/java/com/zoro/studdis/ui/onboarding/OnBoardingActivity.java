package com.zoro.studdis.ui.onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rd.PageIndicatorView;
import com.zoro.studdis.R;

public class OnBoardingActivity extends AppCompatActivity {
    ViewPager viewPager;
    TextView textView;
    Button buttonSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        initViews();


    }


    @SuppressLint("WrongViewCast")
    public void initViews() {
        PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(4); // specify total count of indicators
        viewPager = findViewById(R.id.boarding_viewpager);
        textView = findViewById(R.id.skipButton);
        buttonSkip = findViewById(R.id.skipButton);
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewPager.setAdapter(new OnBoardingPagerAdapter(getSupportFragmentManager()));
    }

    public class OnBoardingPagerAdapter extends FragmentPagerAdapter {
        public static final int PAGES_COUNT = 4;

        public OnBoardingPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, OnBoardingPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return OnBoardingFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }

}