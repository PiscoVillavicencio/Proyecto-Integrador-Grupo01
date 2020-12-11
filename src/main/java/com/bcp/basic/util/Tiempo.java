/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jen
 */
public class Tiempo {

    public static final int AGREGAR_DIA = 1;
    public static final int AGREGAR_MES = 2;
    public static final int AGREGAR_ANIO = 3;
    public static final int AGREGAR_HORAS = 4;
    public static final int AGREGAR_MINUTOS = 5;
    public static final int AGREGAR_SEGUNDOS = 6;
    public static final int ST_DDMMYYY = 1;
    public static final int ST_DDMMYYY_HHMM_A = 2;
    public static final int ST_DDMMYYY_HHMM = 6;
    public static final int ST_DDMMYYY_HHMM_SS = 8;
    public static final int ST_DDMMYYY_EEEE = 3;
    public static final int ST_HHMM = 4;
    public static final int ST_YYMMDD = 5;
    public static final int ST_YYMMDD_HHMM = 7;

    public static final int DAT_YYMMDD = 1;
    public static final int DAT_DDMMYY = 2;
    public static final int DAT_YYMMDD_HHMM = 3;
    public static final int DAT_DDMMYY_HHMM = 4;
    public static final int DAT_HHMM = 5;

    public static Date addTime(Date date, int dias, int tipo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // Configuramos la fecha que se recibe
        switch (tipo) {
            case AGREGAR_DIA:
                calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
                break;
            case AGREGAR_MES:
                calendar.add(Calendar.MONTH, dias);  // numero de días a añadir, o restar en caso de días<0
                break;
            case AGREGAR_ANIO:
                calendar.add(Calendar.YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
                break;
            case AGREGAR_HORAS:
                calendar.add(Calendar.HOUR, dias);  // numero de días a añadir, o restar en caso de días<0
                break;
            case AGREGAR_MINUTOS:
                calendar.add(Calendar.MINUTE, dias);  // numero de días a añadir, o restar en caso de días<0
                break;
            case AGREGAR_SEGUNDOS:
                calendar.add(Calendar.SECOND, dias);  // numero de días a añadir, o restar en caso de días<0
                break;
        }

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    public static String parseToDate(Date fecha, int opcion) {
        try {
            if (fecha != null) {
                String date = null;
                DateFormat df;
                switch (opcion) {
                    case ST_DDMMYYY:
                        df = new SimpleDateFormat("dd/MM/yyyy");
                        date = df.format(fecha);
                        break;
                    case ST_DDMMYYY_HHMM_A:
                        df = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
                        date = df.format(fecha);
                        break;
                    case ST_DDMMYYY_HHMM:
                        df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        date = df.format(fecha);
                        date = date != null ? date.substring(0, 16) : "";
                        break;
                    case ST_DDMMYYY_HHMM_SS:
                        df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        date = df.format(fecha);
                        date = date != null ? date.substring(0, 19) : "";
                        break;
                    case ST_DDMMYYY_EEEE:
                        df = new SimpleDateFormat("dd/MM/yyyy (EEEEE)");
                        date = df.format(fecha);
                        break;
                    case ST_HHMM:
                        df = new SimpleDateFormat("HH:mm");
                        date = df.format(fecha);
                        break;
                    case ST_YYMMDD:
                        df = new SimpleDateFormat("yyyyMMdd");
                        date = df.format(fecha);
                        break;
                    case ST_YYMMDD_HHMM:
                        df = new SimpleDateFormat("yyyyMMdd HH:mm");
                        date = df.format(fecha);
                        break;

                }
                return date;
            } else {
                return "";
            }
        } catch (Exception e) {
            UtilException.error(e);
            return "";
        }
    }

    public static Date parseToDate(String fecha, int opcion) {
        if (fecha != null ? fecha.equals("") : true) {
            return null;
        }
        Date date;
        DateFormat format;
        try {
            switch (opcion) {
                case DAT_YYMMDD:
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    date = format.parse(fecha);
                    break;

                case DAT_DDMMYY:
                    format = new SimpleDateFormat("dd/MM/yyyy");
                    date = format.parse(fecha);
                    break;
                case DAT_YYMMDD_HHMM:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    date = format.parse(fecha);
                    break;
                case DAT_DDMMYY_HHMM:
                    format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    date = format.parse(fecha);
                    break;
                case DAT_HHMM:
                    format = new SimpleDateFormat("HH:mm");
                    date = format.parse(fecha);
                    break;
                default:
                    date = null;
                    break;
            }
        } catch (ParseException e) {
            UtilException.error(e);
            date = null;
        }
        return date;
    }

}
