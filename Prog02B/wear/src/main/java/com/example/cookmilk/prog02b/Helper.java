package com.example.cookmilk.prog02b;

import android.app.Application;

/**
 * Created by Cookmilk on 3/11/16.
 */
public class Helper extends Application {
    private int zipcode;


    public int getZip() {
        return zipcode;
    }

    public void setZip(int zip) {
        zipcode = zip;
    }
}
