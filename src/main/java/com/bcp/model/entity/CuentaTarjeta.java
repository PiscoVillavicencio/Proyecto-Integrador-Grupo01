package com.bcp.model.entity;

import java.util.Date;

public class CuentaTarjeta {
	private Integer idCuenta;
    private String nroCuenta;
    private Integer idCliente;
    private Date fechaInscripcion;
    private Double saldo;
    private Date fechaVencimiento;
    private String fechaVencimientoString;
    private String fechaInscripcionString;
    private Boolean flagNotificar;
    private Double monto;
    private String nroTarjeta;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getFechaVencimientoString() {
        return fechaVencimientoString;
    }

    public void setFechaVencimientoString(String fechaVencimientoString) {
        this.fechaVencimientoString = fechaVencimientoString;
    }

    public String getFechaInscripcionString() {
        return fechaInscripcionString;
    }

    public void setFechaInscripcionString(String fechaInscripcionString) {
        this.fechaInscripcionString = fechaInscripcionString;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getFlagNotificar() {
        return flagNotificar;
    }

    public void setFlagNotificar(Boolean flagNotificar) {
        this.flagNotificar = flagNotificar;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }
}
