package com.example.m.pra9boundmess;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by m on 2016-07-05.
 */
public class MyService extends Service {
    static final int JOB1 = 1;
    static final int JOB2 = 2;
    Messenger messenger =new Messenger(new IcomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Uwaga","OnBind");
        Toast.makeText(getApplicationContext(),"getBinder...",Toast.LENGTH_LONG).show();
        return messenger.getBinder();
    }

    public class IcomingHandler extends Handler{


        @Override
        public void handleMessage(Message msg) {

            Log.d("Uwaga","handleMessagebeforeSwitch");
            switch (msg.what){
                case 1:
                    Log.d("Uwaga","handlecase1");
                    Toast.makeText(getApplicationContext(),"hello job1",Toast.LENGTH_LONG).show();
                break;
                case 2:
                    Log.d("Uwaga","handlecase2");
                    Toast.makeText(getApplicationContext(),"hello job2",Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
