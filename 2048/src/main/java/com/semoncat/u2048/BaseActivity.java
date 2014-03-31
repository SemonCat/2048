package com.semoncat.u2048;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;

import com.semoncat.u2048.Fragment.ThemeSwitchDialog;

/**
 * Created by SemonCat on 2014/3/30.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeSwitchDialog.getUserTheme(this));
        super.onCreate(savedInstanceState);
    }


}
