package com.example.parkinson.model.general_models;

public class Time {
    private int minutes, hour;

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
}
