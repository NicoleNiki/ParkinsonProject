package com.example.parkinson.features.splash;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;

import javax.inject.Inject;

public class SplashViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public SplashViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }


}
