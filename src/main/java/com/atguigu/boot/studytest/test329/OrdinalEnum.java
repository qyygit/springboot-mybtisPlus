package com.atguigu.boot.studytest.test329;

public enum OrdinalEnum {
    WEST("live in west"),

    EAST("live in east"),
    SOUTH("live in south"),
    NORTH("live in north");
    String description;

    OrdinalEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void main(String[] args) {
        for (OrdinalEnum ordinalEnum : OrdinalEnum.values()) {
            System.out.println(ordinalEnum.getDescription());
        }
    }
}