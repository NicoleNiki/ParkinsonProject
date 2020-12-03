package com.example;

import android.app.Application;

import com.example.parkinson.di.ApplicationComponent;
import com.example.parkinson.di.DaggerApplicationComponent;

public class ParkinsonApplication extends Application {

    public ApplicationComponent appComponent = DaggerApplicationComponent.create();

}
