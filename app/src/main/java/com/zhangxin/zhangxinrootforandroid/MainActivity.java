package com.zhangxin.zhangxinrootforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zhangxin.serialport.api.SerialPortFinder;
import com.zhangxin.serialport.dto.DeviceDto;
import com.zhangxin.serialport.manager.SerialPortManager;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SerialPortManager.OnSerialPortIOListener, SerialPortManager.OnSerialPortOpenListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SerialPortFinder serialPortFinder = new SerialPortFinder();

        ArrayList<DeviceDto> devices = serialPortFinder.getDevices();

        SerialPortManager.getInstance().setOnSerialPortIOListener(this).setOnOpenSerialPortListener(this).openSerialPort(devices.get(4).getFile(),115200);
    }

    @Override
    public void onDataReceived(byte[] bytes) {
        
    }

    @Override
    public void onDataSent(byte[] bytes) {

    }

    @Override
    public void onSucceed(File device) {
        Log.i(TAG, "onSucceed: ");
    }

    @Override
    public void onFailure(File device, Status status) {
        Log.i(TAG, "onFailure: ");
    }
}
