package com.karl.android.templates;

/**
 * Created by Karl on 25/11/2016.
 *
 * This is a slimmed down version of Save. This only holds the essential information for displaying
 * the information to the user through the lists of the app
 */

public class SlimSave {

    private String id;
    private String title;
    private String amount;
    private String date;
    private String currency;

    public SlimSave() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "SlimSave{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
