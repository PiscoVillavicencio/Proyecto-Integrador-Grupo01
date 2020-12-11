/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util;

import java.util.Date;
import javax.persistence.ParameterMode;
import org.hibernate.SQLQuery;
import org.hibernate.procedure.ParameterRegistration;
import org.hibernate.procedure.ProcedureCall;

/**
 *
 * @author jen
 */
public class UtilHibernate {

    public static final String stringNULL_(String string) {
        return string != null ? (string.trim().equals("") ? null : string.trim()) : null;
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

    public static final String stringDate(String string) {
        return string != null ? (string.trim().equals("") ? "" : ("" + string.trim() + "")) : "";
    }


    public static final String logTransaction(ProcedureCall pro) {
        String ejecucion;
        try {
            ejecucion = pro.getProcedureName() + " ";
            for (ParameterRegistration obj : pro.getRegisteredParameters()) {
                String parametro = "@" + obj.getName() + "=";
                if (obj.getMode() == ParameterMode.IN) {
                    if (obj.getType() == String.class) {
                        parametro = parametro + (obj.getBind().getValue() != null ? stringNULL_CORT(obj.getBind().getValue().toString()) : "null");
                    } else if (obj.getType() == Date.class) {
                        parametro = parametro + Tiempo.parseToDate((Date) obj.getBind().getValue(), Tiempo.ST_YYMMDD_HHMM);
                    } else {
                        parametro = parametro + obj.getBind().getValue();
                    }
                } else if (obj.getMode() == ParameterMode.OUT) {
                    parametro = parametro + "OUT";
                }
                ejecucion = ejecucion + parametro + " ,";
            }
            ejecucion = ejecucion.substring(0, ejecucion.length() - 1);
        } catch (Exception e) {
            UtilException.error(e);
            ejecucion = pro.getProcedureName() + " error objeniendo parametros";
        }
        return ejecucion;
    }

    public static final String logTransaction(SQLQuery sQLQuery) {
        String ejecucion;
        try {
            ejecucion = sQLQuery.getQueryString() + " ";
//            for (ParameterRegistration obj : pro.getRegisteredParameters()) {
//                String parametro = "@" + obj.getName() + "=";
//                if (obj.getMode() == ParameterMode.IN) {
//                    parametro = parametro + obj.getBind().getValue();
//                } else if (obj.getMode() == ParameterMode.OUT) {
//                    parametro = parametro + "OUT";
//                }
//                ejecucion = ejecucion + parametro + " ,";
//            }
//            ejecucion = ejecucion.substring(0, ejecucion.length() - 1);
        } catch (Exception e) {
            UtilException.error(e);
            ejecucion = sQLQuery.getQueryString() + " error objeniendo parametros";
        }
        return ejecucion;
    }

}
