package com.example.parkinson.features.notification;

import com.example.parkinson.data.DataRepository;
import com.example.parkinson.data.UserRepository;
import com.example.parkinson.di.MainScope;
import com.example.parkinson.model.enums.EStatus;
import com.example.parkinson.model.general_models.Report;
import java.util.Calendar;

import javax.inject.Inject;

@MainScope
public class NotificationViewModel {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    @Inject
    public NotificationViewModel(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public void updateReport(EStatus status) {
        Report report = new Report(Calendar.getInstance().getTime().getTime(),status);
        userRepository.postReport(report);
    }
}
