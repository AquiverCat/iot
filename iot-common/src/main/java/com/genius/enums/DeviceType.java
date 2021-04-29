package com.genius.enums;

/**
 * @Desc: 设备类型 枚举
 */
public enum DeviceType {
    aquarium(1,"水族箱"),
    arrangement(2,"智能插排"),
    flowerpot(3,"智能花盆");

    public final Integer type;
    public final String value;

    DeviceType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
