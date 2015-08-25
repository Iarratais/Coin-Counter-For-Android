// Copyright Karl Jones

package com.karl.android.coincounter;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends ActionBarActivity{
    MySQLiteHelper myDB;

    public static TextToSpeech t1;

    public static String total_title = " ";
    public static String note10000Amt = "0";
    public static String note5000Amt = "0";
    public static String note2000Amt = "0";
    public static String note1000Amt = "0";
    public static String note500Amt = "0";
    public static String note200Amt = "0";
    public static String note100Amt = "0";
    public static String note50Amt = "0";
    public static String note20Amt = "0";
    public static String note10Amt = "0";
    public static String note5Amt = "0";
    public static String note1Amt = "0";
    public static String coin2Amt = "0";
    public static String coin1Amt = "0";
    public static String cent50Amt = "0";
    public static String cent25Amt = "0";
    public static String cent20Amt = "0";
    public static String cent10Amt = "0";
    public static String cent5Amt = "0";
    public static String cent2Amt = "0";
    public static String cent1Amt = "0";
    public static String additionalCoins = "0";
    private static String total = "0";

    // All edittexts
    private EditText total_title_, note10000edit, note5000edit, note2000edit, note1000edit, note500edit, note200edit, note100edit, note50edit, note20edit, note10edit, note5edit, note1edit;
    private EditText coin2edit, coin1edit;
    private EditText cent50edit, cent25edit, cent20edit, cent10edit, cent5edit, cent2edit, cent1edit;
    private EditText additionaledit;
    public static TextView overlay;

    public Button btnadd;
    public Button btnsave;

    public AdView adView;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        updateLanguage(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar    = (Toolbar) findViewById (R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        settings = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        myDB = new MySQLiteHelper(this);

        overlay          = (TextView) findViewById (R.id.OverlayTotal);
        note10000edit    = (EditText) findViewById (R.id.notes10000);
        note5000edit     = (EditText) findViewById (R.id.notes5000);
        note2000edit     = (EditText) findViewById (R.id.notes2000);
        note1000edit     = (EditText) findViewById (R.id.notes1000);
        note500edit      = (EditText) findViewById (R.id.notes500);
        note200edit      = (EditText) findViewById (R.id.notes200);
        note100edit      = (EditText) findViewById (R.id.notes100);
        note50edit       = (EditText) findViewById (R.id.notes50);
        note20edit       = (EditText) findViewById (R.id.notes20);
        note10edit       = (EditText) findViewById (R.id.notes10);
        note5edit        = (EditText) findViewById (R.id.notes5);
        note1edit        = (EditText) findViewById (R.id.notes1);
        coin2edit        = (EditText) findViewById (R.id.coins2);
        coin1edit        = (EditText) findViewById (R.id.coins1);
        cent50edit       = (EditText) findViewById (R.id.cent50);
        cent25edit       = (EditText) findViewById (R.id.cent25);
        cent20edit       = (EditText) findViewById (R.id.cent20);
        cent10edit       = (EditText) findViewById (R.id.cent10);
        cent5edit        = (EditText) findViewById (R.id.cent5);
        cent2edit        = (EditText) findViewById (R.id.cent2);
        cent1edit        = (EditText) findViewById (R.id.cent1);
        additionaledit   = (EditText) findViewById (R.id.extraAdditions);
        total_title_     = (EditText) findViewById (R.id.total_title);

        btnadd = (Button) findViewById(R.id.button);
        btnsave = (Button) findViewById(R.id.btnSaves);

        // Set text watchers
        note10000edit.addTextChangedListener(note10000listener);
        note5000edit.addTextChangedListener(note5000listener);
        note2000edit.addTextChangedListener(note2000listener);
        note1000edit.addTextChangedListener(note1000listener);
        note500edit.addTextChangedListener(note500listener);
        note200edit.addTextChangedListener(note200listener);
        note100edit.addTextChangedListener(note100listener);
        note50edit.addTextChangedListener(note50listener);
        note20edit.addTextChangedListener(note20listener);
        note10edit.addTextChangedListener(note10listener);
        note5edit.addTextChangedListener(note5listener);
        note1edit.addTextChangedListener(note1listener);
        coin2edit.addTextChangedListener(coin2listener);
        coin1edit.addTextChangedListener(coin1listener);
        cent50edit.addTextChangedListener(cent50listener);
        cent25edit.addTextChangedListener(cent25listener);
        cent20edit.addTextChangedListener(cent20listener);
        cent10edit.addTextChangedListener(cent10listener);
        cent5edit.addTextChangedListener(cent5listener);
        cent2edit.addTextChangedListener(cent2listener);
        cent1edit.addTextChangedListener(cent1listener);
        additionaledit.addTextChangedListener(additionallistener);
        total_title_.addTextChangedListener(total_title_listener);

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

        btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddData();
                    }
                });

        btnsave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showsaves();
                    }
                });

        hintChecks();
        clearAll();
    } // End onCreate()


    public static void updateLanguage(Context ctx) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        String lang = prefs.getString("locale_override", "");
        updateLanguage(ctx, lang);
    }

    public static void updateLanguage(Context ctx, String lang) {
        Configuration cfg = new Configuration();
        if(!TextUtils.isEmpty(lang)) {
            cfg.locale = new Locale(lang);
        } else {
            cfg.locale = Locale.getDefault();
        }
        ctx.getResources().updateConfiguration(cfg, null);
    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }

    public void AddData() {
        boolean isEmpty = checkIfEmpty();
        if (!isEmpty) {
            boolean isInserted = myDB.insertData(getFormatDate(), total_title_.getText().toString(), overlay.getText().toString());
            if (isInserted) {
                Toast.makeText(MainActivity.this, R.string.add_data_success, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, R.string.add_data_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    // ----------------------------------- ACTIVITY RELATED METHODS ---------------------------------------------------
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
        hintChecks();
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public String getFormatDate() {
        Date now = new Date();
        String nowAsString = new SimpleDateFormat("dd-MM-yy", getResources().getConfiguration().locale).format(now);
        String andMins = nowAsString + new SimpleDateFormat("HH:mm", getResources().getConfiguration().locale).format(now);
        return andMins;
    }

    // --------------------------------------------------- TEXT WATCHERS --------------------------------------------------------------------
    private final TextWatcher total_title_listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!total_title_.getText().toString().equals("")){
                total_title = total_title_.getText().toString();
                System.out.println("total_title_listener: " + total_title_.getText().toString());
            }
            if (total_title_.getText().toString().equals("")) {
                total_title = " ";
                calcTotal();
            }
        }
    };
    private final TextWatcher note10000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note10000edit.getText().toString().equals("")){
                note10000Amt = note10000edit.getText().toString();
                System.out.println("note10000listener: " + note10000edit.getText().toString());
                calcTotal();
            }
            if (note10000edit.getText().toString().equals("")) {
                note10000Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note5000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note5000edit.getText().toString().equals("")){
                note5000Amt = note5000edit.getText().toString();
                System.out.println("note5000listener: " + note5000edit.getText().toString());
                calcTotal();
            }
            if (note5000edit.getText().toString().equals("")) {
                note5000Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note2000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note2000edit.getText().toString().equals("")){
                note2000Amt = note2000edit.getText().toString();
                System.out.println("note2000listener: " + note2000edit.getText().toString());
                calcTotal();
            }
            if (note2000edit.getText().toString().equals("")) {
                note2000Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note1000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note1000edit.getText().toString().equals("")){
                note1000Amt = note1000edit.getText().toString();
                System.out.println("note1000listener: " + note1000edit.getText().toString());
                calcTotal();
            }
            if (note1000edit.getText().toString().equals("")) {
                note1000Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note500listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note500edit.getText().toString().equals("")){
                note500Amt = note500edit.getText().toString();
                System.out.println("note500listener: " + note500edit.getText().toString());
                calcTotal();
            }
            if (note500edit.getText().toString().equals("")) {
                note500Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note200listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note200edit.getText().toString().equals("")){
                note200Amt = note200edit.getText().toString();
                System.out.println("note200listener: " + note200edit.getText().toString());
                calcTotal();
            }
            if (note2000edit.getText().toString().equals("")) {
                note200Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note100listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note100edit.getText().toString().equals("")){
                note100Amt = note100edit.getText().toString();
                System.out.println("note100listener: " + note100edit.getText().toString());
                calcTotal();
            }
            if (note100edit.getText().toString().equals("")) {
                note100Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note50listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
    private final TextWatcher note1listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note1edit.getText().toString().equals("")){
                note1Amt = note1edit.getText().toString();
                System.out.println("note1listener: " + note1edit.getText().toString());
                calcTotal();
            }
            if (note1edit.getText().toString().equals("")) {
                note1Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher coin2listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
    private final TextWatcher cent25listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!cent25edit.getText().toString().equals("")){
                cent25Amt = cent25edit.getText().toString();
                System.out.println("cent25listener: " + cent25edit.getText().toString());
                calcTotal();
            }
            if (cent25edit.getText().toString().equals("")) {
                cent25Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher cent20listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!cent20edit.getText().toString().equals("")){
                cent20Amt = cent20edit.getText().toString();
                System.out.println("cent20listener: " + cent20edit.getText().toString());
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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_settings:
                openSettings();
                return true;
            case R.id.action_clear_all:
                clearAll();
                return true;
            case R.id.action_share_via_SMS:
                sendTotals();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // End switch
    }

    // This checks if the total or title is empty. Returns true if they are empty
    public boolean checkIfEmpty() {
        if (total_title_.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, R.string.check_if_empty_title_error, Toast.LENGTH_SHORT).show();
            return true;
        }
        if(toDouble(getTotal()) < 0.01){
            Toast.makeText(MainActivity.this, R.string.check_if_empty_overlay_errror, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
   // Same as above minus the toasts
    public boolean fieldsAreEmpty() {
        if (total_title_.getText().toString().equals("")) {
            return true;
        }
        if(toDouble(getTotal()) < 0.01){
            return true;
        }
        return false;
    }

    // ----------------------------------- MENU SETTINGS ------------------------------
    // Method brings the user to the saves activity, showing them their saved data.
    public void showsaves() {
        Intent intent = new Intent(MainActivity.this, ShowSaves.class);
        startActivity(intent);
    }

    // Method opens the settings from the menu in the main activity.
    public void openSettings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    // Clears all the data that the user has input.
    public void clearAll() {
        total_title_.setText("");
        note10000edit.setText("");
        note5000edit.setText("");
        note2000edit.setText("");
        note1000edit.setText("");
        note500edit.setText("");
        note200edit.setText("");
        note100edit.setText("");
        note50edit.setText("");
        note20edit.setText("");
        note10edit.setText("");
        note5edit.setText("");
        note1edit.setText("");
        coin2edit.setText("");
        coin1edit.setText("");
        cent50edit.setText("");
        cent25edit.setText("");
        cent20edit.setText("");
        cent10edit.setText("");
        cent5edit.setText("");
        cent2edit.setText("");
        cent1edit.setText("");
        additionaledit.setText("");

        System.out.println("clearAll: All inputs cleared");
    }

    // Method to allow the user to share the data via whatever method they would like to.
    public void sendTotals() {
        boolean isEmpty = checkIfEmpty();
        if(!isEmpty) {
            StringBuilder message = new StringBuilder();
            message.append(overlay.getText().toString() + "\n" + total_title_.getText().toString() + "\n" + getFormatDate());

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(intent.EXTRA_TEXT, message.toString());
            intent.setType("text/plain");
            startActivity(intent);
        }
    }

    // ------------------------ CALCULATION METHODS ---------------------------------------------------------------------------------
    public static double calcTotal() {
        double note10000 = toInt(note10000Amt) * 10000.0;
        double note5000 = toInt(note5000Amt) * 5000.0;
        double note2000 = toInt(note2000Amt) * 2000.0;
        double note1000 = toInt(note1000Amt) * 1000.0;
        double note500 = toInt(note500Amt) * 500.0;
        double note200 = toInt(note200Amt) * 200.0;
        double note100 = toInt(note100Amt) * 100.0;
        double note50 = toInt(note50Amt) * 50.0;
        double note20 = toInt(note20Amt) * 20.0;
        double note10 = toInt(note10Amt) * 10.0;
        double note5 = toInt(note5Amt) * 5.0;
        double note1 = toInt(note1Amt) * 1.0;
        double euro2 = toInt(coin2Amt) * 2.0;
        double euro1 = toInt(coin1Amt) * 1.0;
        double cent50 = toInt(cent50Amt) * 0.5;
        double cent25 = toInt(cent25Amt) * 0.25;
        double cent20 = toInt(cent20Amt) * 0.2;
        double cent10 = toInt(cent10Amt) * 0.1;
        double cent5 = toInt(cent5Amt) * 0.05;
        double cent2 = toInt(cent2Amt) * 0.02;
        double cent1 = toInt(cent1Amt) * 0.01;
        double total = 0;

        double total_numb = note10000
                + note5000
                + note2000
                + note1000
                + note500
                + note200
                + note100
                + note50
                + note20
                + note10
                + note5
                + note1
                + euro2
                + euro1
                + cent50
                + cent25
                + cent20
                + cent10
                + cent5
                + cent2
                + cent1
                + toInt(additionalCoins);

        System.out.println("Done with calculating total");

        DecimalFormat formatter = new DecimalFormat("#,###.00");
        setTotal(formatter.format(total_numb));

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
    public static int toInt(String number) { return Integer.parseInt(number); }

    // Convert String into double
    public static double toDouble(String number){
        System.out.println("toDouble: " + number);
        String num = number.replace(",", "");
        double value = Double.parseDouble(num);
        System.out.println("toDouble: " + value);
        return value;
    }

    // Sets the total
    public static void setTotal(String incoming_total) {
        total = incoming_total;
        System.out.println("setTotal: " + total + " - total");
    }
    public static String getTotal() { return total; }

    // ---------------- CURRENCY METHODS --------------------------------------------

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void make_a_toast(){
        String curr;
        switch (currentCurrency) {
            case "EUR":
                curr = "Euro";
                break;
            case "RUB":
                curr = "Roubles";
                break;
            case "USD":
                curr = "Dollars";
                break;
            case "GBP":
                curr = "Pounds";
                break;
            case "RON":
                curr = "Leu";
                break;
            case "CSK":
                curr = "Koruna";
                break;
            case "ISK":
                curr = "Korona";
                break;
            default:
                curr = "Monies";
                break;
        } // End switch

        String Speak = (getTotal()) + curr;
        System.out.println("make_a_toast (TTS): " + Speak);


        if(Build.VERSION.RELEASE.startsWith("5")) {
            t1.speak(Speak, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            t1.speak(Speak, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public static final String PREFS_NAME = "PREFS";
    public static final String PREFS_KEY = "PREFS_String";

    public static String currentCurrency;

    public String getCurrency() {
        System.out.println("getCurrency: Starting..");
        SharedPreferences settings;
        String text;
        settings = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        text = settings.getString(PREFS_KEY, "EUR");

        currentCurrency = text;
        System.out.println("getCurrency: finished.");
        return text;
    }

    public static final String EUR = "\u20AC";
    public static final String USD_ = "\u0024";
    public static final String USD_CENT = "\u00A2";
    public static final String GBP = "\u00a3";
    public static final String RUB = "\u20BD";
    public static final String RUB_COIN = "kopeks";
    public static final String ISK = "kr";

    // This sets the right hints depending on the currency that is currently selected by the user.
    public void hintChecks() {
        System.out.println("hintChecked: " + getCurrency());

        if(currentCurrency.equals("USD")){
            // Set hints
            note100edit.setHint(USD_ + "100");
            note50edit.setHint(USD_ + "50");
            note20edit.setHint(USD_ + "20");
            note10edit.setHint(USD_ + "10");
            note5edit.setHint(USD_ + "5");
            note1edit.setHint(USD_ + "1");
            cent25edit.setHint("25" + USD_CENT);
            cent10edit.setHint("10" + USD_CENT);
            cent5edit.setHint("5" + USD_CENT);
            cent1edit.setHint("1" + USD_CENT);
            additionaledit.setHint(getResources().getString(R.string.additions) + " " + USD_);

            // Set visibilities
            note10000edit.setVisibility(View.GONE);
            note5000edit.setVisibility(View.GONE);
            note2000edit.setVisibility(View.GONE);
            note1000edit.setVisibility(View.GONE);
            note500edit.setVisibility(View.GONE);
            note200edit.setVisibility(View.GONE);
            note100edit.setVisibility(View.VISIBLE);
            note50edit.setVisibility(View.VISIBLE);
            note20edit.setVisibility(View.VISIBLE);
            note10edit.setVisibility(View.VISIBLE);
            note5edit.setVisibility(View.VISIBLE);
            note1edit.setVisibility(View.VISIBLE);
            coin2edit.setVisibility(View.GONE);
            coin1edit.setVisibility(View.GONE);
            cent50edit.setVisibility(View.GONE);
            cent25edit.setVisibility(View.VISIBLE);
            cent20edit.setVisibility(View.GONE);
            cent10edit.setVisibility(View.VISIBLE);
            cent5edit.setVisibility(View.VISIBLE);
            cent2edit.setVisibility(View.GONE);
            cent1edit.setVisibility(View.VISIBLE);
        } // End USD
        else if (currentCurrency.equals("EUR")){
            // Set hints
            note100edit.setHint(EUR + "100");
            note50edit.setHint(EUR + "50");
            note20edit.setHint(EUR + "20");
            note10edit.setHint(EUR + "10");
            note5edit.setHint(EUR + "5");
            coin2edit.setHint(EUR + "2");
            coin1edit.setHint(EUR + "1");
            cent50edit.setHint("50c");
            cent20edit.setHint("20c");
            cent10edit.setHint("10c");
            cent5edit.setHint("5c");
            cent2edit.setHint("2c");
            cent1edit.setHint("1c");
            additionaledit.setHint(getResources().getString(R.string.additions) + " " + EUR);

            // Set visibilities
            note10000edit.setVisibility(View.GONE);
            note5000edit.setVisibility(View.GONE);
            note2000edit.setVisibility(View.GONE);
            note1000edit.setVisibility(View.GONE);
            note500edit.setVisibility(View.GONE);
            note200edit.setVisibility(View.GONE);
            note100edit.setVisibility(View.VISIBLE);
            note50edit.setVisibility(View.VISIBLE);
            note20edit.setVisibility(View.VISIBLE);
            note10edit.setVisibility(View.VISIBLE);
            note5edit.setVisibility(View.VISIBLE);
            note1edit.setVisibility(View.GONE);
            coin2edit.setVisibility(View.VISIBLE);
            coin1edit.setVisibility(View.VISIBLE);
            cent50edit.setVisibility(View.VISIBLE);
            cent25edit.setVisibility(View.GONE);
            cent20edit.setVisibility(View.VISIBLE);
            cent10edit.setVisibility(View.VISIBLE);
            cent5edit.setVisibility(View.VISIBLE);
            cent2edit.setVisibility(View.VISIBLE);
            cent1edit.setVisibility(View.VISIBLE);
            additionaledit.setVisibility(View.VISIBLE);
        } // End EUR
        else if (currentCurrency.equals("GBP")){
            // Set hints
            note50edit.setHint(GBP + "50");
            note20edit.setHint(GBP + "20");
            note10edit.setHint(GBP + "10");
            note5edit.setHint(GBP + "5");
            coin2edit.setHint(GBP + "2");
            coin1edit.setHint(GBP + "1");
            cent50edit.setHint("50p");
            cent20edit.setHint("20p");
            cent10edit.setHint("10p");
            cent5edit.setHint("5p");
            cent2edit.setHint("2p");
            cent1edit.setHint("1p");
            additionaledit.setHint(getResources().getString(R.string.additions) + " " + GBP);

            // Set visibilities
            note10000edit.setVisibility(View.GONE);
            note5000edit.setVisibility(View.GONE);
            note2000edit.setVisibility(View.GONE);
            note1000edit.setVisibility(View.GONE);
            note500edit.setVisibility(View.GONE);
            note200edit.setVisibility(View.GONE);
            note100edit.setVisibility(View.GONE);
            note50edit.setVisibility(View.VISIBLE);
            note20edit.setVisibility(View.VISIBLE);
            note10edit.setVisibility(View.VISIBLE);
            note5edit.setVisibility(View.VISIBLE);
            note1edit.setVisibility(View.GONE);
            coin2edit.setVisibility(View.VISIBLE);
            coin1edit.setVisibility(View.VISIBLE);
            cent50edit.setVisibility(View.VISIBLE);
            cent25edit.setVisibility(View.GONE);
            cent20edit.setVisibility(View.VISIBLE);
            cent10edit.setVisibility(View.VISIBLE);
            cent5edit.setVisibility(View.VISIBLE);
            cent2edit.setVisibility(View.VISIBLE);
            cent1edit.setVisibility(View.VISIBLE);
            additionaledit.setVisibility(View.VISIBLE);
        } // End GBP
        else if (currentCurrency.equals("RUB")){
            note5000edit.setHint(RUB + "5,000 ");
            note1000edit.setHint(RUB + "1,000 ");
            note500edit.setHint(RUB + "500 ");
            note100edit.setHint(RUB + "100 ");
            note50edit.setHint(RUB + "50 ");
            note5edit.setHint(RUB + "5 ");
            coin2edit.setHint(RUB + "2 ");
            coin1edit.setHint(RUB + "1 ");
            cent50edit.setHint("50 " + RUB_COIN);
            cent10edit.setHint("10 " + RUB_COIN);
            cent5edit.setHint("5 " + RUB_COIN);
            cent1edit.setHint("1 " + RUB_COIN);
            additionaledit.setHint(getResources().getString(R.string.additions) + " " + RUB);

            // Set visibilities
            note10000edit.setVisibility(View.GONE);
            note5000edit.setVisibility(View.VISIBLE);
            note2000edit.setVisibility(View.GONE);
            note1000edit.setVisibility(View.VISIBLE);
            note500edit.setVisibility(View.VISIBLE);
            note200edit.setVisibility(View.GONE);
            note100edit.setVisibility(View.VISIBLE);
            note50edit.setVisibility(View.VISIBLE);
            note20edit.setVisibility(View.GONE);
            note10edit.setVisibility(View.GONE);
            note5edit.setVisibility(View.VISIBLE);
            note1edit.setVisibility(View.GONE);
            coin2edit.setVisibility(View.VISIBLE);
            coin1edit.setVisibility(View.VISIBLE);
            cent50edit.setVisibility(View.VISIBLE);
            cent25edit.setVisibility(View.GONE);
            cent20edit.setVisibility(View.GONE);
            cent10edit.setVisibility(View.VISIBLE);
            cent5edit.setVisibility(View.VISIBLE);
            cent2edit.setVisibility(View.GONE);
            cent1edit.setVisibility(View.VISIBLE);
            additionaledit.setVisibility(View.VISIBLE);
        } // eND rub
        else if (currentCurrency.equals("ISK")){
            note10000edit.setHint("10,000" + ISK);
            note5000edit.setHint("5,000" + ISK);
            note2000edit.setHint("2,000" + ISK);
            note1000edit.setHint("1,000" + ISK);
            note500edit.setHint("500" + ISK);
            note100edit.setHint("100" + ISK);
            note50edit.setHint("50" + ISK);
            note10edit.setHint("10" + ISK);
            note5edit.setHint("5" + ISK);
            note1edit.setHint("1" + ISK);
            additionaledit.setHint(getResources().getString(R.string.additions) + " " + ISK);

            // Set visibilities
            note10000edit.setVisibility(View.VISIBLE);
            note5000edit.setVisibility(View.VISIBLE);
            note2000edit.setVisibility(View.VISIBLE);
            note1000edit.setVisibility(View.VISIBLE);
            note500edit.setVisibility(View.VISIBLE);
            note200edit.setVisibility(View.GONE);
            note100edit.setVisibility(View.VISIBLE);
            note50edit.setVisibility(View.VISIBLE);
            note20edit.setVisibility(View.GONE);
            note10edit.setVisibility(View.VISIBLE);
            note5edit.setVisibility(View.VISIBLE);
            note1edit.setVisibility(View.VISIBLE);
            coin2edit.setVisibility(View.GONE);
            coin1edit.setVisibility(View.GONE);
            cent50edit.setVisibility(View.GONE);
            cent25edit.setVisibility(View.GONE);
            cent20edit.setVisibility(View.GONE);
            cent10edit.setVisibility(View.GONE);
            cent5edit.setVisibility(View.GONE);
            cent2edit.setVisibility(View.GONE);
            cent1edit.setVisibility(View.GONE);
            additionaledit.setVisibility(View.VISIBLE);
        } // end ISK
    }
}

