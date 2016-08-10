package rishabh.agarwal.holmes.timetable;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Holmes on 12/30/2015.
 */
public class Myservice extends Service {
    NotificationManager mgr;
    Intent in;
    PendingIntent pin;
    int i=0;
    String a="";


    @Override
    public void onCreate() {
        super.onCreate();
        mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        in=new Intent();
        a=  intent.getStringExtra("aaaa");
        pin = PendingIntent.getActivity(Myservice.this, 101, in, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification n=new Notification.Builder(Myservice.this).
                setContentTitle("Hey You Have A Class")
                .setContentText(a)

                .setSound(alarmSound)

                .setSmallIcon(R.drawable.ri)
                .setAutoCancel(true)
                .setContentIntent(pin)
                .setTicker(a)

                .build();

        // n.setLatestEventInfo(Myservice.this, "New Message",abc, pin);
        mgr.notify(++i, n);
        stopService(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
