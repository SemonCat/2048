package com.semoncat.u2048;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.widget.Toast;

import com.google.android.gms.games.Games;
import com.semoncat.u2048.Fragment.ThemeSwitchDialog;

/**
 * Created by SemonCat on 2014/3/30.
 */
public class BaseActivity extends BaseGameActivity {


    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeSwitchDialog.getUserTheme(this));
        super.onCreate(savedInstanceState);
    }

    public void showToast(String Message){
        if (mToast==null){
            mToast = Toast.makeText(this,Message,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(Message);
        }
        mToast.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {


    }
}
