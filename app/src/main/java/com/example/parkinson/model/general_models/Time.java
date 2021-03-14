package com.example.parkinson.model.general_models;

import androidx.annotation.NonNull;

public class Time {
    private int minutes, hour;

    public Time (){

    }

    public Time(int minutes, int hour) {
        this.minutes = minutes;
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @NonNull
    @Override
    public String toString() {
        return (hour + ":"+ minutes);
    }
}
