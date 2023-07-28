package com.mrmk.restfulwebservices.versioning;

public class Name {
    private String fname;
    private String lName;

    public Name(String fname, String lName) {
        this.fname = fname;
        this.lName = lName;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "fname='" + fname + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }
}
