// Copyright Karl Jones

package com.karl.android.coincounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class MainActivity extends ActionBarActivity {

    public static String note50Amt = "0";
    public static String note20Amt = "0";
    public static String note10Amt = "0";
    public static String note5Amt = "0";
    public static String coin2Amt = "0";
    public static String coin1Amt = "0";
    public static String cent50Amt = "0";
    public static String cent20Amt = "0";
    public static String cent10Amt = "0";
    public static String cent5Amt = "0";
    public static String cent2Amt = "0";
    public static String cent1Amt = "0";
    public static String additionalCoins = "0";
    private static String total = "0";

    public final static String EXTRA_MESSAGE = "com.karl.android.coincounter.MESSAGE";

    private Toolbar toolbar;

    // All edittexts
    private EditText note50edit;
    private EditText note20edit;
    private EditText note10edit;
    private EditText note5edit;
    private EditText coin2edit;
    private EditText coin1edit;
    private EditText cent50edit;
    private EditText cent20edit;
    private EditText cent10edit;
    private EditText cent5edit;
    private EditText cent2edit;
    private EditText cent1edit;
    private EditText additionaledit;
    public static TextView overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar    = (Toolbar) findViewById (R.id.tool_bar);
        setSupportActionBar(toolbar);

        note50edit       = (EditText) findViewById (R.id.notes50);
        note20edit       = (EditText) findViewById (R.id.notes20);
        note10edit       = (EditText) findViewById (R.id.notes10);
        note5edit        = (EditText) findViewById (R.id.notes5);
        coin2edit        = (EditText) findViewById (R.id.coins2);
        coin1edit        = (EditText) findViewById (R.id.coins1);
        cent50edit       = (EditText) findViewById (R.id.cent50);
        cent20edit       = (EditText) findViewById (R.id.cent20);
        cent10edit       = (EditText) findViewById (R.id.cent10);
        cent5edit        = (EditText) findViewById (R.id.cent5);
        cent2edit        = (EditText) findViewById (R.id.cent2);
        cent1edit        = (EditText) findViewById (R.id.cent1);
        additionaledit   = (EditText) findViewById (R.id.extraAdditions);
        overlay          = (TextView) findViewById (R.id.OverlayTotal);

        // Set text watchers
        note50edit.addTextChangedListener(note50listener);
        note20edit.addTextChangedListener(note20listener);
        note10edit.addTextChangedListener(note10listener);
        note5edit.addTextChangedListener(note5listener);
        coin2edit.addTextChangedListener(coin2listener);
        coin1edit.addTextChangedListener(coin1listener);
        cent50edit.addTextChangedListener(cent50listener);
        cent20edit.addTextChangedListener(cent20listener);
        cent10edit.addTextChangedListener(cent10listener);
        cent5edit.addTextChangedListener(cent5listener);
        cent2edit.addTextChangedListener(cent2listener);
        cent1edit.addTextChangedListener(cent1listener);
        additionaledit.addTextChangedListener(additionallistener);
    }

    // Text watcher for €50
    private final TextWatcher note50listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!note50edit.getText().toString().equals("")){
                note50Amt = note50edit.getText().toString();
                System.out.println("note50listener: " + note50edit.getText().toString());
                calcTotal();
            }
            if (note50edit.getText().toString().equals("")) {
                note50Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note20listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            note20Amt = note20edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher note10listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            note10Amt = note10edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher note5listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            note5Amt = note5edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher coin2listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            coin2Amt = coin2edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher coin1listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            coin1Amt = coin1edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher cent50listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            cent50Amt = cent50edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher cent20listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            cent20Amt = cent20edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher cent10listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            cent10Amt = cent10edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher cent5listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            cent5Amt = cent5edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher cent2listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            cent2Amt = cent2edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher cent1listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            cent1Amt = cent1edit.getText().toString();
            calcTotal();
        }
    };
    private final TextWatcher additionallistener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            additionalCoins = additionaledit.getText().toString();
            calcTotal();
        }
    };

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

        double total_numb = note50 + note20 + note10 + note5 + euro2 + euro1 + cent50 + cent20 + cent10 + cent5 + cent2 + cent1 + toInt(additionalCoins);

        StringBuilder sb = new StringBuilder();
        sb.append(total_numb);

        setTotal(sb.toString());

        System.out.println("Total: " + getTotal());

        overlay.setText(getTotal());
        return total;
    }

    // Method to convert the strings into integers
    public static int toInt(String number) {
        int new_num = Integer.parseInt(number);
        return new_num;
    }

    public static double toDouble(String number){
        double value = Double.parseDouble(number);
        return value;
    }

    // Total Accessors
    public static void setTotal(String incoming_total) {
        total = incoming_total;
        System.out.println("setTotal: " + total + " - total");

    }

    public static String getTotal() { return total; }

    public static String add_to_total(String incoming_addition, double multiple) {
        String totalNum = getTotal();
        System.out.println("add_to_total: " + totalNum + " - totalNum");

        double total_number = toDouble (totalNum);
        System.out.println("add_to_total: " + total_number + " - total_number");

        double incoming = toDouble (incoming_addition);
        System.out.println("add_to_total: " + incoming + " - incoming");

        double current = incoming * multiple;
        System.out.println("add_to_total: " + current + " - current");

        double final_num = total_number + current;
        System.out.println("add_to_total: " + final_num + " - final_num");

        StringBuilder sb = new StringBuilder();
        sb.append(final_num);

        String total_final = sb.toString();
        System.out.println("add_to_total: " + total_final + " - total_final");

        setTotal(total_final);

        return total_final;
    }
}

