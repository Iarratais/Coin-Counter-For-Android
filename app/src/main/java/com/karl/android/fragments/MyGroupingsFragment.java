package com.karl.android.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karl.android.coincounter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupingsFragment extends Fragment {


    public MyGroupingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_groupings, container, false);
    }

}
