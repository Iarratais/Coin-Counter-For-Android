package com.karl.android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karl.android.coincounter.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * http://fixer.io/ should be helpful when getting the most current information on currency conversions
 */
public class CurrencyConverterFragment extends Fragment {


    public CurrencyConverterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_converter, container, false);
    }

}
