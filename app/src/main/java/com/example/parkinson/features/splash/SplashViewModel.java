package com.example.parkinson.features.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.parkinson.data.UserRepository;

import javax.inject.Inject;

public class SplashViewModel {
    private final UserRepository userRepository;

    public enum NavigationEvent{
        OPEN_ON_BOARDING_ACTIVITY,
        OPEN_MAIN_ACTIVITY
    }

    MutableLiveData<NavigationEvent> navigationEvent;

    // @Inject tells Dagger how to create instances of MainViewModel
    @Inject
    public SplashViewModel(UserRepository userRepository) {

        this.userRepository = userRepository;
        navigationEvent = new MutableLiveData<NavigationEvent>();
    }

    public void init(){
        fetchUser();
    }

    private void fetchUser(){
        if(userRepository.getCurrentUser() == null){
            navigationEvent.postValue(NavigationEvent.OPEN_ON_BOARDING_ACTIVITY);
        } else {
            navigationEvent.postValue(NavigationEvent.OPEN_MAIN_ACTIVITY);
        }
    }


}
