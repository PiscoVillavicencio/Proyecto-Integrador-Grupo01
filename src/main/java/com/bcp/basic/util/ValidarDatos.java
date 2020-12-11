package com.bcp.basic.util;

import com.bcp.model.entity.Cliente;
import com.bcp.model.entity.CuentaTarjeta;
import com.bcp.model.entity.Notificacion;
import com.bcp.model.entity.Tarjeta;
import com.bcp.model.entity.Transaccion;

public class ValidarDatos {
	   public static void valInserUser(Cliente row) throws BcpException {
	        if (!valString(row.getNombres())) {
	            throw new BcpException("Ingrese nombres");
	        } else if (!valString(row.getApellidos())) {
	            throw new BcpException("Ingrese apellidos");
	        } else if (!valString(row.getUsuario())) {
	            throw new BcpException("Ingrese usuario");
	        } else if (row.getIdUsuario() != null) {
	            if (row.getFlagPass() && !valString(row.getPassword())) {
	                throw new BcpException("Ingrese contraseña");
	            }
	        } else if (row.getIdUsuario() == null) {
	            if (!valString(row.getPassword())) {
	                throw new BcpException("Ingrese contraseña");
	            }
	        }
	    }

	    public static void valInsertCliente(Cliente row) throws BcpException {
	        if (!valString(row.getNombres())) {
	            throw new BcpException("Ingrese nombres");
	        } else if (!valString(row.getApellidos())) {
	            throw new BcpException("Ingrese apellidos");
	        } else if (!valInteger(row.getIdTipoCliente())) {
	            throw new BcpException("Seleccione tipo de cliente");
	        } else if (!valString(row.getUsuario())) {
	            throw new BcpException("Ingrese usuario");
	        } else if (row.getIdUsuario() != null) {
	            if (row.getFlagPass() && !valString(row.getPassword())) {
	                throw new BcpException("Ingrese contraseña");
	            }
	        } else if (row.getIdUsuario() == null) {
	            if (!valString(row.getPassword())) {
	                throw new BcpException("Ingrese contraseña");
	            }
	        }
	    }

	    public static void valInsertCuenta(CuentaTarjeta row) throws BcpException {

	    }

	    public static void valNotiCliente(Notificacion row) throws BcpException {
	        if (!valString(row.getTitulo())) {
	            throw new BcpException("Ingrese título");
	        } else if (!valString(row.getMensaje())) {
	            throw new BcpException("Ingrese mensaje");
	        } else if (!valInteger(row.getIdTipoCliente())) {
	            throw new BcpException("Seleccione tipo de cliente");
	        }
	    }

	    public static void valUpdateTarjeta(Tarjeta row) throws BcpException {
	        if (!valDouble(row.getMonto())) {
	            throw new BcpException("Ingrese límite");
	        }
	    }

	    public static void valTransferencia(Transaccion row) throws BcpException {
	        if (!valDouble(row.getMonto())) {
	            throw new BcpException("Ingrese monto");
	        }
	    }

	    private static Boolean valString(String str) {
	        return str != null ? (!str.trim().equals("")) : false;
	    }

	    private static Boolean valInteger(Integer inte) {
	        return inte != null ? (inte > 0) : false;
	    }

	    private static Boolean valDouble(Double inte) {
	        return inte != null ? (inte > 0) : false;
	    }
}
