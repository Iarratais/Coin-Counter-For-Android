package com.karl.android.templates;

/**
 * Created by Karl on 20/11/2016.
 *
 * Save template holds all the information to be sent to the database and retrieved from the database
 * to be displayed
 */

public class Save {

    private String id;
    private String title;
    private String amount;
    private String date;
    private String comment;
    private String currency;
    private String grouping;

    private String note200000 = "0";
    private String note100000 = "0";
    private String note50000 = "0";
    private String note20000 = "0";
    private String note10000 = "0";
    private String note5000 = "0";
    private String note2000 = "0";
    private String note1000 = "0";
    private String note500 = "0";
    private String note200 = "0";
    private String note100 = "0";
    private String note50 = "0";
    private String note20 = "0";
    private String note10 = "0";
    private String note5 = "0";
    private String note1 = "0";

    private String coin2 = "0";
    private String coin1 = "0";
    private String cent50 = "0";
    private String cent25 = "0";
    private String cent20 = "0";
    private String cent10 = "0";
    private String cent5 = "0";
    private String cent2 = "0";
    private String cent1 = "0";

    // default constructor
    public Save(){}

    // Database keys
    public static class DatabaseExtras{
        public static String KEY_NOTE_200000 = "note200000";
        public static String KEY_NOTE_100000 = "note100000";
        public static String KEY_NOTE_50000 = "note50000";
        public static String KEY_NOTE_20000 = "note20000";
        public static String KEY_NOTE_10000 = "note10000";
        public static String KEY_NOTE_5000 = "note5000";
        public static String KEY_NOTE_2000 = "note2000";
        public static String KEY_NOTE_1000 = "note1000";
        public static String KEY_NOTE_500 = "note500";
        public static String KEY_NOTE_200 = "note200";
        public static String KEY_NOTE_100 = "note100";
        public static String KEY_NOTE_50 = "note50";
        public static String KEY_NOTE_20 = "note20";
        public static String KEY_NOTE_10 = "note10";
        public static String KEY_NOTE_5 = "note5";
        public static String KEY_NOTE_1 = "note1";

        public static String KEY_COIN_2 = "coin2";
        public static String KEY_COIN_1 = "coin1";

        public static String KEY_CENT_50 = "cent50";
        public static String KEY_CENT_25 = "cent25";
        public static String KEY_CENT_20 = "cent20";
        public static String KEY_CENT_10 = "cent10";
        public static String KEY_CENT_5 = "cent5";
        public static String KEY_CENT_2 = "cent2";
        public static String KEY_CENT_1 = "cent1";
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getNote200000() {
        return note200000;
    }

    public void setNote200000(String note200000) {
        this.note200000 = note200000;
    }

    public String getNote100000() {
        return note100000;
    }

    public void setNote100000(String note100000) {
        this.note100000 = note100000;
    }

    public String getNote50000() {
        return note50000;
    }

    public void setNote50000(String note50000) {
        this.note50000 = note50000;
    }

    public String getNote20000() {
        return note20000;
    }

    public void setNote20000(String note20000) {
        this.note20000 = note20000;
    }

    public String getNote10000() {
        return note10000;
    }

    public void setNote10000(String note10000) {
        this.note10000 = note10000;
    }

    public String getNote5000() {
        return note5000;
    }

    public void setNote5000(String note5000) {
        this.note5000 = note5000;
    }

    public String getNote2000() {
        return note2000;
    }

    public void setNote2000(String note2000) {
        this.note2000 = note2000;
    }

    public String getNote1000() {
        return note1000;
    }

    public void setNote1000(String note1000) {
        this.note1000 = note1000;
    }

    public String getNote500() {
        return note500;
    }

    public void setNote500(String note500) {
        this.note500 = note500;
    }

    public String getNote200() {
        return note200;
    }

    public void setNote200(String note200) {
        this.note200 = note200;
    }

    public String getNote100() {
        return note100;
    }

    public void setNote100(String note100) {
        this.note100 = note100;
    }

    public String getNote50() {
        return note50;
    }

    public void setNote50(String note50) {
        this.note50 = note50;
    }

    public String getNote20() {
        return note20;
    }

    public void setNote20(String note20) {
        this.note20 = note20;
    }

    public String getNote10() {
        return note10;
    }

    public void setNote10(String note10) {
        this.note10 = note10;
    }

    public String getNote5() {
        return note5;
    }

    public void setNote5(String note5) {
        this.note5 = note5;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getCoin2() {
        return coin2;
    }

    public void setCoin2(String coin2) {
        this.coin2 = coin2;
    }

    public String getCoin1() {
        return coin1;
    }

    public void setCoin1(String coin1) {
        this.coin1 = coin1;
    }

    public String getCent50() {
        return cent50;
    }

    public void setCent50(String cent50) {
        this.cent50 = cent50;
    }

    public String getCent25() {
        return cent25;
    }

    public void setCent25(String cent25) {
        this.cent25 = cent25;
    }

    public String getCent20() {
        return cent20;
    }

    public void setCent20(String cent20) {
        this.cent20 = cent20;
    }

    public String getCent10() {
        return cent10;
    }

    public void setCent10(String cent10) {
        this.cent10 = cent10;
    }

    public String getCent5() {
        return cent5;
    }

    public void setCent5(String cent5) {
        this.cent5 = cent5;
    }

    public String getCent2() {
        return cent2;
    }

    public void setCent2(String cent2) {
        this.cent2 = cent2;
    }

    public String getCent1() {
        return cent1;
    }

    public void setCent1(String cent1) {
        this.cent1 = cent1;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Save{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", currency='" + currency + '\'' +
                ", grouping='" + grouping + '\'' +
                ", note200000='" + note200000 + '\'' +
                ", note100000='" + note100000 + '\'' +
                ", note50000='" + note50000 + '\'' +
                ", note20000='" + note20000 + '\'' +
                ", note10000='" + note10000 + '\'' +
                ", note5000='" + note5000 + '\'' +
                ", note2000='" + note2000 + '\'' +
                ", note1000='" + note1000 + '\'' +
                ", note500='" + note500 + '\'' +
                ", note200='" + note200 + '\'' +
                ", note100='" + note100 + '\'' +
                ", note50='" + note50 + '\'' +
                ", note20='" + note20 + '\'' +
                ", note10='" + note10 + '\'' +
                ", note5='" + note5 + '\'' +
                ", note1='" + note1 + '\'' +
                ", coin2='" + coin2 + '\'' +
                ", coin1='" + coin1 + '\'' +
                ", cent50='" + cent50 + '\'' +
                ", cent25='" + cent25 + '\'' +
                ", cent20='" + cent20 + '\'' +
                ", cent10='" + cent10 + '\'' +
                ", cent5='" + cent5 + '\'' +
                ", cent2='" + cent2 + '\'' +
                ", cent1='" + cent1 + '\'' +
                '}';
    }
}
