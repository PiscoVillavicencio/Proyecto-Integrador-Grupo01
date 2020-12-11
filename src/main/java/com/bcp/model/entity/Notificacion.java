/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.model.entity;

import java.util.Date;

import com.bcp.basic.util.AuditoriaID;

/**
 *
 * @author User
 */
public class Notificacion extends AuditoriaID {

    private Integer idNotificacion;
    private Integer idCliente;
    private String mensaje;
    private String titulo;
    private Date fecha;
    private Boolean flagNoti;
    private Boolean flagTarj;
    private Integer idTipoCliente;

    public Integer getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(Integer idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Boolean getFlagNoti() {
        if (flagNoti == null) {
            flagNoti = false;
        }
        return flagNoti;
    }

    public void setFlagNoti(Boolean flagNoti) {
        this.flagNoti = flagNoti;
    }

    public Boolean getFlagTarj() {
        if (flagTarj == null) {
            flagTarj = false;
        }
        return flagTarj;
    }

    public void setFlagTarj(Boolean flagTarj) {
        this.flagTarj = flagTarj;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Notificacion{" + "idNotificacion=" + idNotificacion + ", idCliente=" + idCliente + ", mensaje=" + mensaje + ", titulo=" + titulo + ", fecha=" + fecha + ", flagNoti=" + flagNoti + ", flagTarj=" + flagTarj + ", idTipoCliente=" + idTipoCliente + '}';
    }

}
