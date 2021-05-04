package com.example.parkinson.features.brodacsts;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.parkinson.R;
import com.example.parkinson.features.notification.NotifManager;
import com.example.parkinson.model.general_models.Medicine;
import com.example.parkinson.model.general_models.Time;
import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class NotifService extends Service implements NotifManager.NotifMangerInteface {

    NotificationManager manager;

    private NotifManager notifManger = NotifManager.getNotifManager();

    public void postNotifaction(List<Medicine> medicines, int id,String notifHour) {
        if (medicines.isEmpty()) {
            return;
        }

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String channelId = null;
        if (Build.VERSION.SDK_INT >= 26) {
            channelId = "Notifaction";
            CharSequence channelName = "Medicine Report Chanel";
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(notificationChannel);
        }


        Intent replyIntent = new Intent(this, NotifService.class);
        replyIntent.putExtra("command","medicationReport");
        replyIntent.putExtra("notifHour",notifHour);
        PendingIntent replyPendingIntent = PendingIntent.getService(this,
                5, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                .setBigContentTitle("עליך לקחת את התרופות הבאות")
                .setSummaryText("תרופות לקחת");

        for (Medicine med : medicines)
        {
            inboxStyle.addLine(med.getName());
        }

        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_medical)
                .setContentTitle("תזכורת על לקיחת תרופות")
                .setContentText("עליך לדווח על התרופות של השעה : "+notifHour)
                .setStyle(inboxStyle)
                .addAction(R.mipmap.ic_launcher,"דיווח על לקיחת התרופות",replyPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        manager.notify(id, notification);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void setNotification(Intent intent, int notifId,String notifHour) {

        notifManger.getMedication(notifId,notifHour);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            return super.onStartCommand(intent, flags, startId);
        }

        notifManger.setListner(this);
        String command = intent.getStringExtra("command");
        int notifId = intent.getIntExtra("notifId",1);
        String notifHour = getCurrnetHour();
       // String notifHour = intent.getStringExtra("notifHour");
        if (command == null)
            return super.onStartCommand(intent, flags, startId);


        switch (command) {
            case "medicationReport":
               notifManger.report(notifId,notifHour);
               break;
            case "start Notifaction":
                setNotification(intent,notifId,notifHour);
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private String getCurrnetHour() {
        Calendar rightNow = Calendar.getInstance();
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightNow.get(Calendar.MINUTE);
        minutes = minutes < 30 ? 0 : 30;
        Time time = new Time(minutes,currentHourIn24Format);
        return time.toString();
    }

    @Override
    public void closeNotif(int id) {
        manager.cancel(id);
    }






    public void secondNotif(Intent intent) {

        String channelId = null;
        if (Build.VERSION.SDK_INT >= 26) {
            channelId = "Music chanel";
            CharSequence channelName = "Music Chanel";
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(notificationChannel);
        }

        String title = intent.getStringExtra("title");


//        RemoteInput remoteInput = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
//            remoteInput = new RemoteInput.Builder("key_text_reply")
//                    .setLabel("מדווח ...")
//                    .build();
//        }

        Intent replyIntent = new Intent(this, NotifService.class);
        replyIntent.putExtra("command","medicationReport");
        PendingIntent replyPendingIntent = PendingIntent.getService(this,
                0, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
//                R.drawable.ic_launcher_background,
//                "לקחתי את התרופה",
//                null
//        ).addRemoteInput(remoteInput).build();

        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title+"נוטיפקציה שנייה ")
                .setContentText("תזכורת על לקיחת תרופות")
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("This is line 1")
                        .addLine("This is line 2")
                        .addLine("This is line 3")
                        .addLine("This is line 4")
                        .addLine("This is line 5")
                        .addLine("This is line 6")
                        .addLine("This is line 7")
                        .setBigContentTitle("רשימת התקופות")
                        .setSummaryText("תרופות לקחת"))
                .addAction(R.mipmap.ic_launcher,"דיווח על לקיחת התרופות",replyPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        manager.notify(2, notification);

    }


    //todo old notif

    //        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        String channelId = null;
//        if (Build.VERSION.SDK_INT >= 26) {
//            channelId = "Notifaction";
//            CharSequence channelName = "Music Chanel";
//            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
//            manager.createNotificationChannel(notificationChannel);
//        }
//
//        String title = intent.getStringExtra("title");
//
//
//
//        Intent replyIntent = new Intent(this, NotifService.class);
//        replyIntent.putExtra("command","medicationReport");
//        PendingIntent replyPendingIntent = PendingIntent.getService(this,
//                0, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Notification notification = new NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle(title)
//                .setContentText("תזכורת על לקיחת תרופות")
//                .setStyle(new NotificationCompat.InboxStyle()
//                        .addLine("This is line 1")
//                        .addLine("This is line 2")
//                        .addLine("This is line 3")
//                        .addLine("This is line 4")
//                        .addLine("This is line 5")
//                        .addLine("This is line 6")
//                        .addLine("This is line 7")
//
//                        .setBigContentTitle("רשימת התקופות")
//                        .setSummaryText("תרופות לקחת"))
//                        .addAction(R.mipmap.ic_launcher,"דיווח על לקיחת התרופות",replyPendingIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .build();
//
//        manager.notify(NOTIF_ID, notification);
//
//        secondNotif(intent);


//        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
//        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent1);




//        builder = new NotificationCompat.Builder(this, channelId);
//
//        remoteViews = new RemoteViews(getPackageName(), R.layout.notifaication_layout);
//
//        Intent reportIntent = new Intent(this, MainActivity.class); // לשנות לכמוד דיוומ תרופה
//        //reportIntent.putExtra("medicationId", medication.getId());
//        reportIntent.putExtra("command", "medicationReport");
//        PendingIntent reportPendingIntent = PendingIntent.getService(this, 0, reportIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.notif_report_btn_id, reportPendingIntent);
//
//
//        builder.setCustomBigContentView(remoteViews);
//        builder.setSmallIcon(android.R.drawable.ic_media_play);
//        //builder.setCustomContentView(m_RemoteViews);
//        //manager.notify(NOTIF_ID,builder.build());






//        startForeground(NOTIF_ID, builder.build());

    //manager.notify(NOTIF_ID,builder.build());
}
