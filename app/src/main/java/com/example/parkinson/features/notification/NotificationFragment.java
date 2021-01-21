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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.parkinson.R;
import com.example.parkinson.common.OnSwipeTouchListener;
import com.example.parkinson.features.main.MainActivity;
import com.example.parkinson.features.main.MainViewModel;
import com.example.parkinson.model.enums.EStatus;

import javax.inject.Inject;

import static android.content.Context.SENSOR_SERVICE;


public class NotificationFragment extends DialogFragment {
    @Inject
    NotificationViewModel notificationViewModel;

    FrameLayout notificationBtn;
    ConstraintLayout background;
    SensorManager manager;
    Sensor sensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);
        ((MainActivity) getActivity()).mainComponent.inject(this);
        manager = (SensorManager) requireActivity().getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationBtn = view.findViewById(R.id.notificationFrame);
        background = view.findViewById(R.id.notification_background);
        initSwipeListener();

    }


    @SuppressLint("ClickableViewAccessibility")
    private void initSwipeListener() {
        notificationBtn.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
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
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_bottom_out);
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
                requireActivity().onBackPressed();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);
    }

    private void downReport() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_top_out);
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
                requireActivity().onBackPressed();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);

    }

    private void leftReport() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_exit);
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
                requireActivity().onBackPressed();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);

    }

    private void rightReport() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_exit);
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
                requireActivity().onBackPressed();
            }
        });
        hideDescription();
        notificationBtn.startAnimation(animation);
    }

    private void hideDescription() {
        getView().findViewById(R.id.notificationHallucinationBtn).setVisibility(View.GONE);
        getView().findViewById(R.id.notificationOffBtn).setVisibility(View.GONE);
        getView().findViewById(R.id.notificationOnBtn).setVisibility(View.GONE);
        getView().findViewById(R.id.notificationDyskinesiaBtn).setVisibility(View.GONE);
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//// I'm using null here because drawing nothing is faster than drawing transparent pixels.
//        getActivity().getWindow().setBackgroundDrawable(null);
//        getView().setBackground(null);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        getActivity().getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.black_20)));
//    }
}
