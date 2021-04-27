package com.example.parkinson.features.main;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.common.LoadingScreen;
import com.example.parkinson.di.MainComponent;
import com.example.parkinson.features.brodacsts.NotifService;
import com.example.parkinson.features.brodacsts.ReportBroadcast;
import com.example.parkinson.features.brodacsts.ReportService;
import com.example.parkinson.features.notification.NotifServiceForground;
import com.example.parkinson.features.on_boarding.OnBoardingActivity;

import java.util.Calendar;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public MainComponent mainComponent;

    @Inject
    MainViewModel mainViewModel;
    LoadingScreen loadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainComponent = ((ParkinsonApplication) getApplicationContext()).appComponent.mainComponent().create();
        mainComponent.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        initObservers();
        initNotifactions();

    }

    private void initNotifactions() {
        Intent intentService = new Intent(this, NotifServiceForground.class);
        startService(intentService);
        // regular report
//        Intent intent = new Intent(this, ReportService.class);
//        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 9);
//        calendar.set(Calendar.MINUTE,0);
//
//        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
//        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                1000 * 60 * 60, pintent);
        //alarm.setExact()
// Set the alarm to start at 8:30 a.m.
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 22);
//        calendar.set(Calendar.MINUTE, 59);

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
//
        //setMedicineAlarm();


//        Intent medicineIntent = new Intent(this, NotifService.class);
//        medicineIntent.putExtra("command", "start Notifaction");
//        medicineIntent.putExtra("notifHour", "2100");
//        PendingIntent medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);
//
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTimeInMillis(System.currentTimeMillis());
//        calendar2.set(Calendar.HOUR_OF_DAY,21);
//        calendar2.set(Calendar.MINUTE,14);
//
//        AlarmManager alarm2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
//        alarm2.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
//                1000 * 60 *30, medicineIntentPintent);

//        Intent AmedicineIntent = new Intent(this, NotifService.class);
//        AmedicineIntent.putExtra("command", "start Notifaction");
//        AmedicineIntent.putExtra("notifHour", "2200");
//        PendingIntent AmedicineIntentPintent = PendingIntent.getService(this, 0, AmedicineIntent, 0);
//
//        Calendar Acalendar2 = Calendar.getInstance();
//        Acalendar2.setTimeInMillis(System.currentTimeMillis());
//        Acalendar2.set(Calendar.HOUR_OF_DAY,21);
//        Acalendar2.set(Calendar.MINUTE,58);
//
//        AlarmManager alarm3 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
//        alarm3.setRepeating(AlarmManager.RTC_WAKEUP, Acalendar2.getTimeInMillis(),
//                1000 * 60 * 30, AmedicineIntentPintent);
    }

    private void setMedicineAlarm() {
        Intent medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0900");
        PendingIntent medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,9);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm2.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0930");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,9);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm3 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm3.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1000");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,10);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm4 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm4.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1030");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,10);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm5 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm5.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1100");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,11);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm6 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm6.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1130");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,11);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm7 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm7.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);


        /////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1200");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,12);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm8 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm8.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1230");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,12);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm9 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm9.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);
        //////////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1300");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,13);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm10 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm10.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1330");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,13);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm11 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm11.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        ////////////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1400");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,14);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm12 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm12.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1430");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,14);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm13 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm13.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        //////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1500");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,15);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm14 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm14.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1530");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,15);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm15 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm15.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);
        ///////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1600");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,16);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm16 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm16.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1630");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,16);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm17 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm17.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        ///////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1700");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,17);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm18 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm18.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1730");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,17);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm19 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm19.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        /////////////////////////////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1800");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,18);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm20 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm20.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1830");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,18);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm21 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm21.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        /////////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1900");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,19);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm22 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm22.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "1930");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,19);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm23 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm23.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        ///////////////////////////////////////


        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2000");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,20);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm24 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm24.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2030");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,20);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm25 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm25.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        /////////////////////////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2100");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,21);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm26 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm26.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2130");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,21);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm27 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm27.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        //////////////////////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2200");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,22);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm28 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm28.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2230");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,22);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm29 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm29.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        ////////////////////////////////////////////


        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2300");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,23);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm30 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm30.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "2330");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,23);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm31 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm31.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        //////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0000");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,0);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm32 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm32.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0030");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,0);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm33 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm33.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);


        ///////////////////////////////////////// TODO AM

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0500");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,5);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm34 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm34.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0530");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,5);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm35 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm35.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);
        /////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0600");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,6);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm36 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm36.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0630");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,6);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm37 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm37.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        /////////////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0700");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,7);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm38 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm38.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0730");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,7);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm39 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm39.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);
////////////////////////////////////

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0800");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,8);
        calendar2.set(Calendar.MINUTE,0);

        AlarmManager alarm40 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm40.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);

        medicineIntent = new Intent(this, NotifService.class);
        medicineIntent.putExtra("command", "start Notifaction");
        medicineIntent.putExtra("notifHour", "0830");
        medicineIntentPintent = PendingIntent.getService(this, 0, medicineIntent, 0);

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY,8);
        calendar2.set(Calendar.MINUTE,30);

        AlarmManager alarm41 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 3000, AlarmManager.INTERVAL_DAY, pintent);
        alarm41.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
                1000 * 60 * 60*24, medicineIntentPintent);



    }

    private void initUi() {
        loadingScreen = findViewById(R.id.loadingScreen);
    }

    private void initObservers(){
        mainViewModel.openActivityEvent.observe(this, navigationEvent -> {
            switch (navigationEvent){
                case OPEN_ON_BOARDING_ACTIVITY:
                    openOnBoarding();
                    break;
            }
        });
        mainViewModel.isLoading.observe(this, isLoading ->{
            updateLoadingScreen(isLoading);
        });
    }

    /** Show / hide loading animation
     * @param isLoading is true when waiting for data from server
     */
    public void updateLoadingScreen(Boolean isLoading) {
        if(isLoading){
            loadingScreen.show();
        } else {
            loadingScreen.hide();
        }
    }

    private void openOnBoarding(){
        Intent intent = new Intent(this, OnBoardingActivity.class);
        startActivity(intent);
        finish();
    }
}