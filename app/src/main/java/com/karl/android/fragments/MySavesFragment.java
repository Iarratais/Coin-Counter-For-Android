package com.karl.android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.karl.android.coincounter.R;
import com.karl.android.templates.SlimSave;

/**
 * A simple {@link Fragment} subclass.
 *
 * This fragment shows the user their saved items from the database
 */
public class MySavesFragment extends Fragment {

    View rootView;

    ListView savesList;
    TextView noInformationToDisplay;

    public MySavesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_my_saves, container, false);

        // initialise the views of the fragment.
        initialiseViews();

        savesList.setVisibility(View.GONE);

        return rootView;
    }

    /**
     * Initialise the views for the fragment.
     */
    private void initialiseViews(){
        // lists
        savesList = (ListView) rootView.findViewById(R.id.my_saves_fragment_list_view);

        // text views
        noInformationToDisplay = (TextView) rootView.findViewById(R.id.my_saves_fragment_no_information_text_view);
    }

    /**
     * This method gets the information from the database and return it in a Save object to be added into the views.
     * This method only returns the essential information that the app needs in order to display
     * the list of items for the user to view.
     *
     * @return a SlimSave object with the information from the database.
     */
    private SlimSave getInformationFromDatabase(){
        SlimSave save = new SlimSave();

        // get information from the database and add it to the save object.

        return save;
    }
}
