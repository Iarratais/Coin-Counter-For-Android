package com.karl.android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karl.android.coincounter.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * This fragment shows the user their saved items from the database
 */
public class MySavesFragment extends Fragment {

    View rootView;

    public MySavesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_my_saves, container, false);

        return rootView;
    }

}
