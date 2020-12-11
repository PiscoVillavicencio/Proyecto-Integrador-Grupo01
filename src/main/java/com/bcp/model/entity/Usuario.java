/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.model.entity;

/**
 *
 * @author jen
 */
public class Usuario {

    private Integer idUsuario;
    private Integer idCliente;
    private Integer idRol;
    private String nombreRol;
    private String usuario;
    private String clave;
    private Boolean flagClave;
    private String claveD;
    private String app;
    private String type;
    private String origen;
    private String nomEmpresa;
    private String nombre;
    private String codiEmpresa;
    private String apellidos;
    private String terminal;
    private Boolean flagNotificar;

    public Usuario() {

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getTerminal() {
        return terminal;
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

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getApp() {
        return app;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getFlagClave() {
        if (flagClave == null) {
            flagClave = false;
        }
        return flagClave;
    }

    public void setFlagClave(Boolean flagClave) {
        this.flagClave = flagClave;
    }

    public String getClaveD() {
        return claveD;
    }

    public void setClaveD(String claveD) {
        this.claveD = claveD;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", clave=" + clave + '}';
    }

    public String getCodiEmpresa() {
        return codiEmpresa;
    }

    public void setCodiEmpresa(String codiEmpresa) {
        this.codiEmpresa = codiEmpresa;
    }

}
