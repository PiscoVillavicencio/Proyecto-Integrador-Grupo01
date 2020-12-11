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
public class Transaccion extends AuditoriaID {

    private Integer idTransacion;
    private Integer idCuenta;
    private Integer idCuentaOrigen;
    private Integer idCuentaDestino;
    private Double monto;
    private String nroCuentaOrigen;
    private String nroCuentaDestino;
    private Integer tipo;
    private Date fecTrans;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getIdTransacion() {
        return idTransacion;
    }

    public void setIdTransacion(Integer idTransacion) {
        this.idTransacion = idTransacion;
    }

    public Integer getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(Integer idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public Date getFecTrans() {
        return fecTrans;
    }

    public void setFecTrans(Date fecTrans) {
        this.fecTrans = fecTrans;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public void setIdCuentaOrigen(Integer idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNroCuentaOrigen() {
        return nroCuentaOrigen;
    }

    public void setNroCuentaOrigen(String nroCuentaOrigen) {
        this.nroCuentaOrigen = nroCuentaOrigen;
    }

    public String getNroCuentaDestino() {
        return nroCuentaDestino;
    }

    public void setNroCuentaDestino(String nroCuentaDestino) {
        this.nroCuentaDestino = nroCuentaDestino;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "idTransacion=" + idTransacion + ", idCuenta=" + idCuenta + ", idCuentaOrigen=" + idCuentaOrigen + ", idCuentaDestino=" + idCuentaDestino + ", monto=" + monto + ", nroCuentaOrigen=" + nroCuentaOrigen + ", nroCuentaDestino=" + nroCuentaDestino + ", tipo=" + tipo + ", fecTrans=" + fecTrans + '}';
    }

}
