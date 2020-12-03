package com.example.parkinson.features.on_boarding;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;

import javax.inject.Inject;

public class OnBoardingViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public OnBoardingViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }


}
