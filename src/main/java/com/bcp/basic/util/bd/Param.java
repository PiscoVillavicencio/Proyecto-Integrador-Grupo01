/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util.bd;

/**
 *
 * @author jen
 */
public class Param {

    private String string;
    private Object o;

    public Param(String string, Object o) {
        this.string = string;
        this.o = o;
    }

    public Param() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
