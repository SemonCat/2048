package com.semoncat.u2048.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;

import com.semoncat.u2048.MainActivity;
import com.semoncat.u2048.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SemonCat on 2014/3/30.
 */
public class ThemeSwitchDialog extends DialogFragment implements DialogInterface.OnClickListener{

    public static final String USER_THEME = "user_theme";



    private String[] Themes;

    private static final int[] ThemeNameResourceIds =
            new int[]{R.string.theme_base,
                    R.string.theme_pink,
                    R.string.theme_dynasty};

    private static final int[] ThemeResourceIds =
            new int[]{R.style.Theme_Base,
                    R.style.Theme_Pink,
                    R.style.Theme_Dynasty};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        List<String> ThemeNameList = new ArrayList<String>();
        for (int NameResourceId:ThemeNameResourceIds){
            ThemeNameList.add(getResources().getString(NameResourceId));
        }

        Themes = ThemeNameList.toArray(new String[ThemeNameResourceIds.length]);

        Dialog dialog =
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.chose_theme)
                        .setSingleChoiceItems(Themes,
                                getUserThemePosition(), this)
                        .create();

        return dialog;
    }

    public static int getUserTheme(Context mContext){
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        return mSharedPreferences.getInt(USER_THEME,ThemeResourceIds[0]);
    }

    private int getUserThemePosition(){
        int UserTheme = getUserTheme(getActivity());


        for (int i=0;i<ThemeResourceIds.length;i++){
            if (ThemeResourceIds[i]==UserTheme){
                return i;
            }
        }

        return -1;
    }

    public static void setUserTheme(Context mContext,int ThemeResourceId){
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        mSharedPreferences.edit().putInt(USER_THEME,ThemeResourceId).commit();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        setUserTheme(getActivity(),ThemeResourceIds[which]);
        getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
        getActivity().finish();
    }
}
