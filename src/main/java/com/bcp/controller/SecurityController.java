package com.bcp.controller;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.bcp.basic.util.AuditoriaGeneralID;
import com.bcp.basic.util.AuditoriaID;
import com.bcp.basic.util.BcpException;
import com.bcp.basic.util.Response;
import com.bcp.basic.util.Tiempo;
import com.bcp.basic.util.UtilException;
import com.bcp.basic.util.ValidarDatos;
import com.bcp.model.entity.Cliente;
import com.bcp.model.entity.Cuenta;
import com.bcp.model.entity.CuentaTarjeta;
import com.bcp.model.entity.Notificacion;
import com.bcp.model.entity.Tarjeta;
import com.bcp.model.entity.TipoCliente;
import com.bcp.model.entity.Transaccion;
import com.bcp.model.entity.Usuario;
import com.bcp.service.BcpService;

import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/security")
public class SecurityController {
	@Autowired
	BcpService service;
	
    @PostMapping(value = "/login",produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Response login(@RequestBody Usuario row, HttpSession request) {
        Response response = new Response();
        try {
            System.out.println("objeto:" + row);
            if (row != null) {
                Usuario login = service.exeLogin(row);
                if (login == null) {
                    throw new BcpException("Usuario y/o contraseña incorrecta");
                } else if (login.getIdUsuario() == null) {
                    throw new BcpException("Usuario y/o contraseña incorrecta");
                } else {
                    request.setAttribute("user", login);
                }
                if(login.getIdRol()==1) {
                	response.setData("/pages/welcomeAdmin.html");
                }else {
                	response.setData("/pages/welcome.html");	
                }
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }
    

    @RequestMapping(
            value = "/closeSesion",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response closeSesion(@RequestBody String objeto, HttpSession request) {
        Response response = new Response();
        try {
        	String base ="";
            request.removeAttribute("user");
            request.removeAttribute("login");
            response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            response.setData("location.href ='" + base + "/index.html'");
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/accessUser",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response accessUser(@RequestBody Usuario objeto, HttpSession request) {
        Response response = new Response();
        String base ="";
        try {
            System.out.println("objeto::" + objeto);
            Usuario user = (Usuario) request.getAttribute("user");
            if (user != null && user.getIdUsuario() != null) {
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
                Usuario row=new Usuario();
                row.setNombre(user.getNombre());
                row.setApellidos(user.getApellidos());
                response.setData(row);
            } else {
                response.setEval("location.href ='" + base + "/index.html'");
                response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
            }
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setData("location.href ='" + base + "/index.html'");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectCuenta",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectCuenta(@RequestBody Cuenta row, HttpSession request) {
        Response response = new Response();
        try {
            System.out.println("objeto:" + row);
            if (row != null ) {
                Usuario user = (Usuario) request.getAttribute("user");
                row.setIdCliente(user.getIdCliente());
                response.setData(service.selectCuenta(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectNotificacion",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectNotificacion(@RequestBody Notificacion row, HttpSession request) {
        Response response = new Response();
        try {
            System.out.println("objeto:" + row);
            if (row != null) {
                Usuario user = (Usuario) request.getAttribute("user");
                if(user!=null) {
	                row.setIdCliente(user.getIdCliente());
	                response.setData(service.selectNotificacion(row));
	                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
                }else {
                	response.setEval("location.href ='/index.html'");
                }
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/findCuentaNro",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response findCuentaNro(@RequestBody  Cuenta row, HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + row);
            if (row != null) {
                response.setData(service.findCuentaNro(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/findUserCliente",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response findUserCliente(@RequestBody String objeto, HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + objeto);
            if (objeto != null) {
                Usuario user = (Usuario) request.getAttribute("user");
                response.setData(user);
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectMovimientos",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectMovimientos(@RequestBody Transaccion row, HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + row);
            if (row != null) {
                response.setData(service.selectMovimientos(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/exeTransferencia",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response exeTransferencia(@RequestBody Transaccion row, HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + row);
            if (row != null) {
                audit(row, request);
                response.setData(service.exeTransferencia(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectTarjeta",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectTarjeta(@RequestBody Tarjeta row, HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + row);
            if (row != null) {
                Usuario user = (Usuario) request.getAttribute("user");
                row.setIdCliente(user.getIdCliente());
                response.setData(service.selectTarjeta(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectUsuario",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectUsuario(@RequestBody Cliente row, HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + row);
            if (row != null) {
                row.setIdRol(1);
                response.setData(service.selectUsuarioCliente(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectCliente",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectCliente(@RequestBody Cliente row , HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + row);
            if (row != null) {
                row.setIdRol(2);
                response.setData(service.selectUsuarioCliente(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/updateNotificacion",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response updateNotificacion(@RequestBody  Cliente row, HttpSession request) {
        Response response = new Response();
        try {
        	System.out.println("objeto:" + row);
            if (row != null) {
                Usuario user = (Usuario) request.getAttribute("user");
                row.setIdCliente(user.getIdCliente());
                response.setData(service.updateNotificacion(row));
                user.setFlagNotificar(row.getFlagNotificar());
                request.setAttribute("user", user);
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/updateTarjeta",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response updateTarjeta(@RequestBody Tarjeta row, HttpSession request) {
        Response response = new Response();
        try {
            if (row!=null) {                
                Usuario user = (Usuario) request.getAttribute("user");
                row.setIdCliente(user.getIdCliente());
                ValidarDatos.valUpdateTarjeta(row);
                response.setData(service.updateTarjeta(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/updateTarjetaNoti",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response updateTarjetaNoti(@RequestBody Tarjeta row, HttpSession request) {
        Response response = new Response();
        try {            
            if (row!=null) {                
                response.setData(service.updateTarjetaNoti(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/exeCliente",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response exeCliente(@RequestBody Cliente row, HttpSession request) {
        Response response = new Response();
        try {            
            if (row!=null) {
                row.setIdRol(2);
                ValidarDatos.valInsertCliente(row);
                response.setData(service.exeCliente(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/exeUsuario",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response exeUsuario(@RequestBody Cliente row, HttpSession request) {
        Response response = new Response();
        try {
            
            if (row!=null) {
                row.setIdRol(1);
                ValidarDatos.valInserUser(row);
                response.setData(service.exeCliente(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/exeNotiCliente",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response exeNotiCliente(@RequestBody Notificacion row, HttpSession request) {
        Response response = new Response();
        try {
            if (row!=null) {
                ValidarDatos.valNotiCliente(row);
                response.setData(service.exeNotiCliente(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectCuentaTarjeta",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectCuentaTarjeta(@RequestBody CuentaTarjeta row, HttpSession request) {
        Response response = new Response();
        try {
            if (row!=null) {                
                List<CuentaTarjeta> list = service.selectCuentaTarjeta(row);
                for (CuentaTarjeta cuentaTarjeta : list) {
                    System.out.println("cuentaTarjeta.getFechaVencimiento()::" + cuentaTarjeta.getFechaVencimiento());
                    cuentaTarjeta.setFechaVencimientoString(Tiempo.parseToDate(cuentaTarjeta.getFechaVencimiento(), Tiempo.ST_DDMMYYY));
                    cuentaTarjeta.setFechaInscripcionString(Tiempo.parseToDate(cuentaTarjeta.getFechaInscripcion(), Tiempo.ST_DDMMYYY));
                }
                response.setData(list);
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/exeInsertCuentaTarjeta",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response exeInsertCuentaTarjeta(@RequestBody CuentaTarjeta row, HttpSession request) {
        Response response = new Response();
        try {

            if (row!=null) {
                ValidarDatos.valInsertCuenta(row);
                response.setData(service.exeInsertCuentaTarjeta(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/selectTipoCliente",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response selectTipoCliente(@RequestBody TipoCliente objeto, HttpSession request) {
        Response response = new Response();
        try {
            response.setData(service.selectTipoCliente(new TipoCliente()));
            response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }

    @RequestMapping(
            value = "/exeInactivarUsuario",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Response exeInactivarUsuario(@RequestBody Cliente row, HttpSession request) {
        Response response = new Response();
        try {
            if (row!=null) {
                response.setData(service.exeInactivarUsuario(row));
                response.setStatus(AuditoriaGeneralID.OUT_SUCCESS);
            } else {
                throw new BcpException("Parametro incorrecto");
            }
        } catch (BcpException ex) {
            response.setMensaje(ex.getMessage());
            response.setStatus(AuditoriaGeneralID.OUT_EXCEPTION);
        } catch (Exception e) {
            response.setMensaje("Error en el servidor");
            response.setStatus(AuditoriaGeneralID.OUT_ERROR);
            UtilException.error(e);
        }
        return response;
    }


    public void audit(AuditoriaID row, HttpSession request) throws BcpException {
        Usuario user = (Usuario) request.getAttribute("user");
        if (user != null && user.getIdUsuario() != null) {
            row.auditoria(user);
        } else {
            user = new Usuario();
            user.setIdUsuario(1);
            user.setTerminal("ipusuario");
            row.auditoria(user);
            //throw new PeopleException("Usuario no cuenta con sessión");
        }
    }
}
