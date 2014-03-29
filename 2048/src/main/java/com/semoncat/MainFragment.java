package com.semoncat;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.semoncat.Bean.Tile;
import com.semoncat.View.MainView;

/**
 * Created by SemonCat on 2014/3/29.
 */
public class MainFragment extends Fragment{

    MainView view;
    final String WIDTH = "width";
    final String HEIGHT= "height";
    final String SCORE = "score";
    final String HIGH_SCORE = "high score";
    final String WON = "won";
    final String LOSE = "lose";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new MainView(getActivity());

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        view.hasSaveState = settings.getBoolean("save_state", false);

        if (savedInstanceState != null) {
            int[][] saveState = new int[view.game.grid.field.length][view.game.grid.field[0].length];
            for (int xx = 0; xx < saveState.length; xx++) {
                saveState[xx] = savedInstanceState.getIntArray("" + xx);
            }
            for (int xx = 0; xx < saveState.length; xx++) {
                for (int yy = 0; yy < saveState[0].length; yy++) {
                    if (saveState[xx][yy] != 0) {
                        view.game.grid.field[xx][yy] = new Tile(xx, yy, saveState[xx][yy]);
                    } else {
                        view.game.grid.field[xx][yy] = null;
                    }
                }
            }
            view.game.score = savedInstanceState.getLong(SCORE);
            view.game.highScore = savedInstanceState.getLong(HIGH_SCORE);
            view.game.won = savedInstanceState.getBoolean(WON);
            view.game.lose = savedInstanceState.getBoolean(LOSE);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Tile[][] field = view.game.grid.field;
        int[][] saveState = new int[field.length][field[0].length];
        for (int xx = 0; xx < field.length; xx++) {
            for (int yy = 0; yy < field[0].length; yy++) {
                if (field[xx][yy] != null) {
                    saveState[xx][yy] = field[xx][yy].getValue();
                } else {
                    saveState[xx][yy] = 0;
                }
            }
        }
        for (int xx = 0; xx < saveState.length; xx++) {
            savedInstanceState.putIntArray("" + xx, saveState[xx]);
        }
        savedInstanceState.putLong(SCORE, view.game.score);
        savedInstanceState.putLong(HIGH_SCORE, view.game.highScore);
        savedInstanceState.putBoolean(WON, view.game.won);
        savedInstanceState.putBoolean(LOSE, view.game.lose);
    }



    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = settings.edit();
        Tile[][] field = view.game.grid.field;
        editor.putInt(WIDTH, field.length);
        editor.putInt(HEIGHT, field.length);
        for (int xx = 0; xx < field.length; xx++) {
            for (int yy = 0; yy < field[0].length; yy++) {
                if (field[xx][yy] != null) {
                    editor.putInt(xx + " " + yy, field[xx][yy].getValue());
                } else {
                    editor.putInt(xx + " " + yy, 0);
                }
            }
        }
        editor.putLong(SCORE, view.game.score);
        editor.putLong(HIGH_SCORE, view.game.highScore);
        editor.putBoolean(WON, view.game.won);
        editor.putBoolean(LOSE, view.game.lose);
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        //Stopping all animations
        view.game.aGrid.cancelAnimations();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        for (int xx = 0; xx < view.game.grid.field.length; xx++) {
            for (int yy = 0; yy < view.game.grid.field[0].length; yy++) {
                int value = settings.getInt(xx + " " + yy, -1);
                if (value > 0) {
                    view.game.grid.field[xx][yy] = new Tile(xx, yy, value);
                } else if (value == 0) {
                    view.game.grid.field[xx][yy] = null;
                }
            }
        }

        view.game.score = settings.getLong(SCORE, view.game.score);
        view.game.highScore = settings.getLong(HIGH_SCORE, view.game.highScore);
        view.game.won = settings.getBoolean(WON, view.game.won);
        view.game.lose = settings.getBoolean(LOSE, view.game.lose);
    }
}
