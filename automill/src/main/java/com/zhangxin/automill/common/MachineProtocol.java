package com.zhangxin.automill.common;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MachineProtocol {

    protected final String TAG = MachineProtocol.class.getSimpleName();
    private String msgHead = "5AA5";
    private int cmdBitLength = 2;
    public Map<Integer, String> protocols = new HashMap<>();

    public MachineProtocol() {
        super();
        protocols.put(0x00, "机器ID号码");                        //控制板ID
        protocols.put(0x01, "版本信息");                        //版本信息
        protocols.put(0x02, "横纵封当前温度");                    //当前机器状态
        protocols.put(0x03, "称重重量");                            //称重重量
        protocols.put(0x04, "当前总电流");                          //当前总电流
        protocols.put(0x05, "当前工作状态");                        //当前工作状态
        protocols.put(0x06, "报警状态");                              //报警
        protocols.put(0x07, "输出状况");                         //输出状况
        protocols.put(0x08, "接近开关状态");                     //接近开关状态
        protocols.put(0x09, "谷仓距离检测");                     //接近开关状态
        protocols.put(0x10, "关门延时时间");                      //关门延时时间
        protocols.put(0x11, "横封上限温度");                       //横封上限温度
        protocols.put(0x12, "纵封上限温度");                        //纵封上限温度
        protocols.put(0x13, "回差温度");                          //回差温度

        protocols.put(0x15, "重量补偿值");            //重量补偿值
        protocols.put(0x16, "精度步数");                //精度步数
        protocols.put(0x17, "精度步数");                //精度步数
        protocols.put(0x18, "精度步数");                //精度步数
        protocols.put(0x19, "精度步数");                //精度步数
        protocols.put(0x1A, "精度步数");                //精度步数
        protocols.put(0x1B, "精度步数");                //精度步数
        protocols.put(0x1C, "精度步数");                //精度步数
        protocols.put(0x1D, "精度步数");                //精度步数
        protocols.put(0x1E, "精度步数");                //精度步数
        protocols.put(0x1F, "精度步数");                //精度步数

        protocols.put(0x20, "超温上限值");                        //超温上限值
        protocols.put(0x21, "加热时间上限值");                //加热时间上限值（s）
        protocols.put(0x22, "碾米电机运动时间上限值");            //碾米电机运动时间上限值（s）
        protocols.put(0x23, "下米电机运动时间上限值");            //下米电机运动时间上限值
        protocols.put(0x24, "拉膜电机运动时间上限值");            //拉膜电机运动时间上限值
        protocols.put(0x25, "封膜电机运动时间上限值");            //封膜电机运动时间上限值
        protocols.put(0x26, "开门最大时间");                    //开门最大时间
        protocols.put(0x27, "关门最大时间");                    //关门最大时间
        protocols.put(0x28, "最大取货时间");                    //最大取货时间
        protocols.put(0x29, "送谷电机运动时间上限值");            //送谷电机运动时间上限值
        protocols.put(0x2A, "送膜电机运动时间上限值");            //送膜电机运动时间上限值
        protocols.put(0x2B, "精度电机最大复位步数");            //送膜电机运动时间上限值
        protocols.put(0x2C, "糠满超时");

        protocols.put(0x30, "总电流限制");            //总电流限制
        protocols.put(0x31, "碾米电机电流上限");        //碾米电机电流上限
        protocols.put(0x32, "送谷电机电流上限");        //送谷电机电流上限
        protocols.put(0x33, "拉膜电机电流上限");        //拉膜电机电流上限
        protocols.put(0x34, "封膜电机电流上限");        //封膜电机电流上限
        protocols.put(0x35, "开关门电机电流上限");            //开关门电机电流上限

        protocols.put(0x41, "系统复位");                        //系统复位
        protocols.put(0x42, "系统重启");                        //系统重启
        protocols.put(0x43, "配置精度");                    //配置精度
        protocols.put(0x44, "开始碾米命令");                //开始碾米命令
        protocols.put(0x45, "仅包装");                    //仅包装
        protocols.put(0x46, "错误清除");                        //开始暂停
        protocols.put(0x47, "恒温开关");                    //恒温开关
        protocols.put(0x48, "重量传感器标定");            //重量传感器标定
        protocols.put(0x49, "标定电流");                 //标定电流
        protocols.put(0x4a, "标定温度");                //标定温度

        protocols.put(0x50, "切换到手动模式");                    //切换到手动模式
        protocols.put(0x51, "碾米电机动作");                //碾米电机动作
        protocols.put(0x52, "运行下米动作");                //运行下米动作
        protocols.put(0x53, "拉膜电机运行");                //拉膜电机运行，参数1表示拉膜电机运行模式，参数2表示运行参数
        protocols.put(0x54, "封膜电机运行");                //封膜电机运行
        protocols.put(0x55, "吹糠电机运行");                //吹糠电机运行
        protocols.put(0x56, "送谷电机运行");                //送谷电机运行
        protocols.put(0x57, "送膜电机运行");                //送膜电机运行
        protocols.put(0x58, "精度电机运转");                //精度电机运转
        protocols.put(0x59, "开关门电机运转");                    //开关门电机运转
        protocols.put(0x5a, "纵封加热");                    //纵封加热
        protocols.put(0x5b, "横封加热");                    //横封加热
    }


    /**
     * 生成命令主体
     *
     * @param cmd
     * @return
     */
    public String translateCmd2Code(String cmd) {
        String cmdBody = "";
        cmd = cmd.toUpperCase();
        int start = cmd.indexOf(this.msgHead);
        if (start >= 0) {
            int headLength = this.msgHead.length();
            int length = Integer.parseInt(cmd.substring((start + headLength), (start + headLength + this.cmdBitLength)), 16);
            for (int i = 0; i < length - 2; i++) {
                cmdBody += cmd.substring((start + headLength + this.cmdBitLength + this.cmdBitLength * i), (start + headLength + this.cmdBitLength + (i + 1) * this.cmdBitLength));
            }
            return cmdBody;

        } else {
            return null;
        }

    }

    /**
     * 通过协议生成一对一的命令解释
     *
     * @param cmdBody
     * @return
     */
    public HashMap<String, String> translateCmd2Map(String cmdBody) {
        HashMap<String, String> msg = new HashMap<String, String>();
        //取命令的长度
        int length = cmdBody.length();
        //取寄存器起始地址，并将其从16进制字符转为10进制数字
        int regAdd = Integer.parseInt(cmdBody.substring(2, 6), 16);
        //写入命令的动作，82 或 83
        msg.put("操作", cmdBody.substring(0, 2));
        for (int i = this.cmdBitLength * 3; i < length; i += this.cmdBitLength * 2) {
            msg.put(this.protocols.get(regAdd++), cmdBody.substring(i, this.cmdBitLength * 2 + i));
        }
        return msg;
    }

    public boolean isAllCmd(String str) {
        str = str.toUpperCase();
        int start = str.indexOf(this.msgHead);
        if (start >= 0) {
            int headLength = this.msgHead.length();
            int length = Integer.parseInt(str.substring((start + headLength), (start + headLength + this.cmdBitLength)), 16);
            return (length + 3) * 2 < str.length();

        } else {
            return false;
        }
    }

    public HashMap<String, Integer> translateTest2Map(String cmdBody) {
        HashMap<String, Integer> msg = new HashMap<String, Integer>();
        try {
            //取命令的长度
            //int length = cmdBody.length();
            //写入命令的动作，82 或 83
            //msg.put("action",cmdBody.substring(0, 2));
            msg.put("横封上限温度", Integer.parseInt(cmdBody.substring(6, 10), 16));
            msg.put("纵封上限温度", Integer.parseInt(cmdBody.substring(10, 14), 16));
            msg.put("称重重量", Integer.parseInt(cmdBody.substring(14, 22), 16));
            msg.put("当前总电流", Integer.parseInt(cmdBody.substring(22, 30), 16));
            msg.put("机器状态", Integer.parseInt(cmdBody.substring(30, 34), 16));
            msg.put("细节状态", Integer.parseInt(cmdBody.substring(34, 38), 16));
        } catch (Exception e) {
            Log.i(TAG, "translateTest2Map: " + e.getMessage());
        }
        return msg;
    }

    public HashMap<String, Long> translateHeart2Map(String cmdBody) {
        HashMap<String, Long> msg = new HashMap<String, Long>();
        //取命令的长度
        //int length = cmdBody.length();
        //写入命令的动作，82 或 83
        //msg.put("action",cmdBody.substring(0, 2));
        try {
            msg.put("hTemp", Long.parseLong(cmdBody.substring(6 + 6, 10 + 6), 16));            //横封温度
            msg.put("vTemp", Long.parseLong(cmdBody.substring(10 + 6, 14 + 6), 16));            //纵封温度
            msg.put("weight", Long.parseLong(cmdBody.substring(14 + 6, 22 + 6), 16));            //称重重量
            msg.put("electricity", Long.parseLong(cmdBody.substring(22 + 6, 30 + 6), 16));    //当前总电流
            msg.put("mainStatus", Long.parseLong(cmdBody.substring(30 + 6, 34 + 6), 16));        //当前工作状态
            msg.put("detailStatus", Long.parseLong(cmdBody.substring(34 + 6, 38 + 6), 16));    //碾米状态下的细节状态
            msg.put("warnValue", Long.parseLong(cmdBody.substring(38 + 6, 46 + 6), 16));        //警报
            msg.put("outStatus", Long.parseLong(cmdBody.substring(46 + 6, 54 + 6), 16));        //输出状况
            msg.put("switchStatus", Long.parseLong(cmdBody.substring(54 + 6, 62 + 6), 16));    //接近开关状态
        } catch (Exception e) {
            Log.i(TAG, "translateHeart2Map: " + e.getMessage());
        }

        return msg;
    }

    /**
     * 解析整机状态
     *
     * @param cmdMap
     * @return
     */
    public String analyMainStatusValue(HashMap<String, Long> cmdMap) {
        String mainStatus = "";
        try {
            String v16 = String.format("%04x", cmdMap.get("mainStatus"));
            mainStatus = v16;
            if (v16.equals("0000")) {
                mainStatus = "上电";
            } else if (v16.equals("0001")) {
                mainStatus = "复位";
            } else if (v16.equals("0002")) {
                mainStatus = "待机";
            } else if (v16.equals("0003")) {
                mainStatus = "碾米中";
                String detailStatus = String.format("%04x", cmdMap.get("detailStatus"));
                if (detailStatus.equals("0001")) {
                    detailStatus = "调整精度";
                } else if (detailStatus.equals("0002")) {
                    detailStatus = "碾米";
                } else if (detailStatus.equals("0003")) {
                    detailStatus = "下米";
                } else if (detailStatus.equals("0004")) {
                    detailStatus = "拉膜";
                } else if (detailStatus.equals("0005")) {
                    detailStatus = "封膜";
                } else if (detailStatus.equals("0006")) {
                    detailStatus = "出货";
                }

                mainStatus = mainStatus + "，碾米细节：" + detailStatus;
            }
        } catch (Exception e) {
            Log.i(TAG, "analyMainStatusValue: " + e.getMessage());
        } finally {
            return mainStatus;
        }


    }

    /**
     * 解析警报参数
     *
     * @param v
     * @return
     */
    public String analyseWarnValue(Long v) {
        String v32 = get32BitBinString(v);
        String warn = "";
        try {
            if (v32.charAt(31) == '1') {
                warn += "超温上限，";
            }
            if (v32.charAt(30) == '1') {
                warn += "加热超超时，";
            }
            if (v32.charAt(29) == '1') {
                warn += "碾米超时，";
            }
            if (v32.charAt(28) == '1') {
                warn += "下米超时，";
            }
            if (v32.charAt(27) == '1') {
                warn += "拉膜超时，";
            }
            if (v32.charAt(26) == '1') {
                warn += "封膜超时，";
            }
            if (v32.charAt(25) == '1') {
                warn += "开门超时，";
            }
            if (v32.charAt(24) == '1') {
                warn += "关门超时，";
            }
            if (v32.charAt(23) == '1') {
                warn += "取货超时，";
            }
            if (v32.charAt(22) == '1') {
                warn += "送谷超时，";
            }
            if (v32.charAt(21) == '1') {
                warn += "送膜超时，";
            }
            if (v32.charAt(20) == '1') {
                warn += "精度超时，";
            }
            if (v32.charAt(19) == '1') {
                warn += "电流过载，";
            }
            if (v32.charAt(18) == '1') {
                warn += "碾米电机电流上限，";
            }
            if (v32.charAt(17) == '1') {
                warn += "送谷电机电流上限，";
            }
            if (v32.charAt(16) == '1') {
                warn += "拉膜电机电流上限，";
            }
            if (v32.charAt(15) == '1') {
                warn += "封膜电机电流上限，";
            }
            if (v32.charAt(14) == '1') {
                warn += "开关门电机电流上限，";
            }
        } catch (Exception e) {
            Log.i(TAG, "analyseWarnValue: " + e.getMessage());
        }
        if (warn.length() > 0) {
            warn = warn.substring(0, warn.length() - 1);
        }
        return warn;
    }

    /**
     * 解析输出状态
     *
     * @param v
     * @return
     */
    public String analysisOutStatusValue(Long v) {
        String ret = "";
        try {
            //先转换成二进制
            String v2 = Long.toBinaryString(v);
            StringBuilder base = new StringBuilder("0000000000000000");
            int len = v2.length();
            String outStatus = base.replace(base.length() - len, 16, v2).toString();

            if (outStatus.substring(15, 16).equals("1")) {
                ret += "横封电机正在运行 ,";
            }
            if (outStatus.substring(14, 15).equals("1")) {
                ret += "纵封电机正在运行 ,";
            }
            if (outStatus.substring(13, 14).equals("1")) {
                ret += "包装电机正在运行 ,";
            }
            if (outStatus.substring(12, 13).equals("1")) {
                ret += "送膜电机正在运行 ,";
            }
            if (outStatus.substring(11, 12).equals("1")) {
                ret += "下米电机正在运行 ,";
            }
            if (outStatus.substring(10, 11).equals("1")) {
                ret += "吹糠电机正在运行 ,";
            }
            if (outStatus.substring(9, 10).equals("1")) {
                ret += "送谷电机正在运行 ,";
            }
            if (outStatus.substring(8, 9).equals("1")) {
                ret += "拉膜电机正在运行 ,";
            }

            if (outStatus.substring(7, 8).equals("1")) {
                ret += "备用 ,";
            }

            if (outStatus.substring(6, 7).equals("1")) {
                ret += "开关门电机(开) ,";
            }
            if (outStatus.substring(6, 7).equals("0")) {
                ret += "开关门电机(关) ,";
            }

            if (outStatus.substring(5, 6).equals("1")) {
                ret += "开关门电机(开) ,";
            }
            if (outStatus.substring(4, 5).equals("1")) {
                ret += "精度电机正在运行 ,";
            }
            if (outStatus.substring(3, 4).equals("1")) {
                ret += "碾米电机正在运行 ,";
            }


            if (ret.length() == 0) {
                ret = outStatus;
            } else {
                ret = ret.substring(0, ret.length() - 1);
            }
        } catch (Exception e) {
            Log.i(TAG, "analysisOutStatusValue: " + e.getMessage());
        }

        return ret;
    }

    /**
     * 解析接近开关状态
     *
     * @param v
     * @return
     */
    public String analySwitchStatusValue(Long v) {
        String ret = "";
        try {
            //先转换成二进制
            String v2 = Long.toBinaryString(v);
            StringBuilder base = new StringBuilder("0000000000000000");
            int len = v2.length();
            String switchStatus = base.replace(base.length() - len, 16, v2).toString();

            if (switchStatus.substring(15, 16).equals("0")) {
                ret += "包装电机复位点，";
            }
            if (switchStatus.substring(14, 15).equals("0")) {
                ret += "送膜电机触发点，";
            }
            if (switchStatus.substring(13, 14).equals("0")) {
                ret += "下米电机复位点，";
            }
            if (switchStatus.substring(12, 13).equals("0")) {
                ret += "糠满触发，";
            }
            if (switchStatus.substring(11, 12).equals("0")) {
                ret += "缺谷触发，";
            }
            if (switchStatus.substring(10, 11).equals("0")) {
                ret += "拉膜触发，";
            }
            if (switchStatus.substring(9, 10).equals("0")) {
                ret += "精度电机触发，";
            }
            if (switchStatus.substring(8, 9).equals("0")) {
                ret += "开门触发，";
            }
            if (switchStatus.substring(7, 8).equals("0")) {
                ret += "关门触发，";
            }
            if (switchStatus.substring(6, 7).equals("0")) {
                ret += "取货触发，";
            }
            if (switchStatus.substring(5, 6).equals("0")) {
                ret += "距离触发，";
            }
            if (switchStatus.substring(4, 5).equals("1")) {
                ret += "人体感应出发，";
            }


            if (ret.length() == 0) {
                ret = switchStatus;
            } else {
                ret = ret.substring(0, ret.length() - 1);
            }
        } catch (Exception e) {
            Log.i(TAG, "analySwitchStatusValue: " + e.getMessage());
        }


        return ret;
    }

    private String get32BitBinString(long number) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sBuilder.append(number & 1);
            number = number >>> 1;
        }
        return sBuilder.reverse().toString();
    }
}
