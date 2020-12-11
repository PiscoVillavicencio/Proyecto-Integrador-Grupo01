/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util.bd;

import java.util.Date;

import com.bcp.basic.util.Tiempo;

/**
 *
 * @author jen
 */
public class UtilHibernate {

    public static final String stringNULL_(String string) {
        return string != null ? (string.trim().equals("") ? null : string.trim()) : null;
    }

    public static final Integer integerNOTNULL_(Integer integer) {
        return integer != null ? (integer) : 0;
    }

    public static final Double doubleNOTNULL_(Double doubles) {
        return doubles != null ? (doubles) : 0.0;
    }

    public static final String stringNULL_CORT(String string) {
        return string != null ? (string.trim().equals("") ? null : ("'" + string.trim() + "'")) : null;
    }

    public static final String stringNOTNULL_(String string) {
        return string != null ? (string.trim().equals("") ? "" : string.trim()) : "";
    }

    public static final String stringNOTNULL_CORT(String string) {
        return string != null ? (string.trim().equals("") ? "" : ("'" + string.trim() + "'")) : "";
    }

    //validar vacio en la bd
    public static final String stringDate_CORT(String string) {
        return string != null ? (string.trim().equals("") ? "" : ("'" + string.trim() + "'")) : "''";
    }

    public static final String stringDate(Date fecha) {
        return Tiempo.parseToDate(fecha, Tiempo.ST_YYMMDD);
    }

}
