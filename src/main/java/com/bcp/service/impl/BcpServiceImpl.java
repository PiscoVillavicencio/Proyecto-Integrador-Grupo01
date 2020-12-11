/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bcp.basic.util.ReturnBD;
import com.bcp.model.entity.Cliente;
import com.bcp.model.entity.Cuenta;
import com.bcp.model.entity.CuentaTarjeta;
import com.bcp.model.entity.Notificacion;
import com.bcp.model.entity.Tarjeta;
import com.bcp.model.entity.TipoCliente;
import com.bcp.model.entity.Transaccion;
import com.bcp.model.entity.Usuario;
import com.bcp.repository.impl.BcpRepositoryImpl;
import com.bcp.service.BcpService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author User
 */
@Service
@Slf4j
public class BcpServiceImpl implements BcpService{

    private final BcpRepositoryImpl repository = new BcpRepositoryImpl();

    public Usuario exeLogin(Usuario row) {
        return repository.exeLogin(row);
    }

    public List<Cuenta> selectCuenta(Cuenta row) {
        return repository.selectCuenta(row);
    }

    public List<Notificacion> selectNotificacion(Notificacion row) {
        return repository.selectNotificacion(row);
    }

    public Cuenta findCuentaNro(Cuenta row) {
        return repository.findCuentaNro(row);
    }

    public List<Transaccion> selectMovimientos(Transaccion row) {
        return repository.selectMovimientos(row);
    }

    public ReturnBD exeTransferencia(Transaccion row) {
        return repository.exeTransferencia(row);
    }

    public List<Tarjeta> selectTarjeta(Tarjeta row) {
        return repository.selectTarjeta(row);
    }

    public List<Cliente> selectUsuarioCliente(Cliente row) {
        return repository.selectUsuarioCliente(row);
    }

    public ReturnBD updateNotificacion(Cliente row) {
        return repository.updateNotificacion(row);
    }

    public ReturnBD updateTarjeta(Tarjeta row) {
        return repository.updateTarjeta(row);
    }

    public ReturnBD exeCliente(Cliente row) {
        return repository.exeCliente(row);
    }

    public ReturnBD exeNotiCliente(Notificacion row) {
        return repository.exeNotiCliente(row);
    }

	@Override
	public ReturnBD updateTarjetaNoti(Tarjeta row) {
		return repository.updateTarjetaNoti(row);
	}

	@Override
	public List<CuentaTarjeta> selectCuentaTarjeta(CuentaTarjeta row) {
		return repository.selectCuentaTarjeta(row);
	}

	@Override
	public ReturnBD exeInsertCuentaTarjeta(CuentaTarjeta row) {
		return repository.exeInsertCuentaTarjeta(row);
	}

	@Override
	public List<TipoCliente> selectTipoCliente(TipoCliente row) {
		return repository.selectTipoCliente(row);
	}

	@Override
	public ReturnBD exeInactivarUsuario(Cliente row) {
		return repository.exeInactivarUsuario(row);
	}
}
