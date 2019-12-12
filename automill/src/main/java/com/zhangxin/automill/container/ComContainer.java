package com.zhangxin.automill.container;

import com.zhangxin.automill.common.MachineProtocol;

public class ComContainer {

    public static String cmd = "";
    public static MachineProtocol protocol = new MachineProtocol();

    public static void setCmd(String machineCmd){
        cmd = machineCmd;
    }

    public static String getCmdBody(){
        return protocol.translateCmd2Code(cmd);
    }
}
