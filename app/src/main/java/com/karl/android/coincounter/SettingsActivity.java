package com.karl.android.coincounter;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
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
                    analyticsOn();
                } else {
                    analyticsOff();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void includeLocationOn() {
        Toast.makeText(SettingsActivity.this, "includeLocationOn", Toast.LENGTH_SHORT).show();
    }

    public void includeLocationOff() {
        Toast.makeText(SettingsActivity.this, "includeLocationOff", Toast.LENGTH_SHORT).show();
    }

    public void changeCurrency() {
        Toast.makeText(SettingsActivity.this, "changeCurrency", Toast.LENGTH_SHORT).show();
    }

    public void analyticsOn() {
        Toast.makeText(SettingsActivity.this, "AnalyticsOn", Toast.LENGTH_SHORT).show();
    }

    public void analyticsOff() {
        Toast.makeText(SettingsActivity.this, "AnalyticsOff", Toast.LENGTH_SHORT).show();
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
        new AlertDialog.Builder(this)
                .setMessage(R.string.you_are_exiting_the_application)
                .setTitle("")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/ClW5em")));
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
}
