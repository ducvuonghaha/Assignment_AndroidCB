package com.dcvg.sqlitesinhvien.model;

public class Class {
    private String name, major;
    private int id;

    public Class(String name, String major) {
        this.name = name;
        this.major = major;
    }

    public Class(String name, String major, int id) {
        this.name = name;
        this.major = major;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
