package com.example.parkinson.di;

import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.features.main.MainFragment;
import com.example.parkinson.features.on_boarding.OnBoardingActivity;
import com.example.parkinson.features.on_boarding.login.LoginFragment;
import com.example.parkinson.features.splash.SplashActivity;
import com.example.parkinson.network.Authentication;

import java.net.Authenticator;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component()
public interface ApplicationComponent {

    OnBoardingComponent.Factory onBoardingComponent();
    MainComponent.Factory mainComponent();
    // This tells Dagger that Activity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is injecting.
    void inject(SplashActivity splashActivity);
}
