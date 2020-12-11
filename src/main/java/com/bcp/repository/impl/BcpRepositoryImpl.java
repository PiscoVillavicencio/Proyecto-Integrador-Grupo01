/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ParameterMode;

import org.springframework.stereotype.Repository;

import com.bcp.basic.util.AuditoriaGeneralID;
import com.bcp.basic.util.ReturnBD;
import com.bcp.basic.util.bd.Conexion;
import com.bcp.basic.util.bd.Param;
import com.bcp.basic.util.bd.UtilConexion;
import com.bcp.model.entity.Cliente;
import com.bcp.model.entity.Cuenta;
import com.bcp.model.entity.CuentaTarjeta;
import com.bcp.model.entity.Notificacion;
import com.bcp.model.entity.Tarjeta;
import com.bcp.model.entity.TipoCliente;
import com.bcp.model.entity.Transaccion;
import com.bcp.model.entity.Usuario;
import com.bcp.repository.BcpRepository;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author User
 */
@Slf4j
@Repository
public class BcpRepositoryImpl implements BcpRepository{

	 public Usuario exeLogin(Usuario row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_usuario", row.getUsuario()));
	        params.add(new Param("_password", row.getClave()));
	        return (Usuario) new Conexion().find("spLoginUsuario", params, Usuario.class);
	    }

	    public List<Cuenta> selectCuenta(Cuenta row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_idCliente", row.getIdCliente()));
	        return new Conexion().select("spSelectCuenta", params, Cuenta.class);
	    }

	    public List<Notificacion> selectNotificacion(Notificacion row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_idCliente", row.getIdCliente()));
	        return new Conexion().select("spSelectNotificacion", params, Notificacion.class);
	    }

	    public Cuenta findCuentaNro(Cuenta row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_nroCuenta", row.getNroCuenta()));
	        return (Cuenta) new Conexion().find("spFindCuentaNro", params, Cuenta.class);
	    }

	    public List<Transaccion> selectMovimientos(Transaccion row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_idCuenta", row.getIdCuenta()));
	        return new Conexion().select("spSelectMovimientos", params, Transaccion.class);
	    }

	    public ReturnBD exeTransferencia(Transaccion row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spInsertTranssacion");
	            conexion.procedureCall.registerParameter("_idCuentaOrigen", Integer.class, ParameterMode.IN).bindValue(row.getIdCuentaOrigen());
	            conexion.procedureCall.registerParameter("_idCuentaDestino", Integer.class, ParameterMode.IN).bindValue(row.getIdCuentaDestino());
	            conexion.procedureCall.registerParameter("_monto", Double.class, ParameterMode.IN).bindValue(row.getMonto());
	            conexion.procedureCall.registerParameter("_idUser", Integer.class, ParameterMode.IN).bindValue(row.getIdUsuario());
	            conexion.procedureCall.registerParameter("_terminal", String.class, ParameterMode.IN).bindValue(row.getTerminal());
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }

	    public List<Tarjeta> selectTarjeta(Tarjeta row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_idCliente", row.getIdCliente()));
	        return new Conexion().select("spSelectTarjeta", params, Tarjeta.class);
	    }

	    public List<Cliente> selectUsuarioCliente(Cliente row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_idRol", row.getIdRol()));
	        params.add(new Param("_nombres", row.getNombres()));
	        return new Conexion().select("spSelectUsuarioCliente", params, Cliente.class);
	    }

	    public List<TipoCliente> selectTipoCliente(TipoCliente row) {
	        List<Param> params = new ArrayList<>();
	        return new Conexion().select("spSelectTipoCliente", params, TipoCliente.class);
	    }

	    public ReturnBD updateNotificacion(Cliente row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spUpdateNotificacion");
	            conexion.procedureCall.registerParameter("_idCliente", Integer.class, ParameterMode.IN).bindValue(row.getIdCliente());
	            conexion.procedureCall.registerParameter("_flag", Boolean.class, ParameterMode.IN).bindValue(row.getFlagNotificar());
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }

	    public ReturnBD updateTarjeta(Tarjeta row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spUpdateTarjeta");
	            conexion.procedureCall.registerParameter("_idTarjeta", Integer.class, ParameterMode.IN).bindValue(row.getIdTarjeta());
	            conexion.procedureCall.registerParameter("_idCliente", Integer.class, ParameterMode.IN).bindValue(row.getIdCliente());
	            conexion.procedureCall.registerParameter("_monto", Double.class, ParameterMode.IN).bindValue(row.getMonto());
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }

	    public ReturnBD updateTarjetaNoti(Tarjeta row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spUpdateTarjetaNoti");
	            conexion.procedureCall.registerParameter("_idTarjeta", Integer.class, ParameterMode.IN).bindValue(row.getIdTarjeta());
	            conexion.procedureCall.registerParameter("_flag", Boolean.class, ParameterMode.IN).bindValue(row.getFlagNotificar());
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }

	    public ReturnBD exeCliente(Cliente row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spUpdateUsuarioCliente");
	            conexion.procedureCall.registerParameter("_idUsuario", Integer.class, ParameterMode.IN).bindValue(UtilConexion.integerNOTNULL_(row.getIdUsuario()));
	            conexion.procedureCall.registerParameter("_idCliente", Integer.class, ParameterMode.IN).bindValue(UtilConexion.integerNOTNULL_(row.getIdCliente()));
	            conexion.procedureCall.registerParameter("_idRol", Integer.class, ParameterMode.IN).bindValue(UtilConexion.integerNOTNULL_(row.getIdRol()));
	            conexion.procedureCall.registerParameter("_nombres", String.class, ParameterMode.IN).bindValue(row.getNombres());
	            conexion.procedureCall.registerParameter("_apellidos", String.class, ParameterMode.IN).bindValue(row.getApellidos());
	            conexion.procedureCall.registerParameter("_usuario", String.class, ParameterMode.IN).bindValue(row.getUsuario());
	            conexion.procedureCall.registerParameter("_idTipoCliente", Integer.class, ParameterMode.IN).bindValue(UtilConexion.integerNOTNULL_(row.getIdTipoCliente()));
	            conexion.procedureCall.registerParameter("_flagPass", Boolean.class, ParameterMode.IN).bindValue(row.getFlagPass());
	            conexion.procedureCall.registerParameter("_password", String.class, ParameterMode.IN).bindValue(row.getPassword());
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }

	    public ReturnBD exeNotiCliente(Notificacion row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spInsertNotiCliente");
	            conexion.procedureCall.registerParameter("_idTipoCliente", Integer.class, ParameterMode.IN).bindValue(row.getIdTipoCliente());
	            conexion.procedureCall.registerParameter("_titulo", String.class, ParameterMode.IN).bindValue(row.getTitulo());
	            conexion.procedureCall.registerParameter("_mensaje", String.class, ParameterMode.IN).bindValue(row.getMensaje());
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }

	    public List<CuentaTarjeta> selectCuentaTarjeta(CuentaTarjeta row) {
	        List<Param> params = new ArrayList<>();
	        params.add(new Param("_idCliente", row.getIdCliente()));
	        return new Conexion().select("spSelectCuentaTarjeta", params, CuentaTarjeta.class);
	    }

	    public ReturnBD exeInsertCuentaTarjeta(CuentaTarjeta row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spInsertCuentaTarjeta");
	            conexion.procedureCall.registerParameter("_idCuenta", Integer.class, ParameterMode.IN).bindValue(UtilConexion.integerNOTNULL_(row.getIdCuenta()));
	            conexion.procedureCall.registerParameter("_nroCuenta", String.class, ParameterMode.IN).bindValue(UtilConexion.stringNOTNULL_(row.getNroCuenta()));
	            conexion.procedureCall.registerParameter("_saldo", Double.class, ParameterMode.IN).bindValue(UtilConexion.doubleNOTNULL_(row.getSaldo()));
	            conexion.procedureCall.registerParameter("_idCliente", Integer.class, ParameterMode.IN).bindValue(UtilConexion.integerNOTNULL_(row.getIdCliente()));
	            conexion.procedureCall.registerParameter("_fechaVencimiento", String.class, ParameterMode.IN).bindValue(UtilConexion.stringDate(row.getFechaVencimiento()));
	            conexion.procedureCall.registerParameter("_monto", Double.class, ParameterMode.IN).bindValue(UtilConexion.doubleNOTNULL_(row.getMonto()));
	            conexion.procedureCall.registerParameter("_nroTarjeta", String.class, ParameterMode.IN).bindValue(UtilConexion.stringNOTNULL_(row.getNroTarjeta()));
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }

	    public ReturnBD exeInactivarUsuario(Cliente row) {
	        ReturnBD out = new ReturnBD();
	        Conexion conexion = new Conexion();
	        conexion.iniciarTransacion();
	        System.out.println("row::" + row.toString());
	        try {
	            conexion.procedureCall = conexion.createStoredProcedureCall("spInactivarUsuario");
	            conexion.procedureCall.registerParameter("_idUsuario", Integer.class, ParameterMode.IN).bindValue(row.getIdUsuario());
	            conexion.procedureCall.registerParameter("_estado", Boolean.class, ParameterMode.IN).bindValue(row.getEstado());
	            conexion.procedureCall.registerParameter("_status", Integer.class, ParameterMode.OUT);
	            conexion.procedureCall.registerParameter("_message", String.class, ParameterMode.OUT);
	            Object respuesta = conexion.procedureCall.getOutputs().getOutputParameterValue("_status");
	            Object mensaje = conexion.procedureCall.getOutputs().getOutputParameterValue("_message");
	            out.out = (int) respuesta;
	            out.message = mensaje.toString();
	            conexion.commit();
	        } catch (Exception e) {
	            conexion.rollback();
	            out.out = AuditoriaGeneralID.OUT_EXCEPTION;
	            conexion.exception = e;
	            out.log = e.getMessage();
	        } finally {
	            conexion.printLogProcedureCall("admin");
	            conexion.cerrarSession();
	        }
	        return out;
	    }
}
