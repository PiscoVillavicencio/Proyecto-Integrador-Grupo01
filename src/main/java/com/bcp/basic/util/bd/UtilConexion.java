/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util.bd;

import java.util.Date;

/**
 *
 * @author jen
 */
public class UtilConexion {

    public static final String stringNULL_(String string) {
        return UtilHibernate.stringNULL_(string);
    }

    public static final Integer integerNOTNULL_(Integer integer) {
        return UtilHibernate.integerNOTNULL_(integer);
    }

    public static final Double doubleNOTNULL_(Double doubles) {
        return UtilHibernate.doubleNOTNULL_(doubles);
    }

    public static final String stringNULL_CORT(String string) {
        return UtilHibernate.stringNULL_CORT(string);
    }

    public static final String stringNOTNULL_(String string) {
        return UtilHibernate.stringNOTNULL_(string);
    }

    public static final String stringNOTNULL_CORT(String string) {
        return UtilHibernate.stringNOTNULL_CORT(string);
    }

    //validar vacio en la bd
    public static final String stringDate_CORT(String string) {
        return UtilHibernate.stringDate_CORT(string);
    }
    public static final String stringDate(Date string) {
        return UtilHibernate.stringDate(string);
    }

}
