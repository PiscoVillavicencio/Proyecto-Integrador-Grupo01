package com.bcp.repository;

import java.util.ArrayList;
import java.util.List;

import com.bcp.basic.util.ReturnBD;
import com.bcp.model.entity.Cliente;
import com.bcp.model.entity.Cuenta;
import com.bcp.model.entity.CuentaTarjeta;
import com.bcp.model.entity.Notificacion;
import com.bcp.model.entity.Tarjeta;
import com.bcp.model.entity.TipoCliente;
import com.bcp.model.entity.Transaccion;
import com.bcp.model.entity.Usuario;

public interface BcpRepository {
	public Usuario exeLogin(Usuario row);

    public List<Cuenta> selectCuenta(Cuenta row);

    public List<Notificacion> selectNotificacion(Notificacion row);

    public Cuenta findCuentaNro(Cuenta row) ;

    public List<Transaccion> selectMovimientos(Transaccion row);

    public ReturnBD exeTransferencia(Transaccion row);

    public List<Tarjeta> selectTarjeta(Tarjeta row);

    public List<Cliente> selectUsuarioCliente(Cliente row);

    public ReturnBD updateNotificacion(Cliente row);

    public ReturnBD updateTarjeta(Tarjeta row);

    public ReturnBD exeCliente(Cliente row);

    public ReturnBD exeNotiCliente(Notificacion row);
    
    public ReturnBD updateTarjetaNoti(Tarjeta row) ; 


    public List<CuentaTarjeta> selectCuentaTarjeta(CuentaTarjeta row);

    public ReturnBD exeInsertCuentaTarjeta(CuentaTarjeta row);

    public List<TipoCliente> selectTipoCliente(TipoCliente row) ;

    public ReturnBD exeInactivarUsuario(Cliente row);
}
