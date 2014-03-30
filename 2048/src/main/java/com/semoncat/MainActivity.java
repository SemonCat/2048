package com.semoncat;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setupTranslucent();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupDrawer();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainFragment()).commit();

        setupSystemBarTintManager();
    }

    private void setupTranslucent(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void setupSystemBarTintManager(){
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintEnabled(true);


        TypedValue a = new TypedValue();
        if (getTheme()!=null){
            getTheme().resolveAttribute(R.attr.actionBarBackground, a, true);

            tintManager.setStatusBarTintResource(a.resourceId);
            tintManager.setNavigationBarTintResource(a.resourceId);
        }

    }

    private void setupDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.Navi_Drawer);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,    // 讓 Drawer Toggle 知道母體介面是誰
                R.drawable.ic_navigation_drawer, // Drawer 的 Icon
                R.string.app_name, // Drawer 被打開時的描述
                R.string.app_name // Drawer 被關閉時的描述
        );

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //顯示 Up Button (位在 Logo 左手邊的按鈕圖示)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //打開 Up Button 的點擊功能
        getSupportActionBar().setHomeButtonEnabled(true);

        View DrawContent = findViewById(R.id.drawer_content);
        DrawContent.setPadding(0,getActionBarHeight()+getStatusBarHeight(),0,0);

    }

    public void switchTheme(View mView){
        mDrawerLayout.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
