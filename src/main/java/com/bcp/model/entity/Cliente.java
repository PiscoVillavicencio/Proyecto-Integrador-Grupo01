/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.model.entity;

import com.bcp.basic.util.AuditoriaID;

/**
 *
 * @author User
 */
public class Cliente extends AuditoriaID {

	 private Integer idCliente;
	    private Integer idTipo;
	    private String nombres;
	    private Integer idTipoCliente;
	    private String apellidos;
	    private Boolean flagNotificar;
	    private Integer idRol;
	    private String usuario;
	    private String password;
	    private String tipoCliente;
	    private Boolean flagPass;
	    private Boolean estado;

	    public Cliente() {
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Boolean getEstado() {
	        if (estado == null) {
	            estado = false;
	        }
	        return estado;
	    }

	    public void setEstado(Boolean estado) {
	        this.estado = estado;
	    }

	    public Boolean getFlagPass() {
	        if (flagPass == null) {
	            flagPass = false;
	        }
	        return flagPass;
	    }

	    public void setFlagPass(Boolean flagPass) {
	        this.flagPass = flagPass;
	    }

	    public String getTipoCliente() {
	        return tipoCliente;
	    }

	    public void setTipoCliente(String tipoCliente) {
	        this.tipoCliente = tipoCliente;
	    }

	    public String getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }

	    public Integer getIdTipoCliente() {
	        return idTipoCliente;
	    }

	    public void setIdTipoCliente(Integer idTipoCliente) {
	        this.idTipoCliente = idTipoCliente;
	    }

	    public Integer getIdRol() {
	        return idRol;
	    }

	    public void setIdRol(Integer idRol) {
	        this.idRol = idRol;
	    }

	    public Integer getIdCliente() {
	        return idCliente;
	    }

	    public void setIdCliente(Integer idCliente) {
	        this.idCliente = idCliente;
	    }

	    public Integer getIdTipo() {
	        return idTipo;
	    }

	    public void setIdTipo(Integer idTipo) {
	        this.idTipo = idTipo;
	    }

	    public String getNombres() {
	        return nombres;
	    }

	    public void setNombres(String nombres) {
	        this.nombres = nombres;
	    }

	    public String getApellidos() {
	        return apellidos;
	    }

	    public void setApellidos(String apellidos) {
	        this.apellidos = apellidos;
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
}
