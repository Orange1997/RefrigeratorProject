package com.example.dc.refrigeratorproject.model;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/13.
 */

public class Book implements Serializable{

    /**
     * a : 11
     * b : 11
     */

    private String a;
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
