package com.lifeslicetest.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lifeslicetest.ui.fragment.TabTagFragment;
import com.lifeslicetest.ui.fragment.TabVideoFragment;

public class MainActivityAdapter extends FragmentPagerAdapter {

    public enum Tab {
        TAG, VIDEO
    }


    public MainActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Tab currentTab = Tab.values()[position];
        Fragment fragment = null;
        switch (currentTab) {
            case TAG:
                fragment = TabTagFragment.newInstance();
                break;
            case VIDEO:
                fragment = TabVideoFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Tab.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Fragment A";
            case 1:
                return "Fragment B";
        }
        return null;
    }
}