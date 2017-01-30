package com.mygames.mygames.adapters;

import com.mygames.mygames.view.NavigationFragment;

public class DrawerItem {
    private NavigationFragment.NavigationItem navigationItem;
    private int imageRes;
    private String activityName;


    public DrawerItem(NavigationFragment.NavigationItem navigationItem, int imageRes, String activityName) {
        this.navigationItem = navigationItem;
        this.imageRes = imageRes;
        this.activityName = activityName;
    }

    public NavigationFragment.NavigationItem getNavigationItem() {
        return navigationItem;
    }

    public void setNavigationItem(NavigationFragment.NavigationItem navigationItem) {
        this.navigationItem = navigationItem;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}