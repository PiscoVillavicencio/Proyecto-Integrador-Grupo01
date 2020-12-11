/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jen
 */
@XmlRootElement
public class ReturnBD implements Serializable {

    public int out;
    public String message;
    public Integer idReturn;
    public String log;

    public ReturnBD() {
        this.out = AuditoriaGeneralID.OUT_NOT_FOUNT;
    }

    public ReturnBD(int out, String message) {
        this.out = out;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReturnBD{" + "out=" + out + ", message=" + message + ", idReturn=" + idReturn + ", log=" + log + '}';
    }

}
