package com.karl.android.fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karl.android.coincounter.R;
import com.karl.android.coincounter.adapters.lists.SavesAdapter;
import com.karl.android.templates.SlimSave;

import java.util.ArrayList;
import java.util.List;

import data.sql.MySQLiteHelper;

/**
 * A simple {@link Fragment} subclass.
 *
 * This fragment shows the user their saved items from the database
 */
public class MySavesFragment extends Fragment {

    View rootView;

    RecyclerView savesList;
    TextView noInformationToDisplay;
    List<SlimSave> saves = new ArrayList<>();

    SavesAdapter adapter;

    public MySavesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_my_saves, container, false);

        // initialise the views of the fragment.
        initialiseViews();

        setUpRecyclerView();

        getInformationFromDatabase();

        savesList.setVisibility(View.GONE);

        return rootView;
    }

    /**
     * Initialise the views for the fragment.
     */
    private void initialiseViews(){
        // lists
        savesList = (RecyclerView) rootView.findViewById(R.id.my_saves_fragment_list_view);

        // text views
        noInformationToDisplay = (TextView) rootView.findViewById(R.id.my_saves_fragment_no_information_text_view);
    }

    /**
     * Set up the default settings of the recycler view.
     */
    private void setUpRecyclerView(){
        adapter = new SavesAdapter(saves);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        savesList.setLayoutManager(layoutManager);
        savesList.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        savesList.setItemAnimator(new DefaultItemAnimator());
        savesList.setAdapter(adapter);
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

        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getActivity());

        Cursor totalsFromDatabase = mySQLiteHelper.getDataFromTotals();

        if(totalsFromDatabase.getCount() == 0){
            noInformationToDisplay.setVisibility(View.VISIBLE);
            savesList.setVisibility(View.GONE);
        } else {
            noInformationToDisplay.setVisibility(View.GONE);
            savesList.setVisibility(View.VISIBLE);
            while(totalsFromDatabase.moveToNext()){
                SlimSave slimSave = new SlimSave();
                slimSave.setId(totalsFromDatabase.getString(0));
                slimSave.setTitle(totalsFromDatabase.getString(1));
                slimSave.setAmount(totalsFromDatabase.getString(2));
                slimSave.setDate(totalsFromDatabase.getString(3));
            }
        }

        adapter.notifyDataSetChanged();

        return save;
    }
}
