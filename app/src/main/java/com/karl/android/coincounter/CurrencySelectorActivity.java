package com.karl.android.coincounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class CurrencySelectorActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selector);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences settings = this.getSharedPreferences(
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

    public void change_to_USD(View view) {
        saveCurrency("USD");
        Toast.makeText(this, "Changed currency to USD", Toast.LENGTH_SHORT).show();
    }

    public void change_to_GBP(View view) {
        saveCurrency("GBP");
        Toast.makeText(this, "Changed currency to GBP", Toast.LENGTH_SHORT).show();
    }

    public void change_to_EUR(View view) {
        saveCurrency("EUR");
        Toast.makeText(this, "Changed currency to EUR", Toast.LENGTH_SHORT).show();
    }

    public static final String PREFS_NAME = "PREFS";
    public static final String PREFS_KEY = "PREFS_String";

    public void saveCurrency(String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = this.getSharedPreferences(PREFS_NAME, this.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString(PREFS_KEY, text);
        editor.commit();
    }
}