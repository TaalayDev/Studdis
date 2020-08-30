package com.zoro.studdis.ui.main.fragments.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.zoro.studdis.R;
import com.zoro.studdis.adapters.EventsAdapter;
import com.zoro.studdis.data.EventsModel;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private RecyclerView evRecyclerV;
    private EventsAdapter eventsAdapter;

    private List<EventsModel> eventsList = new ArrayList<>();

    private int pos = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        TabLayout eventsTab = root.findViewById(R.id.eventsTab);

        Animation animationFlipIn = AnimationUtils.loadAnimation(getContext(),
                android.R.anim.slide_in_left);
        Animation animationFlipOut = AnimationUtils.loadAnimation(getContext(),
                android.R.anim.slide_out_right);
        ViewFlipper viewFlipper = root.findViewById(R.id.viewflipper);
        viewFlipper.setInAnimation(animationFlipIn);
        viewFlipper.setOutAnimation(animationFlipOut);

        eventsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ( tab.getPosition() > pos ) {
                    pos = tab.getPosition();
                    viewFlipper.showNext();
                } else if ( tab.getPosition() < pos ) {
                    pos = tab.getPosition();
                    viewFlipper.showPrevious();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        eventsAdapter = new EventsAdapter(getContext());
        evRecyclerV = root.findViewById(R.id.evRecyclerV);
        evRecyclerV.setLayoutManager(new LinearLayoutManager(getContext()));
        evRecyclerV.setAdapter(eventsAdapter);

        fillList();

        return root;
    }

    void fillList() {
        for ( int i = 0; i < 10; i++ ) {
            eventsList.add(new EventsModel(0,
                    "Поездка в село Пригородное",
                    "Поездка в село Пригородное пройдет всеми классами, по два человека в ряд. " +
                            "Поездка предназначена для того чтобы все студенты знали что такое село " +
                            "есть на карте и оно красивое. \\n\\nПо всем вопросам обращаться к своим" +
                            " классным руководителям.",
                    "21 сентября",
                    "10:50",
                    "Пригородное"));
        }
        eventsAdapter.setItems(eventsList);
    }

}
