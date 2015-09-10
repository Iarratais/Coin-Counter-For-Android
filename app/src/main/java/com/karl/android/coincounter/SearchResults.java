package com.karl.android.coincounter;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    public String searchDate = "";
    public String day;
    public String month;
    public String year;

    MySQLiteHelper myDB;

    public Cursor res;
    public ArrayList<StringBuffer> displays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle(R.string.app_name);
        } catch (NullPointerException e) {
            Toast.makeText(SearchResults.this, getString(R.string.error) + e, Toast.LENGTH_SHORT).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout ll = (LinearLayout) findViewById(R.id.llo2);
        myDB = new MySQLiteHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            day = extras.getString("day");
            month = extras.getString("month");
            year = extras.getString("year");

            searchDate = day + "-" + month + "-" + year;
        } else {
            Toast.makeText(SearchResults.this, R.string.error, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SearchResults.this, ShowSaves.class);
            finish();
            startActivity(intent);
        }

        Toast.makeText(SearchResults.this, "Date: " + searchDate, Toast.LENGTH_SHORT).show();   // Working up to here, problem stemming from searchData()
        searchData(searchDate);

        ContextThemeWrapper newContext = new ContextThemeWrapper(getBaseContext(), R.style.FancyText);

        for (int i = displays.size() - 1 ; i >= 0 ; i--) {
            TextView b = new TextView(newContext);
            b.setText(displays.get(i));
            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            b.setId(i);
            b.setTag(i);
            ll.addView(b);
        }
    }

    public void searchData(String search) {
        displays = new ArrayList<StringBuffer>();
        res = myDB.searchData(search);

        if(res.getCount() == 0) {
            Toast.makeText(SearchResults.this, "No saves found", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SearchResults.this, ShowSaves.class);
            startActivity(intent);
            finish();
        }
        while(res.moveToNext()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(
                    res.getString(2) + "\n"
                            + res.getString(3) + "\n"
                            + res.getString(1) + "\n"
                            + getResources().getString(R.string.save_id) + " " + res.getString(0) + "\n"
                            + res.getString(4) + "\n\n");
            displays.add(buffer);
        }
    }

    @Override
    public void onDestroy() {
        finish();
        super.onDestroy();
    }
}
