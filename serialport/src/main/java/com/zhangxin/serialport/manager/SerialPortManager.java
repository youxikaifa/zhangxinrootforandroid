package com.zhangxin.serialport.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;


import com.zhangxin.serialport.api.SerialPort;
import com.zhangxin.serialport.thread.ReadThread;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerialPortManager extends SerialPort {

    private static final String TAG = SerialPortManager.class.getSimpleName();
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;
    private FileDescriptor mFd;
    private OnSerialPortOpenListener mOnSerialPortOpenListener;
    private OnSerialPortIOListener mOnSerialPortIOListener;

    private HandlerThread mSendingHandlerThread;
    private Handler mSendingHandler;
    private ReadThread mSerialPortReadThread;
    public static SerialPortManager manager;

    public static SerialPortManager getInstance() {
        if (manager == null) {
            manager = new SerialPortManager();
        }
        return manager;
    }


    /**
     * open
     * @param device
     * @param baudRate
     * @return
     */
    public boolean openSerialPort(File device, int baudRate) {

        Log.i(TAG, "open SerialPort: " + String.format("open %s  baudrate %s", device.getAbsolutePath(), baudRate));

        // 校验串口权限
        if (!device.canRead() || !device.canWrite()) {
            boolean chmod777 = chmod777(device);
            if (!chmod777) {
                Log.i(TAG, "openSerialPort: no read write auth.");
                if (null != mOnSerialPortOpenListener) {
                    mOnSerialPortOpenListener.onFailure(device, OnSerialPortOpenListener.Status.NO_READ_WRITE_PERMISSION);
                }
                return false;
            }
        }

        try {
            mFd = open(device.getAbsolutePath(), baudRate, 0);
            mFileInputStream = new FileInputStream(mFd);
            mFileOutputStream = new FileOutputStream(mFd);
            Log.i(TAG, "openSerialPort: 串口已经打开 " + mFd);
            if (null != mOnSerialPortOpenListener) {
                mOnSerialPortOpenListener.onSucceed(device);
            }
            startSendThread();
            startReadThread();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (null != mOnSerialPortOpenListener) {
                mOnSerialPortOpenListener.onFailure(device, OnSerialPortOpenListener.Status.OPEN_FAIL);
            }
        }
        return false;
    }

    /**
     * 关闭串口
     */
    public void closeSerialPort() {

        if (null != mFd) {
            close();
            mFd = null;
        }
        stopSendThread();
        stopReadThread();

        if (null != mFileInputStream) {
            try {
                mFileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFileInputStream = null;
        }

        if (null != mFileOutputStream) {
            try {
                mFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFileOutputStream = null;
        }

        mOnSerialPortOpenListener = null;

        mOnSerialPortIOListener = null;
    }

    /**
     *
     * @param listener listener
     * @return SerialPortManager
     */
    public SerialPortManager setOnOpenSerialPortListener(OnSerialPortOpenListener listener) {
        this.mOnSerialPortOpenListener = listener;
        return this;
    }

    /**
     *
     * @param listener listener
     * @return SerialPortManager
     */
    public SerialPortManager setOnSerialPortIOListener(OnSerialPortIOListener listener) {
        mOnSerialPortIOListener = listener;
        return this;
    }

    /**
     * start send thread
     */
    private void startSendThread() {
        mSendingHandlerThread = new HandlerThread("mSendingHandlerThread");
        mSendingHandlerThread.start();
        mSendingHandler = new Handler(mSendingHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                byte[] sendBytes = (byte[]) msg.obj;

                if (null != mFileOutputStream && null != sendBytes && 0 < sendBytes.length) {
                    try {
                        mFileOutputStream.write(sendBytes);
                        mFileOutputStream.flush();
                        if (null != mOnSerialPortIOListener) {
                            mOnSerialPortIOListener.onDataSent(sendBytes);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    /**
     * stop send thread
     */
    private void stopSendThread() {
        mSendingHandler = null;
        if (null != mSendingHandlerThread) {
            mSendingHandlerThread.interrupt();
            mSendingHandlerThread.quit();
            mSendingHandlerThread = null;
        }
    }

    /**
     * start read thread
     */
    private void startReadThread() {
        mSerialPortReadThread = new ReadThread(mFileInputStream) {
            @Override
            public void onDataReceived(byte[] bytes) {
//                Log.i(TAG, "manager onDataReceived: "+ByteUtil.byteArrayToString(bytes));
                if (null != mOnSerialPortIOListener) {
                    mOnSerialPortIOListener.onDataReceived(bytes);
                }
            }
        };
        mSerialPortReadThread.start();
    }

    /**
     * stop receive thread
     */
    private void stopReadThread() {
        if (null != mSerialPortReadThread) {
            mSerialPortReadThread.release();
        }
    }

    /**
     *
     * @param sendBytes
     * @return
     */
    public boolean sendBytes(byte[] sendBytes) {
        if (null != mFd && null != mFileInputStream && null != mFileOutputStream) {
            if (null != mSendingHandler) {
                Message message = Message.obtain();
                message.obj = sendBytes;
                return mSendingHandler.sendMessage(message);
            }
        }
        return false;
    }

    public interface OnSerialPortOpenListener {
        /**
         * @param device
         */
        void onSucceed(File device);

        /**
         * @param device
         * @param status
         */
        void onFailure(File device, Status status);

        enum Status {
            NO_READ_WRITE_PERMISSION, OPEN_FAIL
        }
    }

    public interface OnSerialPortIOListener {
        /**
         */
        void onDataReceived(byte[] bytes);


        /**
         */
        void onDataSent(byte[] bytes);
    }
}
