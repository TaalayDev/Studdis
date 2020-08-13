package com.zoro.studdis.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zoro.studdis.R;
import com.zoro.studdis.ui.login.LoginActivity;

public class OnBoardingFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    public static OnBoardingFragment newInstance(int position) {
        OnBoardingFragment fragment = new OnBoardingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView boardingTitle = view.findViewById(R.id.boarding_title);
        TextView boardingDesc = view.findViewById(R.id.boarding_desc);
        ImageView boardingImage = view.findViewById(R.id.imageView);
        Button startButton = view.findViewById(R.id.start_button);
        int position = getArguments().getInt(ARG_POSITION);
        switch (position) {
            case 0:
                boardingImage.setImageResource(R.drawable.png);
                boardingTitle.setText(R.string.frag1);
                boardingDesc.setText(R.string.frag1desc);
                break;
            case 1:
                boardingImage.setImageResource(R.drawable.png2);
                boardingTitle.setText(R.string.frag2);
                boardingDesc.setText(R.string.frag2desc);
                break;

            case 2:
                boardingImage.setImageResource(R.drawable.png3);
                boardingTitle.setText(R.string.frag3);
                boardingDesc.setText(R.string.frag3desc);
                break;

            case 3:
                startButton.setVisibility(View.VISIBLE);
                startButton.setOnClickListener(v -> startActivity(new Intent(getActivity(), LoginActivity.class)));
                boardingImage.setImageResource(R.drawable.png4);
                boardingTitle.setText(R.string.frag4);
                boardingDesc.setText(R.string.frag4desc);
                break;
        }
    }
}