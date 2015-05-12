package com.yanbober.android_mvp_demo.bean;

import java.io.Serializable;

/**
 * Author       : yanbo
 * Date         : 2015-05-11
 * Time         : 17:31
 * Description  :
 */
public class InfoBean implements Serializable {
    private int id;
    private String name;
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
