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
public class BaseActivity extends BaseGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeSwitchDialog.getUserTheme(this));
        super.onCreate(savedInstanceState);

        beginUserInitiatedSignIn();
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme()!=null &&
                    getTheme().resolveAttribute(android.R.attr.actionBarSize, tv,
                    true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(
                        tv.data, getResources().getDisplayMetrics());
        } else {
            if (getTheme()!=null &&
                    getTheme().resolveAttribute(R.attr.actionBarSize, tv,
                    true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(
                        tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
