package com.leo.wiipu.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button start,bind,unbiand,startCommand;
    MyService.IBinderImple binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button) findViewById(R.id.start);
        bind= (Button) findViewById(R.id.bind);
        unbiand= (Button) findViewById(R.id.unbind);
        startCommand= (Button) findViewById(R.id.start_command);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,MyService.class));
            }
        });

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(MainActivity.this,MyService.class),conn, Context.BIND_AUTO_CREATE);
            }
        });

        startCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,MyService.class));
            }
        });

        unbiand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(conn!=null)
            unbindService(conn);
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            binder = (MyService.IBinderImple) service;//得到LocalBinder实例
            MyService bindService = binder.getMyService();

            //myService = binder.getService();//得到Service实例
            //设置接口回调获取Service中的数据
            /*myService.setOnDataCallback(new MyService.OnDataCallback() {
                @Override
                public void onDataChange( final String message) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(message);
                        }
                    });
                }
            });*/

        }
    };
}
