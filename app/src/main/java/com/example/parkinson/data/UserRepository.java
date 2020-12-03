package com.example.parkinson.data;

import com.example.parkinson.network.NetworkModule;

import javax.inject.Inject;


public class UserRepository {
    private final NetworkModule apiService;

    @Inject
    public UserRepository(NetworkModule apiService) {
        this.apiService = apiService;
    }
}
