package com.example.parkinson.features.notification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.di.ApplicationComponent;
import com.example.parkinson.model.enums.EStatus;

import javax.inject.Inject;


public class NotificationActivity extends AppCompatActivity {
    public ApplicationComponent applicationComponent;

    EStatus chosenStatus;

    @Inject
    NotificationViewModel notificationViewModel;

    FrameLayout notificationBtn;
    ConstraintLayout background;
    SensorManager manager;
    Sensor sensor;
    TextView onnBtn,offBtn,dyskinesiaBtn,reportBtn;
    CheckBox isHallucinations;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((ParkinsonApplication) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);
        //manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        initUi();
    }

    private void initUi() {
        //notificationBtn = findViewById(R.id.notificationFrame);
        background = findViewById(R.id.notification_background);
        initBtnListenrs();
        //initSwipeListener();
    }

    private void initBtnListenrs() {
        reportBtn = findViewById(R.id.reportStatusBtn);
        offBtn = findViewById(R.id.notificationOffBtn);
        onnBtn = findViewById(R.id.notificationOnBtn);
        dyskinesiaBtn = findViewById(R.id.notificationDyskinesiaBtn);
        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenStatus == null || chosenStatus!= EStatus.Off) {
                    view.setBackgroundColor(Color.GREEN);
                    chosenStatus = EStatus.Off;
                } else {
                    view.setBackgroundColor(Color.WHITE);
                    chosenStatus = null;
                }
                view.setBackgroundColor(Color.GREEN);
                onnBtn.setBackgroundColor(Color.WHITE);
                dyskinesiaBtn.setBackgroundColor(Color.WHITE);
            }
        });

        onnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chosenStatus == null || chosenStatus!= EStatus.On) {
                    view.setBackgroundColor(Color.GREEN);
                    chosenStatus = EStatus.On;
                } else {
                    view.setBackgroundColor(Color.WHITE);
                    chosenStatus = null;
                }
                offBtn.setBackgroundColor(Color.WHITE);
                dyskinesiaBtn.setBackgroundColor(Color.WHITE);
            }
        });

        dyskinesiaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenStatus == null || chosenStatus!= EStatus.Dyskinesia) {
                    view.setBackgroundColor(Color.GREEN);
                    chosenStatus = EStatus.Dyskinesia;
                } else {
                    view.setBackgroundColor(Color.WHITE);
                    chosenStatus = null;
                }
                onnBtn.setBackgroundColor(Color.WHITE);
                offBtn.setBackgroundColor(Color.WHITE);
            }
        });

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportToServer();
            }
        });

        isHallucinations = findViewById(R.id.isHallucinationsBtn);
//        onnBtn = findViewById(R.id.notificationOnBtn);
//        dyskinesiaBtn = findViewById(R.id.notificationDyskinesiaBtn);
    }

    private void reportToServer() {
        if (chosenStatus == null)
                return;

        switch (chosenStatus) {
            case On:
                notificationViewModel.updateReport(EStatus.On,isHallucinations.isChecked());
                break;
            case Off:
                notificationViewModel.updateReport(EStatus.Off,isHallucinations.isChecked());
                break;
            case Dyskinesia:
                notificationViewModel.updateReport(EStatus.Dyskinesia,isHallucinations.isChecked());
                break;
        }
        Intent intentService = new Intent(this, NotifServiceForground.class);
        startService(intentService);
        onBackPressed();
    }

    @SuppressLint("ClickableViewAccessibility")
//    private void initSwipeListener() {
//        notificationBtn.setOnTouchListener(new OnSwipeTouchListener(this) {
//            public void onSwipeTop() {
//                upReport();
//            }
//
//            public void onSwipeRight() {
//                rightReport();
//            }
//
//            public void onSwipeLeft() {
//                leftReport();
//            }
//
//            public void onSwipeBottom() {
//                downReport();
//            }
//        });
//    }

    private void upReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.Dyskinesia, isHallucinations.isChecked());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        //hideDescription();
        //notificationBtn.startAnimation(animation);
    }

    private void downReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_top_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.Hallucination, isHallucinations.isChecked());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        //hideDescription();
        //notificationBtn.startAnimation(animation);

    }

    private void leftReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_left_exit);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.Off, isHallucinations.isChecked());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        //hideDescription();
        //notificationBtn.startAnimation(animation);

    }

    private void rightReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_right_exit);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.On, isHallucinations.isChecked());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        //hideDescription();
        //notificationBtn.startAnimation(animation);
    }

    private void hideDescription() {
       //findViewById(R.id.notificationHallucinationBtn).setVisibility(View.GONE);
       findViewById(R.id.notificationOffBtn).setVisibility(View.GONE);
       findViewById(R.id.notificationOnBtn).setVisibility(View.GONE);
       findViewById(R.id.notificationDyskinesiaBtn).setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}
