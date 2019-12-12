package com.zhangxin.serialport.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Locale;

public class HexUtils {

    private static String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * bytearray to string
     *
     * @param results
     * @return
     */
    public static String ByteArrayToString(byte[] results) {
        StringBuilder sb = new StringBuilder();
        for (byte result : results) {
            //per byte to string
            sb.append(ByteToString(result));
        }
        return sb.toString();
    }

    /**
     * bytes to String
     *
     * @param b
     * @return
     */
    public static Object ByteToString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hex[d1] + hex[d2];
    }

    /**
     * byte merge
     *
     * @param bt1
     * @param bt2
     * @return
     */
    public static byte[] ByteMerger(byte[] bt1, byte[] bt2) {
        if (bt1 == null) {
            return bt2;
        } else {
            byte[] bt3 = new byte[bt1.length + bt2.length];
            System.arraycopy(bt1, 0, bt3, 0, bt1.length);
            System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
            return bt3;
        }

    }

    /**
     * hex char to byte[]
     *
     * @param inHex
     * @return
     */
    static public byte[] HexToByteArr(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (isOdd(hexlen) == 1) {//奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {//
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = HexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /**
     * @param num
     * @return
     */
    static public int isOdd(int num) {
        return num & 0x1;
    }

    /**
     * hex char to int
     *
     * @param inHex
     * @return
     */
    static public int HexToInt(String inHex) {
        return Integer.parseInt(inHex, 16);
    }

    /**
     * hex char to string
     *
     * @param inHex
     * @return
     */
    static public byte HexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * @param data
     * @param start start
     * @return
     */
    public static byte GetXor(byte[] data, int start) {

        byte temp = data[start];

        for (int i = start + 1; i < data.length; i++) {
            temp ^= data[i];
        }

        return temp;
    }

    public static int ByteToInt(byte b1, byte b2) {
        char c = (char) (((b1 & 0xFF) << 8) | (b2 & 0xFF));
        return (int) c;
    }

    public static float ByteToFloat(byte data1, byte data2, byte data3, byte data4) {
        byte[] mdata = {data1, data2, data3, data4};
        DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(mdata));
        float f = 0.0f;
        try {
            f = dataStream.readFloat();
            dataStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static byte StringToByte(String bit) {
        int re, len;
        if (null == bit) {
            return 0;
        }
        len = bit.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {//
            if (bit.charAt(0) == '0') {//
                re = Integer.parseInt(bit, 2);
            } else {//
                re = Integer.parseInt(bit, 2) - 256;
            }
        } else {//
            re = Integer.parseInt(bit, 2);
        }

        return (byte) re;
    }


    public static String ByteToHex(byte be) {
        switch (be) {
            case 10:
                return "a";
            case 11:
                return "b";
            case 12:
                return "c";
            case 13:
                return "d";
            case 14:
                return "e";
            case 15:
                return "f";
            default:
                return be + "";
        }
    }

    public static byte[] CharToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public static byte[] IntToByteArray(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    public static float GetFloat(byte data1, byte data2, byte data3, byte data4) {
        byte[] mdata = {data1, data2, data3, data4};
        DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(mdata));
        float f = 0.0f;
        try {
            f = dataStream.readFloat();
            dataStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    static public String ByteArrToHex(byte[] inBytArr) {
        StringBuilder strBuilder = new StringBuilder();
        int j = inBytArr.length;
        for (int i = 0; i < j; i++) {
            strBuilder.append(Byte2Hex(inBytArr[i]));
            strBuilder.append(" ");
        }
        return strBuilder.toString();
    }

    static public String Byte2Hex(Byte inByte) {
        return String.format("%02x", inByte).toUpperCase();
    }




    public static byte[] HexStr2Bytes(String src) {
        src = src.trim().replace(" ", "").toUpperCase(Locale.US);
        int m = 0, n = 0;
        int iLen = src.length() / 2; //
        byte[] ret = new byte[iLen]; //

        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
        }
        return ret;
    }

}
