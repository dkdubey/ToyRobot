package com.app.toy.robot.common;

public enum CommandEnum {

    PLACE("PLACE"),
    MOVE("MOVE"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    REPORT("REPORT");

    String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
