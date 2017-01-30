package com.mygames.mygames.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mygames.mygames.R;
import com.mygames.mygames.adapters.DrawerItem;
import com.mygames.mygames.adapters.DrawerMenuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationFragment extends Fragment {

    @BindView(R.id.list)
    protected ListView mListView;


    private NavigationCallbacks mCallbacks;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private DrawerMenuAdapter mAdapter;
    private NavigationItem mCurrentItem = NavigationItem.HOME;
    private boolean mListIsEmpty;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallbacks = (NavigationCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationCallbacks interface");
        }
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_navigation, container, false);
        mListView = (ListView) root.findViewById(R.id.list);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mAdapter == null) {
            mAdapter = new DrawerMenuAdapter(initDrawerMenuElements());
        }
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener((parent, view, position, id)
                -> mCallbacks.onNavigationItemSelected(mAdapter.getItem(position)));
    }


    @Override
    public void onDetach() {
        mCallbacks = null;
        super.onDetach();
    }

    public enum NavigationItem {
        HOME
    }

    public List<DrawerItem> initDrawerMenuElements(){
        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(NavigationItem.HOME,R.drawable.ic_home_black_24dp,"Home"));
        return  drawerItems;
    }

    public void setSelectedItem(DrawerItem item) {
        mCurrentItem = item.getNavigationItem();
        if (mListView != null && mAdapter != null) {
            mListView.setItemChecked(mAdapter.getItemPosition(item) + mListView.getHeaderViewsCount(), true);
        }
    }



    public interface NavigationCallbacks {
        void onNavigationItemSelected(DrawerItem item);
    }

}
