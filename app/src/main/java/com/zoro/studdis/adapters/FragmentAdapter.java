package com.zoro.studdis.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zoro.studdis.ui.main.fragments.home.HomeFragment;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    HomeFragment.FragmentData[] fraglist;

    public FragmentAdapter(FragmentManager fm, HomeFragment.FragmentData[] fl) {
        super(fm);
        fraglist = fl;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fraglist[position].title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fraglist[position].fragment;
    }

    @Override
    public int getCount() {
        return fraglist.length;
    }
}
