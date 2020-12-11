/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util;

/**
 *
 * @author User
 */
public class Response {

    private int status;
    private String mensaje;
    private Object data;
    private String eval;
    public Response() {
        status = 1;
    }

    public Response(int status, String mensaje, Object data) {
        this.status = status;
        this.mensaje = mensaje;
        this.data = data;
    }

    public String getEval() {
		return eval;
	}

	public void setEval(String eval) {
		this.eval = eval;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
