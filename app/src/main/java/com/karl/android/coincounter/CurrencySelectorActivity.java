package com.karl.android.coincounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CurrencySelectorActivity extends ActionBarActivity{

    private Toolbar toolbar;

    private EditText note5000edit;
    private EditText note1000edit;
    private EditText note500edit;
    private EditText note100edit;
    private EditText note50edit;
    private EditText note20edit;
    private EditText note10edit;
    private EditText note5edit;
    private EditText note1edit;
    private EditText coin2edit;
    private EditText coin1edit;
    private EditText cent50edit;
    private EditText cent25edit;
    private EditText cent20edit;
    private EditText cent10edit;
    private EditText cent5edit;
    private EditText cent2edit;
    private EditText cent1edit;
    private EditText additionaledit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selector);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences settings = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        note5000edit     = (EditText) findViewById (R.id.notes5000);
        note1000edit     = (EditText) findViewById (R.id.notes1000);
        note500edit      = (EditText) findViewById (R.id.notes500);
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void change_to_GBP(View view) {
        saveCurrency("GBP");
        Toast.makeText(this, "Changed currency to GBP", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void change_to_EUR(View view) {
        saveCurrency("EUR");
        Toast.makeText(this, "Changed currency to EUR", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void change_to_RUB(View view) {
        saveCurrency("RUB");
        Toast.makeText(this, "Changed currency to RUB", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

    public void clearAllChars() {
        note5000edit.setText("");
        note1000edit.setText("");
        note500edit.setText("");
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

        System.out.println("CurrencySelectorActivity.clearAll: All inputs cleared");
    }
}
