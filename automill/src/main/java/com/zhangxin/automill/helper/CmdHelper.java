package com.zhangxin.automill.helper;

public class CmdHelper {

    public static String checkCmdForMicro(String cmd) {
        if (cmd.length() < 10) {
            return null;
        } else {
            int start = cmd.indexOf("5AA5");
            int dataLength = Integer.parseInt(cmd.substring(start + 4, start + 6), 16);
            int end = 2 * dataLength + 6;
            if (cmd.length() >= end + start) {
                return cmd.substring(start, end + start);
            } else {
                return null;
            }
        }
    }

    public static String checkCmdForCard(String cmd) {
        if (cmd.length()>6){
            int start = cmd.indexOf("AA");
            int dataLength = Integer.parseInt(cmd.substring(start + 2, start + 4), 16);
            int end = 2 * dataLength + 4;
            if (cmd.length() >= end + start) {
                return cmd.substring(start, end + start);
            } else {
                return null;
            }
        }else{
            return null;
        }

    }
}
