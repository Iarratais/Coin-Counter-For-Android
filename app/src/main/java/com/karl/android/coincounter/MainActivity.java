// Copyright Karl Jones

package com.karl.android.coincounter;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
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
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends ActionBarActivity {

    public static TextToSpeech t1;

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

    public AdView adView;

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

        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make_a_toast();
            }
        });

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        adView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        adView.loadAd(adRequest);
    } // End onCreate()

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
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
            if (!note20edit.getText().toString().equals("")){
                note20Amt = note20edit.getText().toString();
                System.out.println("note20listener: " + note20edit.getText().toString());
                calcTotal();
            }
            if (note20edit.getText().toString().equals("")) {
                note20Amt = "0";
                calcTotal();
            }
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
            if (!note10edit.getText().toString().equals("")){
                note10Amt = note10edit.getText().toString();
                System.out.println("note10listener: " + note10edit.getText().toString());
                calcTotal();
            }
            if (note10edit.getText().toString().equals("")) {
                note10Amt = "0";
                calcTotal();
            }
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
            if (!note5edit.getText().toString().equals("")){
                note5Amt = note5edit.getText().toString();
                System.out.println("note5listener: " + note5edit.getText().toString());
                calcTotal();
            }
            if (note5edit.getText().toString().equals("")) {
                note5Amt = "0";
                calcTotal();
            }
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
            if (!coin2edit.getText().toString().equals("")){
                coin2Amt = coin2edit.getText().toString();
                System.out.println("coin2listener: " + coin2edit.getText().toString());
                calcTotal();
            }
            if (coin2edit.getText().toString().equals("")) {
                coin2Amt = "0";
                calcTotal();
            }
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
            if (!coin1edit.getText().toString().equals("")){
                coin1Amt = coin1edit.getText().toString();
                System.out.println("coin1listener: " + coin1edit.getText().toString());
                calcTotal();
            }
            if (coin1edit.getText().toString().equals("")) {
                coin1Amt = "0";
                calcTotal();
            }
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
            if (!cent50edit.getText().toString().equals("")){
                cent50Amt = cent50edit.getText().toString();
                System.out.println("cent50listener: " + cent50edit.getText().toString());
                calcTotal();
            }
            if (cent50edit.getText().toString().equals("")) {
                cent50Amt = "0";
                calcTotal();
            }
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
            if (!cent20edit.getText().toString().equals("")){
                cent20Amt = cent20edit.getText().toString();
                System.out.println("clistener: " + cent20edit.getText().toString());
                calcTotal();
            }
            if (cent20edit.getText().toString().equals("")) {
                cent20Amt = "0";
                calcTotal();
            }
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
            if (!cent10edit.getText().toString().equals("")){
                cent10Amt = cent10edit.getText().toString();
                System.out.println("cent10listener: " + cent10edit.getText().toString());
                calcTotal();
            }
            if (cent10edit.getText().toString().equals("")) {
                cent10Amt = "0";
                calcTotal();
            }
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
            if (!cent5edit.getText().toString().equals("")){
                cent5Amt = cent5edit.getText().toString();
                System.out.println("cent5listener: " + cent5edit.getText().toString());
                calcTotal();
            }
            if (cent5edit.getText().toString().equals("")) {
                cent5Amt = "0";
                calcTotal();
            }
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
            if (!cent2edit.getText().toString().equals("")){
                cent2Amt = cent2edit.getText().toString();
                System.out.println("cent2listener: " + cent2edit.getText().toString());
                calcTotal();
            }
            if (cent2edit.getText().toString().equals("")) {
                cent2Amt = "0";
                calcTotal();
            }
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
            if (!cent1edit.getText().toString().equals("")){
                cent1Amt = cent1edit.getText().toString();
                System.out.println("cent1listener: " + cent1edit.getText().toString());
                calcTotal();
            }
            if (cent1edit.getText().toString().equals("")) {
                cent1Amt = "0";
                calcTotal();
            }
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
            if (!additionaledit.getText().toString().equals("")){
                additionalCoins = additionaledit.getText().toString();
                System.out.println("additionallistener: " + additionaledit.getText().toString());
                calcTotal();
            }
            if (additionaledit.getText().toString().equals("")) {
                additionalCoins = "0";
                calcTotal();
            }
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
                openHowTo();
                return true;
            case R.id.action_about:
                openAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // End switch
    }

    // Open the howto activity
    public void openHowTo() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    // Open the search activity
    public void openAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
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

        System.out.println("Done with calculating total");

        DecimalFormat formatter = new DecimalFormat("#,###.00");

        StringBuilder sb = new StringBuilder();
        sb.append(formatter.format(total_numb));

        setTotal(sb.toString());

        System.out.println("Total: " + getTotal());

        if(toDouble(getTotal()) < 0.01){
            overlay.setVisibility(TextView.GONE);
            System.out.println("Overlay: Invisible");
        } else {
            overlay.setText(getTotal());
            overlay.setVisibility(TextView.VISIBLE);
            System.out.println("Overlay: Visible");
        }
        return total;
    }

    // Convert String to Integer
    public static int toInt(String number) {
        int new_num = Integer.parseInt(number);
        return new_num;
    }

    // Convert String into double
    public static double toDouble(String number){
        System.out.println("toDouble: " + number);
        String num = number.replace(",", "");
        double value = Double.parseDouble(num);
        System.out.println("toDouble: " + value);
        return value;
    }

    // Total Accessors
    public static void setTotal(String incoming_total) {
        total = incoming_total;
        System.out.println("setTotal: " + total + " - total");

    }

    public static String getTotal() { return total; }

    // Takes a String and double and adds to the total using these two values
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void make_a_toast(){
        StringBuilder toSpeak = new StringBuilder();
        toSpeak.append("Your total is: " + getTotal());

        String Speak = toSpeak.toString();
        System.out.println("make_a_toast (TTS): " + Speak);

        if(Build.VERSION.RELEASE.startsWith("5")) {
            t1.speak(Speak, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            t1.speak(Speak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


}

