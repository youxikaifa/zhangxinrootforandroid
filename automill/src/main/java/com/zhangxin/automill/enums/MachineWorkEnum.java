package com.zhangxin.automill.enums;


/**
 * Created by root on 17-9-16.
 * 机器状态枚举类
 *
 * @author sam
 */
public enum MachineWorkEnum {

    调节精度(""),
    打米中("83000500030002"),
    下米("83000500030003"),
    拉膜("83000500030004"),
    封膜("83000500030005"),
    出货("83000500030006"),
    待机("83000500020000");

    private String code;

    MachineWorkEnum(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}
