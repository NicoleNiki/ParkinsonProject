package com.example.parkinson.di;

import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.features.main.MainFragment;
import com.example.parkinson.features.on_boarding.OnBoardingActivity;
import com.example.parkinson.features.on_boarding.login.LoginFragment;

import dagger.Subcomponent;

@MainScope
@Subcomponent
public interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        MainComponent create();
    }

    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
}
