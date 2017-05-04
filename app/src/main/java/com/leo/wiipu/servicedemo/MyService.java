package com.leo.wiipu.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private IBinderImple binderImple=new IBinderImple();

    public MyService() {
    }

    public class IBinderImple extends Binder {
        public MyService getMyService() {
            Log.v("Service", "getMyService");
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("Service","onBind");
        return binderImple;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d("Service","onCreate");
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Thread","run");
            }
        }).start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Service","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("Service","onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("Service","onDestroy");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("Service","onRebind");
        super.onRebind(intent);
    }
}
