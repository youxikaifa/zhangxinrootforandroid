package com.zhangxin.serialport.api;

import android.util.Log;

import com.zhangxin.serialport.driver.DeviceDriver;
import com.zhangxin.serialport.dto.DeviceDto;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class SerialPortFinder {

    private static final String TAG = SerialPortFinder.class.getSimpleName();
    private static final String DRIVERS_PATH = "/proc/tty/drivers";
    private static final String SERIAL_FIELD = "serial";

    public SerialPortFinder() {
        File file = new File(DRIVERS_PATH);
        boolean b = file.canRead();
        Log.i(TAG, "SerialPortFinder: file.canRead() = " + b);
    }

    /**
     * get Drivers
     *
     * @return Drivers
     * @throws IOException IOException
     */
    private ArrayList<DeviceDriver> getDrivers() throws IOException {
        ArrayList<DeviceDriver> drivers = new ArrayList<>();
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(DRIVERS_PATH));
        String readLine;
        while ((readLine = lineNumberReader.readLine()) != null) {
            String driverName = readLine.substring(0, 0x15).trim();
            String[] fields = readLine.split(" +");
            if ((fields.length >= 5) && (fields[fields.length - 1].equals(SERIAL_FIELD))) {
                Log.d(TAG, "Found new com.zhangxin.serialport.driver " + driverName + " on " + fields[fields.length - 4]);
                drivers.add(new DeviceDriver(driverName, fields[fields.length - 4]));
            }
        }
        return drivers;
    }

    /**
     * get Device
     *
     * @return device
     */
    public ArrayList<DeviceDto> getDevices() {
        ArrayList<DeviceDto> devices = new ArrayList<>();
        try {
            ArrayList<DeviceDriver> drivers = getDrivers();
            for (DeviceDriver driver : drivers) {
                String driverName = driver.getName();
                ArrayList<File> driverDevices = driver.getDevices();
                for (File file : driverDevices) {
                    String devicesName = file.getName();
                    if (devicesName.equals("ttyS0")) {
                        devices.add(new DeviceDto("串口0", driverName, file));
                    } else if (devicesName.equals("ttyS1")) {
                        devices.add(new DeviceDto("串口1", driverName, file));
                    } else if (devicesName.equals("ttyS2")) {
                        devices.add(new DeviceDto("串口2", driverName, file));
                    } else if (devicesName.equals("ttyS3")) {
                        devices.add(new DeviceDto("串口3", driverName, file));
                    } else if (devicesName.equals("ttyS4")) {
                        devices.add(new DeviceDto("串口4", driverName, file));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devices;
    }
}