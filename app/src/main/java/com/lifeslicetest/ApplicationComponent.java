package com.lifeslicetest;

import com.lifeslicetest.ui.fragment.TabTagFragment;
import com.lifeslicetest.ui.fragment.TabVideoFragment;
import com.lifeslicetest.ui.main.MainActivity;
import com.lifeslicetest.ui.widget.CircleLoaderImageView;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(ApplicationController controller);

    void inject(MainActivity activity);

    void inject(TabTagFragment tabTagFragment);

    void inject(TabVideoFragment tabVideoFragment);

    void inject(CircleLoaderImageView circleLoaderImageView);
}
