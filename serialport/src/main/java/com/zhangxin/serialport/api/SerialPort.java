package com.zhangxin.serialport.api;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class SerialPort {

    static {
        System.loadLibrary("serialport");
    }

    private static final String TAG = SerialPort.class.getSimpleName();

    /**
     * @param file
     * @return
     */
    public boolean chmod777(File file) {
        if (null == file || !file.exists()) {
            return false;
        }
        try {
            Process su = Runtime.getRuntime().exec("/system/bin/su");
            String cmd = "chmod 777 " + file.getAbsolutePath() + "\n" + "exit\n";
            su.getOutputStream().write(cmd.getBytes());
            if (0 == su.waitFor() && file.canRead() && file.canWrite() && file.canExecute()) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected native FileDescriptor open(String path, int baudRate, int flags);

    protected native void close();
}
