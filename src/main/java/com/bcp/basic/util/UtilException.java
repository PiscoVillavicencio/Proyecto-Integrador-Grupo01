/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util;

import org.apache.log4j.Logger;

/**
 *
 * @author jen
 */
public class UtilException {

    static final Logger logger = Logger.getLogger(UtilException.class.getName());

    public static final void error(Exception ex) {
        logger.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    public static final void error(Exception ex, Logger log) {
        log.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

}
