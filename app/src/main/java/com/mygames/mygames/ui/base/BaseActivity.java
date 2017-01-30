package com.mygames.mygames.ui.base;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.mygames.mygames.R;
import com.mygames.mygames.annotation.BaseActivityOptions;
import com.mygames.mygames.event.BaseEvent;
import com.mygames.mygames.view.NavigationFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Max on 30.01.2017.
 */

public class BaseActivity extends AppCompatActivity {

    private BaseActivityOptions baseActivityOptions = getClass().getAnnotation(BaseActivityOptions.class);
    protected Toolbar mToolbar;
    @Nullable
    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;
    protected NavigationFragment mNavigationFragment;
    protected ProgressDialog mProgressDialog;
    protected ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     * Calls dialog with progress bar for blocking UI, while some background task have to be executed
     */
    protected void showProgressDialog(boolean show) {
        showProgressDialog(show, "Пожалуйста, подождите");
    }

    /**
     * Calls dialog with progress bar for blocking UI, while some background task have to be executed
     */
    protected void showProgressDialog(boolean show, String message) {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        if (!show) return;
        hideKeyboard();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    /**
     * In OnCreate we have workaround with 3 annotation parameters:
     * <ul>
     * <li>resource &ndash; for setContentView method</li>
     * <li>toolbarResId &ndash; for toolbar activation (if it was defined)</li>
     * <li>knifeEnabled &ndash; for injecting views, if it is set to True</li>
     * </ul>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (baseActivityOptions.layout() != 0) {
            if (baseActivityOptions.menuEnabled()) {
                setContentView(R.layout.activity_base);
                initActivityView(baseActivityOptions.layout());
            } else setContentView(baseActivityOptions.layout());

            if (baseActivityOptions.toolbarResId() != 0) {
                mToolbar = (Toolbar) findViewById(baseActivityOptions.toolbarResId());
                setSupportActionBar(mToolbar);
            }
            if (baseActivityOptions.knifeEnabled()) ButterKnife.bind(this);
            if (baseActivityOptions.menuEnabled()) initNavigationFragment();
        }
    }

    private void initActivityView(int res) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_frame);
        frameLayout.addView(getLayoutInflater().inflate(res, frameLayout, false));
    }

    private void initNavigationFragment() {
        mNavigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    public void closeDrawerLayout() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    /**
     * Here we register class in eventBus if needed
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (baseActivityOptions.eventBusEnabled() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * Here we unregister class in eventBus if needed
     */
    @Override
    protected void onStop() {
        if (baseActivityOptions.eventBusEnabled() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        setTitle(baseActivityOptions.activityName());
        restoreActionBar();
        return result;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    protected void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setDisplayShowHomeEnabled(baseActivityOptions.menuEnabled() || baseActivityOptions.isHomeAsUp());
        if (baseActivityOptions.isHomeAsUp()) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    protected void hideKeyboard() {
        View v = getCurrentFocus();
        if (v != null) {
            ((InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBaseEvent(BaseEvent unused) {
        // do nothing. for EventBus, if it would be registered
    }
}
