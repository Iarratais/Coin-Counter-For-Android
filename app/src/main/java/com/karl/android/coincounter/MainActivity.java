package com.karl.android.coincounter;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


public class MainActivity extends ActionBarActivity {

    public static String note50Amt;
    public static String note20Amt;
    public static String note10Amt;
    public static String note5Amt;
    public static String coin2Amt;
    public static String coin1Amt;
    public static String cent50Amt;
    public static String cent20Amt;
    public static String cent10Amt;
    public static String cent5Amt;
    public static String cent2Amt;
    public static String cent1Amt;
    public static String additionalCoins;

    public final static String EXTRA_MESSAGE = "com.karl.android.coincounter.MESSAGE";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.action_about:
                openAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // End switch
    }

    public void openSettings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void calculateCoins(View button) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        final EditText notes50 = (EditText) findViewById(R.id.notes50);
        note50Amt = notes50.getText().toString();
        if(note50Amt.matches("")){
            note50Amt = "0";
            //Toast.makeText(this, "Please fill in the blanks", Toast.LENGTH_SHORT).show();
            //return;
        }

        final EditText notes20 = (EditText) findViewById(R.id.notes20);
        note20Amt = notes20.getText().toString();
        if(note20Amt.matches("")){
            note20Amt = "0";
        }

        final EditText notes10 = (EditText) findViewById(R.id.notes10);
        note10Amt = notes10.getText().toString();
        if(note10Amt.matches("")){
            note10Amt = "0";
        }

        final EditText notes5 = (EditText) findViewById(R.id.notes5);
        note5Amt = notes5.getText().toString();
        if(note5Amt.matches("")){
            note5Amt = "0";
        }

        final EditText coins2 = (EditText) findViewById(R.id.coins2);
        coin2Amt = coins2.getText().toString();
        if(coin2Amt.matches("")){
            coin2Amt = "0";
        }

        final EditText coins1 = (EditText) findViewById(R.id.coins1);
        coin1Amt = coins1.getText().toString();
        if(coin1Amt.matches("")){
            coin1Amt = "0";
        }

        final EditText cents50 = (EditText) findViewById(R.id.cent50);
        cent50Amt = cents50.getText().toString();
        if(cent50Amt.matches("")){
            cent50Amt = "0";
        }

        final EditText cents20 = (EditText) findViewById(R.id.cent20);
        cent20Amt = cents20.getText().toString();
        if(cent20Amt.matches("")){
            cent20Amt = "0";
        }

        final EditText cents10 = (EditText) findViewById(R.id.cent10);
        cent10Amt = cents10.getText().toString();
        if(cent10Amt.matches("")){
            cent10Amt = "0";
        }

        final EditText cents5 = (EditText) findViewById(R.id.cent5);
        cent5Amt = cents5.getText().toString();
        if(cent5Amt.matches("")){
            cent5Amt = "0";
        }

        final EditText cents2 = (EditText) findViewById(R.id.cent2);
        cent2Amt = cents2.getText().toString();
        if(cent2Amt.matches("")){
            cent2Amt = "0";
        }

        final EditText cents1 = (EditText) findViewById(R.id.cent1);
        cent1Amt = cents1.getText().toString();
        if(cent1Amt.matches("")){
            cent1Amt = "0";
        }

        final EditText additions = (EditText) findViewById(R.id.extraAdditions);
        additionalCoins = additions.getText().toString();
        if(additionalCoins.matches("")){
            additionalCoins = "0";
        }

        double total = calcTotal();

        if(total == 0.00) {
            Toast.makeText(this, "Fill in blanks to get result!", Toast.LENGTH_SHORT).show();
            return;
        }

        String totalString = String.valueOf(total);

        intent.putExtra(EXTRA_MESSAGE, totalString);
        startActivity(intent);
    }

    public static double calcTotal() {
        double note50 = toInt(note50Amt) * 50.0;

        double note20 = toInt(note20Amt) * 20.0;

        double note10 = toInt(note10Amt) * 10.0;

        double note5 = toInt(note5Amt) * 5.0;

        double euro2 = toInt(coin2Amt) * 2.0;

        double euro1 = toInt(coin1Amt) * 1.0;

        double cent50 = toInt(cent50Amt) * 0.5;

        double cent20 = toInt(cent20Amt) * 0.2;

        double cent10 = toInt(cent10Amt) * 0.1;

        double cent5 = toInt(cent5Amt) * 0.05;

        double cent2 = toInt(cent2Amt) * 0.02;

        double cent1 = toInt(cent1Amt) * 0.01;

        double total = 0;

        total += note50 + note20 + note10 + note5 + euro2 + euro1 + cent50 + cent20 + cent10 + cent5 + cent2 + cent1 + toInt(additionalCoins);

        System.out.println("Total: " + total);

        return total;
    }

    // Method to convert the strings into integers
    public static Integer toInt(String number) {

        int num = Integer.parseInt(number);

        return num;
    }
}

