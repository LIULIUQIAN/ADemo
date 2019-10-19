package com.example.ademo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GuideFragment extends Fragment {

    final static String LAYOUT_ID = "layoutid";

    public static GuideFragment newInstance(int layoutId) {

        GuideFragment pane = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_ID,layoutId);
        pane.setArguments(args);
        return pane;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(getArguments().getInt(LAYOUT_ID,-1),container,false);
        return rootView;
    }
}
