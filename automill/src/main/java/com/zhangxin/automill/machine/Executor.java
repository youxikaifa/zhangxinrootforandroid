package com.zhangxin.automill.machine;

import com.zhangxin.automill.enums.MachineCmdEnum;
import com.zhangxin.serialport.manager.SerialPortManager;
import com.zhangxin.serialport.utils.HexUtils;

public class Executor {

    /**
     * 清除警报并待机
     */
    public static void ClearWarn() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.停止当前工作并待机.code()));
    }

    /**
     * 清除警报并继续工作
     */
    public static void ClearWarnAndWork() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.重置当前错误继续工作.code()));
    }

    /**
     * 获取机器当前状态
     */
    public static void getMachineSatus() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.当前工作状态.code()));
    }

    /**
     * 机器模式设置，1为手动模式,0为自动模式
     *
     * @param mode
     */
    public static void setMachineMode(int mode) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.命令模式.code(), mode)));
    }

    /**
     * 设置精度电机
     *
     * @param steps  步数
     * @param offset 每步分度
     */
    public static void setAccuracyMotor(int steps, int offset) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.精度步数设置.code(), steps, offset)));
    }

    /**
     * 设置横封温度
     *
     * @param hTemp
     */
    public static void setHtemp(int hTemp) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.设置横封温度.code(), hTemp)));
    }

    /**
     * 设置纵封温度
     *
     * @param zTemp
     */
    public static void setZtemp(int zTemp) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.设置纵封温度.code(), zTemp)));
    }

    /**
     * 设置回差温度
     *
     * @param offsetTemp
     */
    public static void setOffsetTemp(int offsetTemp) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.设置回差温度.code(), offsetTemp)));
    }

    /**
     * 标定温度
     *
     * @param temp
     */
    public static void checkTemp(int temp) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.标定温度.code(), temp)));
    }

    /**
     * 设置重量补偿值
     *
     * @param offset
     */
    public static void setOffsetWeight(int offset) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.设置重量补偿值.code(), offset)));
    }

    /**
     * 重量标定
     *
     * @param weight
     */
    public static void setWeight(int weight) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.重量标定.code(), weight)));
    }

    /**
     * 调节精度
     *
     * @param barn 谷仓 1为做谷仓，2为B谷仓
     * @param type 2糙米，5胚米，7精米
     */
    public static void adjustAccuracy(int barn, int type) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.精度步数调节.code(), barn, type)));
    }

    /**
     * 碾米
     *
     * @param makeNo 碾米数量
     * @param weight 每包重量
     */
    public static void mill(int makeNo, int weight) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.碾米.code(), makeNo, weight)));
    }


    /**
     * 设置送谷模式 0为自动送谷，1位不自动送谷
     *
     * @param mode
     */
    public static void setValleyMode(int mode) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.送谷模式.code(), mode)));
    }

    /**
     * 打开开关门模式
     */
    public static void openDoorMode() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.打开开关门模式.code()));
    }

    /**
     * 关闭开关门模式
     */
    public static void closeDoorMode() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.关闭开关门模式.code()));
    }

    /**
     * 打开电子锁
     */
    public static void openLockMode() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.打开电子锁模式.code()));
    }

    /**
     * 关闭电子锁
     */
    public static void closeLockMode() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.关闭电子锁模式.code()));
    }

    /**
     * 打开灯箱
     */
    public static void openLampBox() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.打开灯箱.code()));
    }

    /**
     * 关闭灯箱
     */
    public static void closeLampBox() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.关闭灯箱.code()));
    }

    /**
     * 设置温度超温警报
     *
     * @param temp
     * @param isDefault 是否设置为默认温度
     */
    public static void setWarnForTemp(int temp, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.温度超温警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.温度超温警报.code(), temp)));
        }
    }

    /**
     * 加热超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForHeatTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.加热超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.加热超时警报.code(), time)));
        }
    }

    /**
     * 碾米超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForMillTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.碾米超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.碾米超时警报.code(), time)));
        }
    }

    /**
     * 下米超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForXiamiTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.下米超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.下米超时警报.code(), time)));
        }
    }

    /**
     * 拉膜超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForLamoTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.拉膜超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.拉膜超时警报.code(), time)));
        }
    }

    /**
     * 封膜超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForSealMoTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.封膜超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.封膜超时警报.code(), time)));
        }
    }

    /**
     * 开门超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForOpenDoorTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.开门超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.开门超时警报.code(), time)));
        }
    }

    /**
     * 关门超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForCloseDoorTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.关门超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.关门超时警报.code(), time)));
        }
    }

    /**
     * 取货超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForQuhuomeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.取货超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.取货超时警报.code(), time)));
        }
    }

    /**
     * 送膜超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForSongMoTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.送膜超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.送膜超时警报.code(), time)));
        }
    }

    /**
     * 送谷超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForSendValleyTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.送谷超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.送谷超时警报.code(), time)));
        }
    }

    /**
     * 糠满超时警报
     *
     * @param time
     * @param isDefault
     */
    public static void setWarnForBranFullTimeout(int time, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.糠满超时警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.糠满超时警报.code(), time)));
        }
    }

    /**
     * 精度电机最大复位步数
     *
     * @param steps
     * @param isDefault
     */
    public static void setWarnForAccuracyMaxSteps(int steps, boolean isDefault) {
        if (isDefault) {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.精度电机最大复位步数警报默认.code()));
        } else {
            SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.精度电机最大复位步数警报.code(), steps)));
        }
    }


    /**
     * 启动碾米电机
     */
    public static void startMillMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.启动碾米电机.code()));
    }

    /**
     * 关闭碾米电机
     */
    public static void closeMillMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即关闭碾米电机.code()));
    }


    /**
     * 启动下米电机
     */
    public static void startXiamiMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.启动下米电机.code()));
    }

    /**
     * 关闭下米电机
     */
    public static void closeXiamiMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即关闭下米电机.code()));
    }

    /**
     * 启动拉膜电机
     */
    public static void startLamoMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.启动拉膜电机.code()));
    }

    /**
     * 关闭拉膜电机
     */
    public static void closeLamoMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即关闭拉膜电机.code()));
    }

    /**
     * 启动封膜电机
     */
    public static void startSealMoMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.启动封膜电机.code()));
    }

    /**
     * 关闭封膜电机
     */
    public static void closeSealMoMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即关闭封膜电机.code()));
    }

    /**
     * 启动吹糠电机
     */
    public static void startChuikangMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.启动吹糠电机.code()));
    }

    /**
     * 关闭吹糠电机
     */
    public static void closeChuikangMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即关闭吹糠电机.code()));
    }

    /**
     * 启动送谷电机
     */
    public static void startSendValleyMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.启动送谷电机.code()));
    }

    /**
     * 关闭送谷电机
     */
    public static void closeSendValleyMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即关闭送谷电机.code()));
    }

    /**
     * 启动送膜电机
     */
    public static void startSongmoMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.启动送膜电机.code()));
    }

    /**
     * 关闭送膜电机
     */
    public static void closeSongmoMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即关闭送膜电机.code()));
    }

    /**
     * 启动开门电机
     */
    public static void startOpenDoorMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.开门电机开到位.code()));
    }

    /**
     * 关闭开门电机
     */
    public static void closeOpenDoorMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.开门电机关到位.code()));
    }

    /**
     * 立即停止开门电机运行
     */
    public static void stopOpenDoorMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.立即停止开门电机.code()));
    }

    /**
     * 设置关门延时时间
     */
    public static void setCloseDoorOffsetTime(int time) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.关门延时时间.code(), time)));
    }

    /**
     * 设置送谷保持时间
     */
    public static void setSendValleyOffsetTime(int time) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.送谷保持时间.code(), time)));
    }

    /**
     * 停止精度电机
     */
    public static void stopAccuracyMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.停止精度电机.code()));
    }

    /**
     * 复位精度电机
     */
    public static void resetAccuracyMotor() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.复位精度电机.code()));
    }

    /**
     * 开始横封加热
     */
    public static void startHeatForH() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.开始横封加热.code()));
    }

    /**
     * 停止横封加热
     */
    public static void stopHeatForH() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.关闭横封加热.code()));
    }

    /**
     * 开始纵封加热
     */
    public static void startHeatForZ() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.开始纵封加热.code()));
    }

    /**
     * 停止纵封加热
     */
    public static void stopHeatForZ() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.关闭纵封加热.code()));
    }

    /**
     * 设置糙米精度
     */
    public static void setCaomiAccracy() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.糙米.code()));
    }

    /**
     * 设置胚米精度
     */
    public static void setPeimiAccracy() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.胚芽米.code()));
    }

    /**
     * 设置精米精度
     */
    public static void setJingmiAccracy() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.精米.code()));
    }

    /**
     * 碾米一包
     */
    public static void millOnPacket() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.碾米一包.code()));
    }

    /**
     * 打包
     * @param packNo
     */
    public static void pack(int packNo) {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(String.format(MachineCmdEnum.打包.code(),packNo)));
    }

    /**
     * 获取当前重量
     */
    public static void getWeight() {
        SerialPortManager.getInstance().sendBytes(HexUtils.HexStr2Bytes(MachineCmdEnum.当前重量.code()));
    }

}
