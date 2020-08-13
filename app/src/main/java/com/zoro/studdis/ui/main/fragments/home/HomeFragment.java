package com.zoro.studdis.ui.main.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zoro.studdis.R;
import com.zoro.studdis.adapters.FragmentAdapter;
import com.zoro.studdis.ui.main.fragments.events.EventsFragment;
import com.zoro.studdis.ui.main.fragments.timetable.TimetableFragment;
import com.zoro.studdis.ui.main.fragments.assessment.AssessmentFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*
            homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
         */

        FragmentData[] fragList = {
                new FragmentData(new TimetableFragment(), getString(R.string.tab_timetable)),
                new FragmentData(new AssessmentFragment(), getString(R.string.tab_assessment)),
                new FragmentData(new EventsFragment(), getString(R.string.tab_events)) };
        FragmentAdapter fAdapter = new FragmentAdapter(getChildFragmentManager(), fragList);
        ViewPager vPager = root.findViewById(R.id.vPager);
        vPager.setAdapter(fAdapter);

        ((TabLayout) root.findViewById(R.id.tabLayout)).setupWithViewPager(vPager);

        return root;
    }

    public class FragmentData {
        public Fragment fragment;
        public String title;
        public FragmentData(Fragment fragment, String title) {
            this.fragment = fragment;
            this.title = title;
        }
    }

}
