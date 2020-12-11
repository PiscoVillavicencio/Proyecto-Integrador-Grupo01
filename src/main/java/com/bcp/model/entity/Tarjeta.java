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
public class Tarjeta extends AuditoriaID {

    private Integer idTarjeta;
    private Integer idCuenta;
    private Date fechaVencimiento;
    private Boolean flagNotificar;
    private Double monto;
    private Integer idCliente;
    private String nroTarjeta;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Boolean getFlagNotificar() {
        if (flagNotificar == null) {
            flagNotificar = false;
        }
        return flagNotificar;
    }

    public void setFlagNotificar(Boolean flagNotificar) {
        this.flagNotificar = flagNotificar;
    }

    public Double getMonto() {
        if (monto == null) {
            monto = 0.0;
        }
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
