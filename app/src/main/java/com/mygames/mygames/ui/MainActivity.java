package com.mygames.mygames.ui;

import android.os.Bundle;

import com.mygames.mygames.R;
import com.mygames.mygames.adapters.DrawerItem;
import com.mygames.mygames.annotation.BaseActivityOptions;
import com.mygames.mygames.ui.base.BaseActivity;
import com.mygames.mygames.view.NavigationFragment;

@BaseActivityOptions(layout = R.layout.activity_main, activityName = "Home" ,
        knifeEnabled = true , eventBusEnabled = true, menuEnabled =  true , isHomeAsUp = true)
public class MainActivity extends BaseActivity implements NavigationFragment.NavigationCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onNavigationItemSelected(DrawerItem item) {

    }
}
