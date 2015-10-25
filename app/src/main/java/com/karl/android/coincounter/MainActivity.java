// Copyright Karl Jones

package com.karl.android.coincounter;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.res.Configuration;
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
import android.view.WindowManager;
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

public class MainActivity extends ActionBarActivity {
    MySQLiteHelper myDB;

    public static TextToSpeech t1;

    public static String total_title = " ";
    public static String note200000Amt = "0";
    public static String note100000Amt = "0";
    public static String note50000Amt = "0";
    public static String note20000Amt = "0";
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
    public static String comment = " ";

    // All edittexts
    private EditText total_title_, note200000edit, note100000edit, note50000edit, note10000edit, note20000edit, note5000edit, note2000edit, note1000edit, note500edit, note200edit, note100edit, note50edit, note20edit, note10edit, note5edit, note1edit;
    private EditText coin2edit, coin1edit;
    private EditText cent50edit, cent25edit, cent20edit, cent10edit, cent5edit, cent2edit, cent1edit;
    private EditText additionaledit, commentedit;
    public static TextView overlay;

    public Button btnadd;
    public Button btnsave;

    public AdView adView;

    SharedPreferences curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle(R.string.app_name);
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }

        curr = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        myDB            =   new MySQLiteHelper(this);

        overlay         =   (TextView) findViewById(R.id.OverlayTotal);
        note200000edit  =   (EditText) findViewById(R.id.notes200000);
        note100000edit  =   (EditText) findViewById(R.id.notes100000);
        note50000edit   =   (EditText) findViewById(R.id.notes50000);
        note20000edit   =   (EditText) findViewById(R.id.notes20000);
        note10000edit   =   (EditText) findViewById(R.id.notes10000);
        note5000edit    =   (EditText) findViewById(R.id.notes5000);
        note2000edit    =   (EditText) findViewById(R.id.notes2000);
        note1000edit    =   (EditText) findViewById(R.id.notes1000);
        note500edit     =   (EditText) findViewById(R.id.notes500);
        note200edit     =   (EditText) findViewById(R.id.notes200);
        note100edit     =   (EditText) findViewById(R.id.notes100);
        note50edit      =   (EditText) findViewById(R.id.notes50);
        note20edit      =   (EditText) findViewById(R.id.notes20);
        note10edit      =   (EditText) findViewById(R.id.notes10);
        note5edit       =   (EditText) findViewById(R.id.notes5);
        note1edit       =   (EditText) findViewById(R.id.notes1);
        coin2edit       =   (EditText) findViewById(R.id.coins2);
        coin1edit       =   (EditText) findViewById(R.id.coins1);
        cent50edit      =   (EditText) findViewById(R.id.cent50);
        cent25edit      =   (EditText) findViewById(R.id.cent25);
        cent20edit      =   (EditText) findViewById(R.id.cent20);
        cent10edit      =   (EditText) findViewById(R.id.cent10);
        cent5edit       =   (EditText) findViewById(R.id.cent5);
        cent2edit       =   (EditText) findViewById(R.id.cent2);
        cent1edit       =   (EditText) findViewById(R.id.cent1);
        additionaledit  =   (EditText) findViewById(R.id.extraAdditions);
        commentedit     =   (EditText) findViewById(R.id.comment);
        total_title_    =   (EditText) findViewById(R.id.total_title);

        btnadd          =   (Button) findViewById(R.id.button);
        btnsave         =   (Button) findViewById(R.id.btnSaves);

        adView          =   (AdView) findViewById(R.id.adView);

        // Set text watchers
        note200000edit.addTextChangedListener(note200000listener);
        note100000edit.addTextChangedListener(note100000listener);
        note50000edit.addTextChangedListener(note50000listener);
        note20000edit.addTextChangedListener(note20000listener);
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
        commentedit.addTextChangedListener(commentlistener);
        total_title_.addTextChangedListener(total_title_listener);

        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make_a_toast();
            }
        });

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("BB06F90FCB63535EB4CCF8EF55E116D6").build();

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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        clearAll();
        setCurrencyBoxes();
    } // End onCreate()

    public void AddData() {
        boolean isEmpty = checkIfEmpty();
        if (!isEmpty) {
            boolean isInserted = myDB.insertData(getFormatDate(), total_title_.getText().toString(), overlay.getText().toString(), commentedit.getText().toString());
            if (isInserted) {Toast.makeText(MainActivity.this, R.string.add_data_success, Toast.LENGTH_SHORT).show();}
            else {Toast.makeText(MainActivity.this, R.string.add_data_error, Toast.LENGTH_SHORT).show();}
        }
    }

    // Shows an alert to the user
    public void showMessage(String title, String message, Boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(cancelable).setTitle(title).setMessage(message).show();
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

        if (adView != null) {adView.resume();}

        setCurrencyBoxes();
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    // This puts the date in the rght format when the total is saved, currenctly only works with english locale
    public String getFormatDate() {
        Date now = new Date();
        return new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(now);
    }

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
                new AlertDialog.Builder(this)
                        .setMessage(R.string.are_you_sure_you_want_to_delete)
                        .setTitle("")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                clearAll();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User has cancelled the action, does not change the activity
                            }
                        }).show();
                return true;
            case R.id.action_share_via_SMS:
                sendTotals();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // End switch
    }

    // This checks if the total or title is empty.
    // Returns true if they are empty
    public boolean checkIfEmpty() {
        if (total_title_.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, R.string.check_if_empty_title_error, Toast.LENGTH_SHORT).show();
            return true;
        } else if (toDouble(getTotal()) < 0.01) {
            Toast.makeText(MainActivity.this, R.string.check_if_empty_overlay_errror, Toast.LENGTH_SHORT).show();
            return true;
        } else {return false;}
    }

    // Same as above minus the toasts.
    // Returns true if fields are empty
    public boolean fieldsAreEmpty() {
        if (total_title_.getText().toString().equals("")) {return true;}
        if (toDouble(getTotal()) < 0.01) {return true;}
        return false;
    }

    // ----------------------------------- MENU SETTINGS ------------------------------
    // Method brings the user to the saves activity, showing them their saved data.
    public void showsaves() {
        if (!fieldsAreEmpty()) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.are_you_sure_data_loss_inevidable)
                    .setTitle("")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this, ShowSaves.class);
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User has cancelled the action, does not change the activity
                        }
                    }).show();
        } else {
            Intent intent = new Intent(MainActivity.this, ShowSaves.class);
            finish();
            startActivity(intent);
            overridePendingTransition(R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_shrink_fade_out_from_bottom);
        }
    }

    // Method opens the settings from the menu in the main activity.
    public void openSettings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    // Clears all the data that the user has input.
    public void clearAll() {
        total_title_.setText("");
        note200000edit.setText("");
        note100000edit.setText("");
        note50000edit.setText("");
        note20000edit.setText("");
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
        commentedit.setText("");
    }

    // Method to allow the user to share the data via whatever method they would like to.
    public void sendTotals() {
        boolean isEmpty = checkIfEmpty();
        if (!isEmpty) {
            String message = overlay.getText().toString() + "\n" + total_title_.getText().toString() + "\n" + getFormatDate() + "\n" + commentedit.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(intent.EXTRA_TEXT, message);
            intent.setType("text/plain");
            startActivity(intent);
        }
    }

    // ------------------------ CALCULATION METHODS ---------------------------------------------------------------------------------
    public double calcTotal() {
        double note200000 = toInt(note200000Amt) * 200000.0;
        double note100000 = toInt(note100000Amt) * 100000.0;
        double note50000 = toInt(note50000Amt) * 50000.0;
        double note20000 = toInt(note20000Amt) * 20000.0;
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

        double total_numb = note100000 + note200000 + note50000 + note20000 + note10000 + note5000 + note2000 + note1000 + note500 + note200 + note100 + note50
                + note20 + note10 + note5 + note1 + euro2 + euro1 + cent50 + cent25 + cent20 + cent10 + cent5 + cent2 + cent1 + toInt(additionalCoins);

        DecimalFormat formatter = new DecimalFormat("#,###.00");
        setTotal(formatter.format(total_numb));

        if (toDouble(getTotal()) < 0.01) {overlay.setVisibility(TextView.GONE);}
        else {overlay.setText(getTotal());overlay.setVisibility(TextView.VISIBLE);}

        return total;
    }

    // Convert String to Integer
    public static int toInt(String number) {return Integer.parseInt(number);}

    // Convert String into double
    public static double toDouble(String number) {return Double.parseDouble(number.replace(",", ""));}

    // Sets the total
    public static void setTotal(String incoming_total) {total = incoming_total;}

    public static String getTotal() {return total;}

    // ---------------- CURRENCY METHODS --------------------------------------------
    // Speaks the total when clicked.
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void make_a_toast() {
        t1.setLanguage(Locale.getDefault());
        if (Build.VERSION.RELEASE.startsWith("5") || Build.VERSION.RELEASE.startsWith("6")) {t1.speak(getString(R.string.your_total_is) + (getTotal()), TextToSpeech.QUEUE_FLUSH, null, null);}
        else {t1.speak(getString(R.string.your_total_is) + (getTotal()), TextToSpeech.QUEUE_FLUSH, null);}
    }

    // Returns the three letter currency code.
    public String getCurrency() {
        SharedPreferences curr = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = "EUR";
        String currency = "";
        currency = curr.getString("currency", currency);
        if (currency.equals("")) {currency = defaultValue;}
        System.out.println("getCurrency sucessful!");
        return currency;
    }

    public void setCurrencyBoxes() {
        String currentCurrency;

        // Set the Strings for all of the currencies, replaces everything but the numbers, easier to paste from the excel spreadsheet
        String ALL = "0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t1\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String ARS = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t1\t0\t1\t1\t0\t1\n".replaceAll("[^0-9]", "");
        String AUD = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t1\t0\t0\n".replaceAll("[^0-9]", "");
        String BGN = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t1\t1\t1\n".replaceAll("[^0-9]", "");
        String BRL = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t1\t0\t1\t1\t0\t1\n".replaceAll("[^0-9]", "");
        String BYR = "1\t1\t1\t1\t1\t1\t0\t1\t1\t0\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String CAD = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t1\t0\t1\t1\t0\t0\n".replaceAll("[^0-9]", "");
        String CHF = "0\t0\t0\t0\t0\t0\t0\t1\t0\t1\t1\t1\t1\t1\t1\t0\t1\t1\t1\t1\t0\t1\t1\t0\t1\n".replaceAll("[^0-9]", "");
        String CZK = "0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t1\t0\t1\t1\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String DKK = "0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String EUR = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t1\t1\t1\n".replaceAll("[^0-9]", "");
        String GBP = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t1\t1\t1\n".replaceAll("[^0-9]", "");
        String HKD = "0\t0\t0\t0\t0\t0\t0\t1\t1\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String ILS = "0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t0\t1\t1\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String ISK = "0\t0\t0\t0\t1\t1\t0\t1\t1\t0\t1\t1\t0\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String JPY = "0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t0\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String KRW = "0\t0\t1\t0\t1\t1\t0\t1\t1\t0\t1\t1\t0\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String NOK = "0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t1\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String NZD = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String RON = "0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t0\t1\t1\t1\t0\t0\t1\t0\t0\t1\t1\t0\t1\n".replaceAll("[^0-9]", "");
        String RSD = "0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t0\t1\t1\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String RUB = "0\t0\t0\t0\t0\t1\t0\t1\t1\t0\t1\t1\t0\t0\t1\t0\t1\t1\t1\t0\t0\t1\t1\t0\t1\n".replaceAll("[^0-9]", "");
        String SEK = "0\t0\t0\t0\t0\t0\t0\t1\t1\t0\t1\t1\t1\t1\t1\t0\t0\t1\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String USD = "0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t0\t0\t0\t1\t0\t1\t1\t0\t1\n".replaceAll("[^0-9]", "");
        String UAH = "0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t0\t1\t1\t1\t1\n".replaceAll("[^0-9]", "");
        String IKR = "0\t0\t0\t0\t0\t0\t0\t1\t1\t0\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String MNT = "0\t0\t0\t1\t1\t1\t0\t1\t1\t0\t1\t1\t1\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String KZT = "0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t0\t0\t0\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String THB = "0\t0\t0\t0\t0\t0\t0\t1\t1\t0\t1\t1\t1\t1\t1\t0\t1\t1\t1\t1\t0\t0\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String ZAR = "0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String PEN = "0\t0\t0\t0\t0\t0\t0\t0\t0\t1\t1\t1\t1\t1\t1\t0\t1\t1\t1\t0\t1\t1\t0\t0\t0\n".replaceAll("[^0-9]", "");
        String def = "1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\t1\n".replaceAll("[^0-9]", "");

        // Compare the string to get the right code for the currency
        if (getCurrency().equals("ALL")) {currentCurrency = ALL;}
        else if (getCurrency().equals("ARS")) {currentCurrency = ARS;}
        else if (getCurrency().equals("AUD")) {currentCurrency = AUD;}
        else if (getCurrency().equals("BGN")) {currentCurrency = BGN;}
        else if (getCurrency().equals("BRL")) {currentCurrency = BRL;}
        else if (getCurrency().equals("BYR")) {currentCurrency = BYR;}
        else if (getCurrency().equals("CAD")) {currentCurrency = CAD;}
        else if (getCurrency().equals("CHF")) {currentCurrency = CHF;}
        else if (getCurrency().equals("CZK")) {currentCurrency = CZK;}
        else if (getCurrency().equals("DKK")) {currentCurrency = DKK;}
        else if (getCurrency().equals("EUR")) {currentCurrency = EUR;}
        else if (getCurrency().equals("GBP")) {currentCurrency = GBP;}
        else if (getCurrency().equals("HKD")) {currentCurrency = HKD;}
        else if (getCurrency().equals("ILS")) {currentCurrency = ILS;}
        else if (getCurrency().equals("ISK")) {currentCurrency = ISK;}
        else if (getCurrency().equals("JPY")) {currentCurrency = JPY;}
        else if (getCurrency().equals("KRW")) {currentCurrency = KRW;}
        else if (getCurrency().equals("NOK")) {currentCurrency = NOK;}
        else if (getCurrency().equals("NZD")) {currentCurrency = NZD;}
        else if (getCurrency().equals("RON")) {currentCurrency = RON;}
        else if (getCurrency().equals("RSD")) {currentCurrency = RSD;}
        else if (getCurrency().equals("RUB")) {currentCurrency = RUB;}
        else if (getCurrency().equals("SEK")) {currentCurrency = SEK;}
        else if (getCurrency().equals("USD")) {currentCurrency = USD;}
        else if (getCurrency().equals("UAH")) {currentCurrency = UAH;}
        else if (getCurrency().equals("IKR")) {currentCurrency = IKR;}
        else if (getCurrency().equals("MNT")) {currentCurrency = MNT;}
        else if (getCurrency().equals("KZT")) {currentCurrency = KZT;}
        else if (getCurrency().equals("THB")) {currentCurrency = THB;}
        else if (getCurrency().equals("ZAR")) {currentCurrency = ZAR;}
        else if (getCurrency().equals("PEN")) {currentCurrency = PEN;}
        else {currentCurrency = def;}

        // Methods to set the boxes to visible or gone, 11 boxes as of 21st Sept 2015

        // i is used so that the system can be expanded easily without having to change all of the numbers
        int i = 0;

        // 200,000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note200000edit.setVisibility(View.VISIBLE);} else {note200000edit.setVisibility(View.GONE);}
        i++;

        // 100,000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note100000edit.setVisibility(View.VISIBLE);} else {note100000edit.setVisibility(View.GONE);}
        i++;

        // 50,000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note50000edit.setVisibility(View.VISIBLE);} else {note50000edit.setVisibility(View.GONE);}
        i++;

        // 20 000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note20000edit.setVisibility(View.VISIBLE);} else {note20000edit.setVisibility(View.GONE);}
        i++;

        // 10 000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note10000edit.setVisibility(View.VISIBLE);} else {note10000edit.setVisibility(View.GONE);}
        i++;

        // 5 000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note5000edit.setVisibility(View.VISIBLE);} else {note5000edit.setVisibility(View.GONE);}
        i++;

        // 2 000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note2000edit.setVisibility(View.VISIBLE);} else {note2000edit.setVisibility(View.GONE);}
        i++;

        // 1 000
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note1000edit.setVisibility(View.VISIBLE);} else {note1000edit.setVisibility(View.GONE);}
        i++;

        // 500
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note500edit.setVisibility(View.VISIBLE);} else {note500edit.setVisibility(View.GONE);}
        i++;

        // 200
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note200edit.setVisibility(View.VISIBLE);} else {note200edit.setVisibility(View.GONE);}
        i++;

        // 100
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note100edit.setVisibility(View.VISIBLE);} else {note100edit.setVisibility(View.GONE);}
        i++;

        // 50
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note50edit.setVisibility(View.VISIBLE);} else {note50edit.setVisibility(View.GONE);}
        i++;

        // 20
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note20edit.setVisibility(View.VISIBLE);} else {note20edit.setVisibility(View.GONE);}
        i++;

        // 10
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note10edit.setVisibility(View.VISIBLE);} else {note10edit.setVisibility(View.GONE);}
        i++;

        // 5
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note5edit.setVisibility(View.VISIBLE);} else {note5edit.setVisibility(View.GONE);}
        i++;

        // 1 note
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {note1edit.setVisibility(View.VISIBLE);} else {note1edit.setVisibility(View.GONE);}
        i++;

        // 2
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {coin2edit.setVisibility(View.VISIBLE);} else {coin2edit.setVisibility(View.GONE);}
        i++;

        // 1
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {coin1edit.setVisibility(View.VISIBLE);} else {coin1edit.setVisibility(View.GONE);}
        i++;

        // .50
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {cent50edit.setVisibility(View.VISIBLE);} else {cent50edit.setVisibility(View.GONE);}
        i++;

        // .25
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {cent25edit.setVisibility(View.VISIBLE);} else {cent25edit.setVisibility(View.GONE);}
        i++;

        // .20
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {cent20edit.setVisibility(View.VISIBLE);} else {cent20edit.setVisibility(View.GONE);}
        i++;

        // .10
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {cent10edit.setVisibility(View.VISIBLE);} else {cent10edit.setVisibility(View.GONE);}
        i++;

        // .05
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {cent5edit.setVisibility(View.VISIBLE);} else {cent5edit.setVisibility(View.GONE);}
        i++;

        // .02
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {cent2edit.setVisibility(View.VISIBLE);} else {cent2edit.setVisibility(View.GONE);}
        i++;

        // .01
        if (Character.toString(currentCurrency.charAt(i)).equals("1")) {cent1edit.setVisibility(View.VISIBLE);} else {cent1edit.setVisibility(View.GONE);}

        System.out.println("setCurrencyBoxes() successful!");
    } // end setCurrencyBoxes()

    // --------------------------------------------------- TEXT WATCHERS --------------------------------------------------------------------
    private final TextWatcher total_title_listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!total_title_.getText().toString().equals("")) {
                total_title = total_title_.getText().toString();
            }
            if (total_title_.getText().toString().equals("")) {
                total_title = " ";
            }
        }
    };
    private final TextWatcher note200000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note200000edit.getText().toString().equals("")) {
                note200000Amt = note200000edit.getText().toString();
                calcTotal();
            }
            if (note200000edit.getText().toString().equals("")) {
                note200000Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note100000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note100000edit.getText().toString().equals("")) {
                note100000Amt = note100000edit.getText().toString();
                calcTotal();
            }
            if (note100000edit.getText().toString().equals("")) {
                note100000Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note50000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note50000edit.getText().toString().equals("")) {
                note50000Amt = note50000edit.getText().toString();
                calcTotal();
            }
            if (note50000edit.getText().toString().equals("")) {
                note50000Amt = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher note20000listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!note20000edit.getText().toString().equals("")) {
                note20000Amt = note20000edit.getText().toString();
                calcTotal();
            }
            if (note20000edit.getText().toString().equals("")) {
                note20000Amt = "0";
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
            if (!note10000edit.getText().toString().equals("")) {
                note10000Amt = note10000edit.getText().toString();
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
            if (!note5000edit.getText().toString().equals("")) {
                note5000Amt = note5000edit.getText().toString();
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
            if (!note2000edit.getText().toString().equals("")) {
                note2000Amt = note2000edit.getText().toString();
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
            if (!note1000edit.getText().toString().equals("")) {
                note1000Amt = note1000edit.getText().toString();
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
            if (!note500edit.getText().toString().equals("")) {
                note500Amt = note500edit.getText().toString();
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
            if (!note200edit.getText().toString().equals("")) {
                note200Amt = note200edit.getText().toString();
                calcTotal();
            }
            if (note200edit.getText().toString().equals("")) {
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
            if (!note100edit.getText().toString().equals("")) {
                note100Amt = note100edit.getText().toString();
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
            if (!note50edit.getText().toString().equals("")) {
                note50Amt = note50edit.getText().toString();
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
            if (!note20edit.getText().toString().equals("")) {
                note20Amt = note20edit.getText().toString();
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
            if (!note10edit.getText().toString().equals("")) {
                note10Amt = note10edit.getText().toString();
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
            if (!note5edit.getText().toString().equals("")) {
                note5Amt = note5edit.getText().toString();
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
            if (!note1edit.getText().toString().equals("")) {
                note1Amt = note1edit.getText().toString();
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
            if (!coin2edit.getText().toString().equals("")) {
                coin2Amt = coin2edit.getText().toString();
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
            if (!coin1edit.getText().toString().equals("")) {
                coin1Amt = coin1edit.getText().toString();
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
            if (!cent50edit.getText().toString().equals("")) {
                cent50Amt = cent50edit.getText().toString();
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
            if (!cent25edit.getText().toString().equals("")) {
                cent25Amt = cent25edit.getText().toString();
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
            if (!cent20edit.getText().toString().equals("")) {
                cent20Amt = cent20edit.getText().toString();
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
            if (!cent10edit.getText().toString().equals("")) {
                cent10Amt = cent10edit.getText().toString();
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
            if (!cent5edit.getText().toString().equals("")) {
                cent5Amt = cent5edit.getText().toString();
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
            if (!cent2edit.getText().toString().equals("")) {
                cent2Amt = cent2edit.getText().toString();
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
            if (!cent1edit.getText().toString().equals("")) {
                cent1Amt = cent1edit.getText().toString();
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
            if (!additionaledit.getText().toString().equals("")) {
                additionalCoins = additionaledit.getText().toString();
                calcTotal();
            }
            if (additionaledit.getText().toString().equals("")) {
                additionalCoins = "0";
                calcTotal();
            }
        }
    };
    private final TextWatcher commentlistener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (!commentedit.getText().toString().equals("")) {
                comment = commentedit.getText().toString();
            }
            if (commentedit.getText().toString().equals("")) {
                comment = " ";
            }
        }
    };
}