package com.app.toy.robot.common;

public enum DirectionEnum {

    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    int value;

    DirectionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DirectionEnum fromValue(int value) {
        switch(value) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            default:
                return null;
        }
    }
}
