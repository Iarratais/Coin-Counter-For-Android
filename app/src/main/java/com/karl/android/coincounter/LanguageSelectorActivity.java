package com.karl.android.coincounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;


public class LanguageSelectorActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selector);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.language_title);

        SharedPreferences lang = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

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

    public void change_to_SPANISH(View view) {
        Locale locale = new Locale("es");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);
        saveLanguage("Spanish");

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

        startActivity(intent);
    }

    public void request_language(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public static final String PREFS_NAME = "PREFS";
    public static final String PREFS_KEY = "PREFS_Lang";

    public void saveLanguage(String text) {
        SharedPreferences lang;
        SharedPreferences.Editor editor;
        lang = this.getSharedPreferences(PREFS_NAME, this.MODE_PRIVATE);
        editor = lang.edit();

        editor.putString(PREFS_KEY, text);
        editor.commit();
    }
}