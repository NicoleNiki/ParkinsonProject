package com.example.parkinson.features.notification;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ParkinsonApplication;
import com.example.parkinson.R;
import com.example.parkinson.common.OnSwipeTouchListener;
import com.example.parkinson.di.ApplicationComponent;
import com.example.parkinson.di.MainComponent;
import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.features.main.MainViewModel;
import com.example.parkinson.model.enums.EStatus;

import javax.inject.Inject;

import static android.content.Context.SENSOR_SERVICE;


public class NotificationActivity extends AppCompatActivity {
    public ApplicationComponent applicationComponent;

    @Inject
    NotificationViewModel notificationViewModel;

    FrameLayout notificationBtn;
    ConstraintLayout background;
    SensorManager manager;
    Sensor sensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((ParkinsonApplication) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        initUi();
    }

    private void initUi() {
        notificationBtn = findViewById(R.id.notificationFrame);
        background = findViewById(R.id.notification_background);
        initSwipeListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initSwipeListener() {
        notificationBtn.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                upReport();
            }

            public void onSwipeRight() {
                rightReport();
            }

            public void onSwipeLeft() {
                leftReport();
            }

            public void onSwipeBottom() {
                downReport();
            }
        });
    }

    private void upReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.Dyskinesia);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);
    }

    private void downReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_top_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.Hallucination);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);

    }

    private void leftReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_left_exit);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.Off);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);

    }

    private void rightReport() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_right_exit);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                notificationViewModel.updateReport(EStatus.On);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);
    }

    private void hideDescription() {
       findViewById(R.id.notificationHallucinationBtn).setVisibility(View.GONE);
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
