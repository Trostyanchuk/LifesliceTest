package com.lifeslicetest.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lifeslicetest.ApplicationController;
import com.lifeslicetest.R;
import com.lifeslicetest.databinding.ActivityMainBinding;
import com.lifeslicetest.service.IBroadcast;
import com.lifeslicetest.service.impl.events.TagVideosEvent;
import com.lifeslicetest.utils.UIUtil;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    IBroadcast broadcast;

    private MainActivityAdapter mViewPagerAdapter;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ApplicationController.getComponent().inject(this);

        mViewPagerAdapter = new MainActivityAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mainBinding.container.setAdapter(mViewPagerAdapter);
        mainBinding.tabs.setupWithViewPager(mainBinding.container);
    }

    @Override
    public void onResume() {
        super.onResume();
        broadcast.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        broadcast.unregister(this);
    }

    @Subscribe
    public void onTagVideosEvent(TagVideosEvent event) {
        UIUtil.hideKeyboard(this);
        mainBinding.container.setCurrentItem(1, true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
