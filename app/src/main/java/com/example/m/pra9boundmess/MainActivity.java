package com.example.m.pra9boundmess;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Messenger messanger;
    boolean isBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Uwaga","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
        Log.d("Uwaga","startService");
        Intent i = new Intent(this,MyService.class);
        bindService(i,sc,BIND_AUTO_CREATE);
    }

    public void sayHello(View view) {
        Log.d("Uwaga","sayHello");
        Message enger = Message.obtain(null,MyService.JOB1,0,0,0);

        try {
            Log.d("Uwaga","sendHello");
            messanger.send(enger);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sayHelloAgain(View view) {
        Log.d("Uwaga","sayHelloAgain");
        Message enger2 = Message.obtain(null,MyService.JOB2,0,0,0);

        try {
            Log.d("Uwaga","sendHelloAgain");
            messanger.send(enger2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Uwaga","serciveConnected");

            messanger = (new Messenger(service));
            isBind=true;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("Uwaga","sercivedisconetced");
            messanger = null;
            isBind = false;

        }
    };

    @Override
    protected void onStop() {
        Log.d("Uwaga","stop");
        unbindService(sc);
        isBind = false;
        messanger = null;
        super.onStop();
    }
}
