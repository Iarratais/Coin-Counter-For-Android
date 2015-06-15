package com.karl.android.coincounter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;


public class LanguageSelectorActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private LinearLayout llo;

    private AdView adView;
    SharedPreferences lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selector);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.language_title);

        lang = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        llo = (LinearLayout) findViewById(R.id.llo2);

        ArrayList<String> strings = new ArrayList<String>();

        strings.add(getString(R.string.bulgarian));     // 1
        strings.add(getString(R.string.croatian));      // 2
        strings.add(getString(R.string.czech));         // 3
        strings.add(getString(R.string.danish));        // 4
        strings.add(getString(R.string.dutch));         // 5
        strings.add(getString(R.string.esperanto));
        strings.add(getString(R.string.english));       // 6
        strings.add(getString(R.string.estonian));      // 7
        strings.add(getString(R.string.finnish));       // 8
        strings.add(getString(R.string.french));        // 9
        strings.add(getString(R.string.german));        // 10
        strings.add(getString(R.string.greek));         // 11
        strings.add(getString(R.string.hungarian));     // 12
        strings.add(getString(R.string.irish));         // 13
        strings.add(getString(R.string.italian));       // 14
        strings.add(getString(R.string.latvian));       // 15
        strings.add(getString(R.string.lithuanian));    // 16
        strings.add(getString(R.string.maltese));       // 17
        strings.add(getString(R.string.polish));        // 18
        strings.add(getString(R.string.portuguese));    // 19
        strings.add(getString(R.string.romanian));      // 20
        strings.add(getString(R.string.russian));       // 21
        strings.add(getString(R.string.slovak));        // 22
        strings.add(getString(R.string.slovienian));    // 23
        strings.add(getString(R.string.spanish));       // 24
        strings.add(getString(R.string.swedish));       // 25
        strings.add(getString(R.string.ukrainian));     // 26

        ContextThemeWrapper newContext = new ContextThemeWrapper(getBaseContext(), R.style.ButtonStyle);

        for (int i = 0 ; i < strings.size() ; i++) {
            Button b = new Button(newContext);
            b.setText(strings.get(i));
            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            b.setId(i);
            b.setTag(i);
            b.setBackgroundResource(getResources().getColor(android.R.color.transparent));
            b.setOnClickListener(btnclick);
            llo.addView(b);
        }

        adView = (AdView) findViewById(R.id.adView1);

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        adView.loadAd(adRequest);
    }

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

    View.OnClickListener btnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case 0:
                    change_to_BULGARIAN(view);
                    break;
                case 1:
                    change_to_CROATIAN(view);
                    break;
                case 2:
                    change_to_CZECH(view);
                    break;
                case 3:
                    change_to_DANISH(view);
                    break;
                case 4:
                    change_to_DUTCH(view);
                    break;
                case 5:
                    change_to_ESPERANTO(view);
                    break;
                case 6:
                    change_to_ENGLISH(view);
                    break;
                case 7:
                    change_to_ESTONIAN(view);
                    break;
                case 8:
                    change_to_FINNISH(view);
                    break;
                case 9:
                    change_to_FRENCH(view);
                    break;
                case 10:
                    change_to_GERMAN(view);
                    break;
                case 11:
                    change_to_GREEK(view);
                    break;
                case 12:
                    change_to_HUNGARIAN(view);
                    break;
                case 13:
                    change_to_IRISH(view);
                    break;
                case 14:
                    change_to_ITALIAN(view);
                    break;
                case 15:
                    change_to_LATVIAN(view);
                    break;
                case 16:
                    change_to_LITHUANIAN(view);
                    break;
                case 17:
                    change_to_MALTESE(view);
                    break;
                case 18:
                    change_to_POLISH(view);
                    break;
                case 19:
                    change_to_PORTUGUESE(view);
                    break;
                case 20:
                    change_to_ROMANIAN(view);
                    break;
                case 21:
                    change_to_RUSSIAN(view);
                    break;
                case 22:
                    change_to_SLOVAK(view);
                    break;
                case 23:
                    change_to_SLOVENIAN(view);
                    break;
                case 24:
                    change_to_SPANISH(view);
                    break;
                case 25:
                    change_to_SWEDISH(view);
                    break;
                case 26:
                    change_to_UKRAINIAN(view);
                    break;
                default:
                    break;
            } // End switch
        }
    };

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

    public void change_to_BULGARIAN(View view) {
        Locale locale = new Locale("bg");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Bulgarian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_CROATIAN(View view) {
        Locale locale = new Locale("hr");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Croatian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_SPANISH(View view) {
        Locale locale = new Locale("es");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Spanish");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_RUSSIAN(View view) {
        Locale locale = new Locale("ru");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Russian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_ESPERANTO(View view) {
        Locale locale = new Locale("eo");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Esperanto");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_LITHUANIAN(View view) {
        Locale locale = new Locale("lt");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Lithuanian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_POLISH(View view) {
        Locale locale = new Locale("pl");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Polish");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();
        finish();
        startActivity(intent);
    }
    public void change_to_GERMAN(View view) {
        Locale locale = new Locale("de");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("German");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();
        finish();
        startActivity(intent);
    }
    public void change_to_ROMANIAN(View view) {
        Locale locale = new Locale("ro");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Romanian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_FRENCH(View view) {
        Locale locale = new Locale("fr");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("French");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_SWEDISH(View view) {
        Locale locale = new Locale("sv");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Swedish");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_CZECH(View view) {
        Locale locale = new Locale("cs");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Czech");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_DANISH(View view) {
        Locale locale = new Locale("da");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Danish");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_DUTCH(View view) {
        Locale locale = new Locale("nl");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Dutch");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_ENGLISH(View view) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("English");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_ESTONIAN(View view) {
        Locale locale = new Locale("et");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Estonian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_FINNISH(View view) {
        Locale locale = new Locale("fi");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Finnish");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_GREEK(View view) {
        Locale locale = new Locale("el");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Greek");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_HUNGARIAN(View view) {
        Locale locale = new Locale("hu");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Hungarian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_IRISH(View view) {
        Locale locale = new Locale("ga");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        saveLanguage("Irish");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_ITALIAN(View view) {
        Locale locale = new Locale("it");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Italian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_LATVIAN(View view) {
        Locale locale = new Locale("lv");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Latvian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_MALTESE(View view) {
        Locale locale = new Locale("mt");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Maltese");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_PORTUGUESE(View view) {
        Locale locale = new Locale("pt");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Portuguese");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_SLOVAK(View view) {
        Locale locale = new Locale("sk");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Slovak");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_SLOVENIAN(View view) {
        Locale locale = new Locale("sl");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Slovenian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void change_to_UKRAINIAN(View view) {
        Locale locale = new Locale("uk");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Ukrainian");

        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, getResources().getString(R.string.lang_english), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

    public static final String PREFS_NAME = "PREFS";
    public static final String PREFS_KEY_LANG = "PREF_Lang";

    public void saveLanguage(String text) {
        SharedPreferences lang;
        SharedPreferences.Editor editor;
        lang = this.getSharedPreferences(PREFS_NAME, this.MODE_PRIVATE);
        editor = lang.edit();

        editor.putString(PREFS_KEY_LANG, text);
        editor.commit();

        System.out.println("saveLanguage: " + text);
    }
}