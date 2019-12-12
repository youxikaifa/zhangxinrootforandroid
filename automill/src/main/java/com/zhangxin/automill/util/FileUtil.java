package com.zhangxin.automill.util;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtil {
    protected static final String TAG = FileUtil.class.getSimpleName();
    private static final String LOCAL_PATH = Environment.getExternalStorageDirectory().getPath();

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        //达到最大文件数量，则删除日期靠前的文件
        try {
            checkFile(filePath);
        }catch (Exception e){
            Log.i(TAG, "makeFilePath: "+e.getLocalizedMessage());
        }
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(LOCAL_PATH + filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            Log.i(TAG, "makeFilePath: " + e);
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    private static void makeRootDirectory(String filePath) {
        File file;
        try {
            file = new File(LOCAL_PATH + filePath);
            if (!file.exists()) {
                file.mkdirs();

            }
        } catch (Exception e) {
            Log.i(TAG, "makeRootDirectory: " + e);
        }
    }

    private static void checkFile(String filePath) {
        File file = new File(LOCAL_PATH + filePath);
        if (file.list().length == 10) {
            String[] files = file.list();
            File f = new File(LOCAL_PATH + filePath + files[0]);
            f.delete();
        }
    }

    public static List<String> read(String filePath, String fileName) {
        String msg;
        List<String> ads = new ArrayList<>();
        File file = makeFilePath(filePath, fileName);
        try {
            InputStream inStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inStream);
            BufferedReader bufferReader = new BufferedReader(inputReader);

            while ((msg = bufferReader.readLine()) != null) {
                System.out.println(msg);//打印从标准输入读取到的数据
                ads.add(msg);
            }
            inStream.close();
            inputReader.close();
            bufferReader.close();
        } catch (IOException e) {
            Log.i(TAG, "readFirstLine: " + e);
        }
        return ads;
    }

    public static void write(String filePath, String fileName, List<String> ads) {
        File file = makeFilePath(filePath, fileName);
        /**以读写的方式建立一个RandomAccessFile对象**/
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(file, "rw");
            //将记录指针移动到文件开始
            raf.seek(0);
            for (int i = 0; i < ads.size(); i++) {
                raf.write(ads.get(i).getBytes());
                raf.writeBytes("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void appendLastLine(String filePath, String fileName, String line) {

        File file = makeFilePath(filePath, fileName);

        FileOutputStream outSTr;
        try {
            outSTr = new FileOutputStream(file, true);
            outSTr.write(line.getBytes());
            outSTr.write(Objects.requireNonNull(System.getProperty("line.separator")).getBytes());
            outSTr.flush();
            outSTr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void modifyFirstLine(String filePath, String fileName) {
        File file = makeFilePath(filePath, fileName);
        String oldLine = readFirstLine(file);
        long count = Long.parseLong(oldLine.substring(7));
        String newLine = oldLine.substring(0, 7) + (count + 1);
        writeFirstLine(file, newLine);
    }

    public static String readFirstLine(File file) {
        String firstLine;
        try {
            InputStream inStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inStream);
            BufferedReader bufferReader = new BufferedReader(inputReader);
            firstLine = bufferReader.readLine();
            if (firstLine == null) {
                firstLine = "累计碾米总数:" + 0;
            }
            inStream.close();
            inputReader.close();
            bufferReader.close();
            return firstLine;
        } catch (IOException e) {
            firstLine = "";
            Log.i(TAG, "readFirstLine: " + e);
        }
        return firstLine;
    }


    private static void writeFirstLine(File file, String newLine) {
        String count = newLine.substring(7);

        /**以读写的方式建立一个RandomAccessFile对象**/
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(file, "rw");
            //将记录指针移动到文件最后
            raf.seek(0);
            raf.write(newLine.getBytes());
            if (Long.parseLong(count) != 0) {
                raf.write(System.getProperty("line.separator").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
