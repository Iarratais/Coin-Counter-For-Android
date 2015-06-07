package com.karl.android.coincounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public static String note50Amt;
    public static String note20Amt;
    public static String note10Amt;
    public static String note5Amt;
    public static String coin2Amt;
    public static String coin1Amt;
    public static String cent50Amt;
    public static String cent20Amt;
    public static String cent10Amt;
    public static String cent5Amt;
    public static String cent2Amt;
    public static String cent1Amt;
    public static String additionalCoins;

    public final static String EXTRA_MESSAGE = "com.karl.coincounter.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void calculateCoins(View button) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        final EditText notes50 = (EditText) findViewById(R.id.notes50);
        note50Amt = notes50.getText().toString();

        final EditText notes20 = (EditText) findViewById(R.id.notes20);
        note20Amt = notes20.getText().toString();

        final EditText notes10 = (EditText) findViewById(R.id.notes10);
        note10Amt = notes10.getText().toString();

        final EditText notes5 = (EditText) findViewById(R.id.notes5);
        note5Amt = notes5.getText().toString();

        final EditText coins2 = (EditText) findViewById(R.id.coins2);
        coin2Amt = coins2.getText().toString();

        final EditText coins1 = (EditText) findViewById(R.id.coins1);
        coin1Amt = coins1.getText().toString();

        final EditText cents50 = (EditText) findViewById(R.id.coins1);
        cent50Amt = cents50.getText().toString();

        final EditText cents20 = (EditText) findViewById(R.id.coins1);
        cent20Amt = cents20.getText().toString();

        final EditText cents10 = (EditText) findViewById(R.id.coins1);
        cent10Amt = cents10.getText().toString();

        final EditText cents5 = (EditText) findViewById(R.id.coins1);
        cent5Amt = cents5.getText().toString();

        final EditText cents2 = (EditText) findViewById(R.id.coins1);
        cent2Amt = cents2.getText().toString();

        final EditText cents1 = (EditText) findViewById(R.id.coins1);
        cent1Amt = cents1.getText().toString();

        final EditText additions = (EditText) findViewById(R.id.extraAdditions);
        additionalCoins = additions.getText().toString();

        double total = calcTotal();

        intent.putExtra(EXTRA_MESSAGE, total);
        startActivity(intent);
    }

    public static double calcTotal() {
        double note50 = toInt(note50Amt) * 50.0;
        System.out.println("€50: " + note50);

        double note20 = toInt(note20Amt) * 20.0;
        System.out.println("€20: " + note20);

        double note10 = toInt(note10Amt) * 10.0;
        System.out.println("€10: " + note10);

        double note5 = toInt(note5Amt) * 5.0;
        System.out.println("€5: " + note5);

        double euro2 = toInt(coin2Amt) * 2.0;
        System.out.println("€2: " + euro2);

        double euro1 = toInt(coin1Amt) * 1.0;
        System.out.println("€1: " + euro1);

        double cent50 = toInt(cent50Amt) * 0.50;
        System.out.println("50c: " + cent50);

        double cent20 = toInt(cent20Amt) * 0.20;
        System.out.println("20c: " + cent20);

        double cent10 = toInt(cent10Amt) * 0.10;
        System.out.println("10c: " + cent10);

        double cent5 = toInt(cent5Amt) * 0.05;
        System.out.println("5c: " + cent5);

        double cent2 = toInt(cent2Amt) * 0.020;
        System.out.println("2c: " + cent2);

        double cent1 = toInt(cent1Amt) * 0.01;
        System.out.println("1c: " + cent1);

        double total = 0;

        total += note50 + note20 + note10 + note5 + euro2 + euro1 + cent50 + cent20 + cent10 + cent5 + cent2 + cent1 + toInt(additionalCoins);

        System.out.println("Total: " + total);

        return total;
    }

    // Method to convert the strings into integers
    public static Integer toInt(String number) {

        int num = Integer.parseInt(number);

        return num;
    }
}
