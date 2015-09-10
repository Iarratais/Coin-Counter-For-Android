package com.karl.android.coincounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Savepoint;
import java.util.Arrays;


public class SettingsActivity extends AppCompatActivity {

    // Location and Currency settings
    public Button btncurr;
    public Button btnlangSel;
    public Switch includeLocation;

    // Other
    public Switch google_Analytics;

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
        getSupportActionBar().setTitle(R.string.action_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialise the buttons for "location and currency"
        btncurr = (Button) findViewById(R.id.button2);
        btnlangSel = (Button) findViewById(R.id.button3);
        //includeLocation = (Switch) findViewById(R.id.Location);

        btncurr.setText(getCurrency());

        btncurr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeCurrency();
                    }
                });
        btnlangSel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SettingsActivity.this, R.string.feature_currently_unavailable, Toast.LENGTH_SHORT).show();
                    }
                }
        );

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

        // Initialise buttons for "Other"
        google_Analytics = (Switch) findViewById(R.id.switch1);

        google_Analytics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    analyticsSwitch(true);
                } else {
                    analyticsSwitch(false);
                }
            }
        });

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // Disabled because there is no need for settings here.
        /*if (id == R.id.action_settings) {
            return true;
        } */

        return super.onOptionsItemSelected(item);
    }

    /*
    public void includeLocationOn() {
        Toast.makeText(SettingsActivity.this, "includeLocationOn", Toast.LENGTH_SHORT).show();
    }

    public void includeLocationOff() {
        Toast.makeText(SettingsActivity.this, "includeLocationOff", Toast.LENGTH_SHORT).show();
    } */

    @Override
    public void onDestroy() {
        finish();
        overridePendingTransition(R.anim.slideout, R.anim.slidein);
        super.onDestroy();
    }

    public void changeCurrency() {
        final String[] currencies = {"EUR", "ISK", "RUB", "USD", "GBP", "JPY", "KRW", "BGN", "CAD", "NZD", "AUD", "DKK", "SEK", "NOK", "RON", "CZK", "ARS", "BRL", "CHF", "ALL", "ILS", "HKD", "RSD", "BYR"};
        Arrays.sort(currencies);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.currency_title)
                .setItems(currencies, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                saveCurrency(currencies[0]);
                                break;
                            case 1:
                                saveCurrency(currencies[1]);
                                break;
                            case 2:
                                saveCurrency(currencies[2]);
                                break;
                            case 3:
                                saveCurrency(currencies[3]);
                                break;
                            case 4:
                                saveCurrency(currencies[4]);
                                break;
                            case 5:
                                saveCurrency(currencies[5]);
                                break;
                            case 6:
                                saveCurrency(currencies[6]);
                                break;
                            case 7:
                                saveCurrency(currencies[7]);
                                break;
                            case 8:
                                saveCurrency(currencies[8]);
                                break;
                            case 9:
                                saveCurrency(currencies[9]);
                                break;
                            case 10:
                                saveCurrency(currencies[10]);
                                break;
                            case 11:
                                saveCurrency(currencies[11]);
                                break;
                            case 12:
                                saveCurrency(currencies[12]);
                                break;
                            case 13:
                                saveCurrency(currencies[13]);
                                break;
                            case 14:
                                saveCurrency(currencies[14]);
                                break;
                            case 15:
                                saveCurrency(currencies[15]);
                                break;
                            case 16:
                                saveCurrency(currencies[16]);
                                break;
                            case 17:
                                saveCurrency(currencies[17]);
                                break;
                            case 18:
                                saveCurrency(currencies[18]);
                                break;
                            case 19:
                                saveCurrency(currencies[19]);
                                break;
                            case 20:
                                saveCurrency(currencies[20]);
                                break;
                            case 21:
                                saveCurrency(currencies[21]);
                                break;
                            case 22:
                                saveCurrency(currencies[22]);
                                break;
                            case 23:
                                saveCurrency(currencies[23]);
                                break;
                            case 24:
                                saveCurrency(currencies[24]);
                                break;
                        }
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
        editor.commit();


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

    public void analyticsSwitch(boolean isChecked) {
        // Let the user know
        if(isChecked) {
            showToast(getString(R.string.google_analytics_activated));
        } else {
            showToast(getString(R.string.google_analytics_deactivated));
        }

        SharedPreferences analytics = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = analytics.edit();
        editor.putBoolean("analytics", isChecked);
        editor.commit();
    }

    public void about() {
        String title = getString(R.string.about_title);
        String message = getString(R.string.description);;
        showMessage(title, message);
    }

    public void howTo() {
        String title = getString(R.string.howto_title);
        String message = getString(R.string.howto);
        showMessage(title, message);
    }

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
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        } catch (Exception e) {
            Toast.makeText(SettingsActivity.this, R.string.common_google_play_services_network_error_text, Toast.LENGTH_SHORT).show();
        }
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

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void showToast(String message) {
        Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
