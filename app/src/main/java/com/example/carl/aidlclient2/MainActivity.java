package com.example.carl.aidlclient2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.carl.aidlservice2.remote.IStudentService;
import com.example.carl.aidlservice2.remote.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btbind = findViewById(R.id.btBind);
        btbind.setOnClickListener(this);
        Button btGetInfo = findViewById(R.id.btGetInfo);
        btGetInfo.setOnClickListener(this);
        Button btUnbind = findViewById(R.id.btUnbind);
        btUnbind.setOnClickListener(this);
    }

    private ServiceConnection conn ;
    private IStudentService studentService;
    private String TAG = "MainActivityC";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btBind:
                Log.i(TAG,"bind onclick.");
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.carl.aidlservice2",
                        "com.example.carl.aidlservice2.remote.MyService"));
                if(conn ==null){
                    conn = new ServiceConnection(){
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            Log.i(TAG,"on connected");
                            studentService = IStudentService.Stub.asInterface(service);
                        }
                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                        }
                    };
                    bindService(intent,conn, Context.BIND_AUTO_CREATE);

                    Toast.makeText(this,"绑定Service",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"已经绑定Service",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btGetInfo:
                try {
                      //studentService.sss();
                    Student s =studentService.getStudentById(3);
                    Log.i(TAG,"获取到的信息："+s.toString());
                    Toast.makeText(this,"获取信息",Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btUnbind:
                if(conn!=null){
                    unbindService(conn);
                    conn = null;
                }
                break;




        }
    }
}
