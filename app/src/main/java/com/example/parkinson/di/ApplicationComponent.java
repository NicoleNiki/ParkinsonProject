package com.example.parkinson.di;

import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.features.on_boarding.OnBoardingActivity;
import com.example.parkinson.features.splash.SplashActivity;

import dagger.Component;

@Component
public interface ApplicationComponent {
    // This tells Dagger that Activity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is injecting.
    void inject(MainActivity mainActivity);
    void inject(SplashActivity splashActivity);
    void inject(OnBoardingActivity onBoardingActivity);
}
