/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util;

import com.bcp.model.entity.Usuario;

/**
 *
 * @author jen
 */
public class AuditoriaID {

    private Integer idUsuario;
    private String terminal;

    public AuditoriaID() {

    }

    public AuditoriaID(Usuario user) {
        if (user != null) {
            this.idUsuario = user.getIdUsuario();
            this.terminal = user.getTerminal();
        }
    }

    public void auditoria(Usuario user) {
        if (user != null) {
            this.idUsuario = user.getIdUsuario();
            this.terminal = user.getTerminal();
        }
    }

    public Usuario getAudit() {
        Usuario row = new Usuario();
        row.setIdUsuario(idUsuario);
        row.setTerminal(terminal);
        return row;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "AuditoriaID{" + "idUsuario=" + idUsuario + ", terminal=" + terminal + '}';
    }

}
