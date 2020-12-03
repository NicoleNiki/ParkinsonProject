package com.example.parkinson.data;

import com.example.parkinson.network.NetworkModule;

import javax.inject.Inject;



public class DataRepository {
    private final NetworkModule apiService;

    @Inject
    public DataRepository(NetworkModule apiService) {
        this.apiService = apiService;
    }


}
