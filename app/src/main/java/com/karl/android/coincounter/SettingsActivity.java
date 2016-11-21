package com.karl.android.coincounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

import data.sql.MySQLiteHelper;


public class SettingsActivity extends AppCompatActivity {

    // Location and Currency settings
    public Button btncurr;
    //public Button btnlangSel;

    // About
    public Button btnHowTo;
    public Button btnAbout;
    public Button btnSendFeedback;
    public Button btnRate;
    public Button btnFacebook;
    public Button btnWordpress;

    // Danger Zone
    public Button btnClearAllData;

    MySQLiteHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Toolbar toolbar    = (Toolbar) findViewById (R.id.tool_bar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle(R.string.action_settings);
        } catch (NullPointerException e) {
            throw new NullPointerException("Error setting up action bar: " + e);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialise the buttons for "location and currency"
        btncurr = (Button) findViewById(R.id.button2);
        //btnlangSel = (Button) findViewById(R.id.button3);
        //includeLocation = (Switch) findViewById(R.id.Location);

        btncurr.setText(getCurrency());

        btncurr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeCurrency();
                    }
                });
        //btnlangSel.setOnClickListener(
        //        new View.OnClickListener() {
        //            @Override
        //    public void onClick(View v) {
        //                // Method not finished yet
        //                changeLanguage();
        //            }
        //        }
        //);

        // Temporarily disabled, not a valid option for the time being.
        /*includeLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    includeLocationOn();
                } else {
                    includeLocationOff();
                }
            }
        }); */

        // Initialise buttons for "About"
        btnHowTo = (Button) findViewById(R.id.button4);
        btnAbout = (Button) findViewById(R.id.button5);
        btnSendFeedback = (Button) findViewById(R.id.button6);
        btnRate = (Button) findViewById(R.id.button8);
        btnFacebook = (Button) findViewById(R.id.button9);
        btnWordpress = (Button) findViewById(R.id.button10);

        btnHowTo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        howTo();
                    }
                }
        );
        btnAbout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        about();
                    }
                }
        );
        btnSendFeedback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendFeedback();
                    }
                }
        );
        btnRate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rateApplication();
                    }
                }
        );
        btnFacebook.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToFacebook();
                    }
                }
        );
        btnWordpress.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToWordpress();
                    }
                }
        );

        // Initialise buttons for "Danger Zone"
        btnClearAllData = (Button) findViewById(R.id.button7);

        btnClearAllData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAllData();
                    }
                }
        );

        myDB = new MySQLiteHelper(this);

        //btnlangSel.setText(getLanguage());
    }

    @Override
    public void onDestroy() {
        finish();
        overridePendingTransition(R.anim.slideout, R.anim.slidein);
        super.onDestroy();
    }

    public void changeLanguage() {
        final String[] languages = {
                "No languages anymore"
        };
        Arrays.sort(languages);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.language_title))
                .setItems(languages, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveLanguage(languages[which]);
                        Toast.makeText(SettingsActivity.this, getString(R.string.language_title) + ": " + languages[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User has cancelled the action, returns to the previous screen
                    }
                })
                .show();

    }

    public void saveLanguage(String language) {
        SharedPreferences curr = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = curr.edit();
        editor.putString("language", language);
        editor.apply();

        //btnlangSel.setText(getLanguage());
    }

    public String getLanguage() {
        SharedPreferences lang = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = getString(R.string.english);
        String language = "";
        language = lang.getString("language", language);
        if (language.equals("")) {
            language = defaultValue;
        }
        return language;
    }

    public void changeCurrency() {
        final String[] currencies = {"EUR", "ISK", "RUB", "USD", "GBP", "JPY", "KRW", "BGN", "CAD", "NZD", "AUD", "DKK", "SEK",
                "NOK", "RON", "CZK", "ARS", "BRL", "CHF", "ALL", "ILS", "HKD", "RSD", "BYR", "UAH", "IKR", "MNT", "KZT", "THB",
                "ZAR", "PEN"};
        Arrays.sort(currencies);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.currency_title)
                .setItems(currencies, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveCurrency(currencies[which]);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User has cancelled the action, returns to the previous screen
                    }
                })
                .show();
    }

    public void saveCurrency(String currency) {
        // Let the user know
        showToast(getString(R.string.changed_curr) + " " + currency);

        SharedPreferences curr = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = curr.edit();
        editor.putString("currency", currency);
        editor.apply();

        btncurr.setText(getCurrency());
    }

    public String getCurrency() {
        SharedPreferences curr = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = "EUR";
        String currency = "";
        currency = curr.getString("currency", currency);
        if (currency.equals("")) {
            currency = defaultValue;
        }
        return currency;
    }

    public void about() {showMessage(getString(R.string.about_title), getString(R.string.description), true);}

    public void howTo() {showMessage(getString(R.string.howto_title), getString(R.string.howto), true);}

    public void sendFeedback() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"coincountr@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Coin Countr feedback");
        try {
            startActivity(Intent.createChooser(i, getString(R.string.choose_an_email_application)));
        } catch (Exception e) {
            Toast.makeText(SettingsActivity.this, R.string.no_email_clients_installed, Toast.LENGTH_SHORT).show();
        }
    }

    public void goToFacebook() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.you_are_exiting_the_application)
                .setTitle("")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/CoinCountr")));
                        } catch (Exception e) {
                            Toast.makeText(SettingsActivity.this, R.string.error + " " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User has cancelled the action, returns to the previous screen
                    }
                }).show();
    }

    public void goToWordpress() {
        final String url = "https://goo.gl/ClW5em";

        new AlertDialog.Builder(this)
                .setMessage(R.string.you_are_exiting_the_application)
                .setTitle("")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        } catch (Exception e) {
                            Toast.makeText(SettingsActivity.this, R.string.error + " " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User has cancelled the action, returns to the previous screen
                    }
                }).show();

    }
    public void rateApplication() {
        final String package_name = this.getPackageName();
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.you_are_exiting_the_application))
                .setTitle("")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + package_name)));
                        } catch (Exception e) {
                            Toast.makeText(SettingsActivity.this, "Could not coneect to play store", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void clearAllData() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.are_you_sure_you_want_to_delete)
                .setTitle(R.string.delete)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myDB.deleteAll();
                        Toast.makeText(SettingsActivity.this, R.string.done, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User has cancelled the action, returns to the previous screen
                    }
                }).show();
    }

    public void showMessage(String title, String message, Boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(cancelable);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void showToast(String message) {
        Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
