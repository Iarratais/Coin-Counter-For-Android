// Copyright Karl Jones

package com.karl.android.coincounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.text.DecimalFormat;


public class DisplayMessageActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_message);

        DecimalFormat formatter = new DecimalFormat("#0.00");

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        StringBuilder stringBuilder = new StringBuilder().append(message);

        // It is converted to a string and then to a double so that the format of the output is correct
        String finalTotal = stringBuilder.toString();

        double finalValue = Double.parseDouble(finalTotal);

        String finalString = formatter.format(finalValue);

        TextView textView = new TextView(this);
        textView = (TextView) findViewById(R.id.finalValueDisplay);
        textView.setTextSize(40);
        textView.setText(finalString);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
