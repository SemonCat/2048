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

    public enum ThemeType{
        Theme_Base,
        Theme_Pink,
        Theme_Dynasty,
        Theme_Zodiac,
        Theme_Periodic
    }

    public static final String USER_THEME = "user_theme";


    private String[] Themes;

    private static final int[] ThemeNameResourceIds =
            new int[]{R.string.theme_base,
                    R.string.theme_pink,
                    R.string.theme_dynasty,
                    R.string.theme_zodiac,
                    R.string.theme_periodic};

    private static final int[] ThemeResourceIds =
            new int[]{R.style.Theme_Base,
                    R.style.Theme_Pink,
                    R.style.Theme_Dynasty,
                    R.style.Theme_Zodiac,
                    R.style.Theme_Periodic};

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

    public static void setUserTheme(Context mContext,int ThemeResourceId){
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        mSharedPreferences.edit().putString(USER_THEME,
                getThemeTypeFromThemeResourceId(ThemeResourceId).name())
                .commit();
    }

    public static int getUserTheme(Context mContext){
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        int ThemeResourceId = getThemeResourceIdFromThemeType(ThemeType.valueOf(mSharedPreferences.getString(USER_THEME,"Theme_Base")));
        return ThemeResourceId;
    }

    public static ThemeType getThemeTypeFromThemeResourceId(int ThemeResourceId){
        switch (ThemeResourceId){
            case R.style.Theme_Base:
                return ThemeType.Theme_Base;
            case R.style.Theme_Pink:
                return ThemeType.Theme_Pink;
            case R.style.Theme_Dynasty:
                return ThemeType.Theme_Dynasty;
            case R.style.Theme_Periodic:
                return ThemeType.Theme_Periodic;
            case R.style.Theme_Zodiac:
                return ThemeType.Theme_Zodiac;
        }

        return ThemeType.Theme_Base;
    }

    public static int getThemeResourceIdFromThemeType(ThemeType mThemeType){
        switch (mThemeType){
            case Theme_Base:
                return R.style.Theme_Base;
            case Theme_Pink:
                return R.style.Theme_Pink;
            case Theme_Dynasty:
                return R.style.Theme_Dynasty;
            case Theme_Periodic:
                return R.style.Theme_Periodic;
            case Theme_Zodiac:
                return R.style.Theme_Zodiac;

        }

        return R.style.Theme_Base;
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




    @Override
    public void onClick(DialogInterface dialog, int which) {
        setUserTheme(getActivity(),ThemeResourceIds[which]);
        getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
        getActivity().finish();
    }
}
