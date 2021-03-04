package com.dcvg.sqlitesinhvien.model;

public class Student {
    private String name, classStudent, major, birth;
    private int id;

    public Student(String name, String classStudent, String major, String birth) {
        this.name = name;
        this.classStudent = classStudent;
        this.major = major;
        this.birth = birth;
    }

    public Student(String name, String classStudent, String major, String birth, int id) {
        this.name = name;
        this.classStudent = classStudent;
        this.major = major;
        this.birth = birth;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(String classStudent) {
        this.classStudent = classStudent;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
