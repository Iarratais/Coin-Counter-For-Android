// Copyright of Karl Jones

package com.karl.android.coincounter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ShowSaves extends ActionBarActivity {

    MySQLiteHelper myDB;

    public Button btncount;

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saves);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        myDB = new MySQLiteHelper(this);

        viewAll();

        LinearLayout ll = (LinearLayout) findViewById(R.id.llo2);
        ContextThemeWrapper newContext = new ContextThemeWrapper(getBaseContext(), R.style.FancyText);

        for (int i = displays.size() - 1 ; i >= 0 ; i--) {
            TextView b = new TextView(newContext);
            b.setText(displays.get(i));
            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            b.setId(i);
            b.setTag(i);
            final int j = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDB.deleteData(toStringFromInt(j));
                }
            });
            ll.addView(b);
        }

        result = (EditText) findViewById(R.id.temp_edit);

        btncount = (Button) findViewById(R.id.btnCount);

        btncount.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToCount();
                    }
                } );

        // Calendar variables
        calendar = (CalendarView) findViewById(R.id.calendarView);
        initializeCalendar();
    }

    // Method opens the settings from the menu in the main activity.
    public void openSettings() {
        Intent intent = new Intent(ShowSaves.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void goToCount() {
        Intent intent = new Intent(ShowSaves.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_shrink_fade_out_from_bottom);
    }

    public void initializeCalendar() {
        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);
        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);

        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                String newDay;
                String newMonth;
                String newYear;

                if (day == 1 || day == 2 || day == 3 || day == 4 || day == 5 || day == 6 || day == 7 || day == 8 || day == 9) {
                    newDay = "0" + Integer.toString(day);
                } else {
                    newDay = Integer.toString(day);
                }
                if (month == 1 || month == 2 || month == 3 || month == 4 || month == 5 || month == 6 || month == 7 || month == 8 || month == 9) {
                    newMonth = "0" + Integer.toString(month+1);
                }else {
                    newMonth = Integer.toString(month+1);
                }
                newYear = Integer.toString(year).substring(2);

                searchDate(newYear, newMonth, newDay);
            }
        });
        calendar.setVisibility(View.GONE);
    }
    // Convert String to Integer
    public static int toInt(String number) {
        return Integer.parseInt(number);
    }

    public String toStringFromInt(int i) {return Integer.toString(i);}

    public Cursor res;
    public ArrayList<StringBuffer> displays;

    public void viewAll() {

        displays = new ArrayList<>();
        res = myDB.getAllData();
        if(res.getCount() == 0) {
            Toast.makeText(ShowSaves.this, "No saves found", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ShowSaves.this, MainActivity.class);
            startActivity(intent);

        }
        while(res.moveToNext()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(
                    res.getString(2) + "\n"
                            + res.getString(3) + "\n"
                            + res.getString(1) + "\n"
                            + getString(R.string.save_id) + " " + res.getString(0) + "\n"
                            + res.getString(4) + "\n\n");
            displays.add(buffer);
        }
    }

    public void searchDate(String year, String month, String day) {
        Bundle extra = new Bundle();

        extra.putString("year", year);
        extra.putString("month", month);
        extra.putString("day", day);

        Intent intent = new Intent(ShowSaves.this, SearchResults.class);
        intent.putExtras(extra);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_saves, menu);
        return true;
    }

    public EditText result;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_delete_entry:
                calendar.setVisibility(View.GONE);
                LayoutInflater li = LayoutInflater.from(this);
                View promptsView = li.inflate(R.layout.prompts, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                alert
                        .setCancelable(true)
                        .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                result.setText(userInput.getText());
                                deleteEntry(result.getText().toString());
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).create().show();
                break;

            // Temporarily removed, this needs work.
            /*case R.id.action_search:
                if(calendar.getVisibility() == View.GONE) {
                    calendar.setVisibility(View.VISIBLE);
                } else {
                    calendar.setVisibility(View.GONE);
                } */
            case R.id.action_main_settings:
                openSettings();
                break;
            }  // end case
        return super.onOptionsItemSelected(item);
    }

    public void deleteEntry(String id) {

        int result = myDB.deleteData(id);
        if(result > 0) {
            Intent intent = new Intent(ShowSaves.this, ShowSaves.class);
            startActivity(intent);
            Toast.makeText(ShowSaves.this, R.string.entry_deleted, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ShowSaves.this, R.string.entry_not_deleted, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroy() {
        finish();
        super.onDestroy();
    }
}
