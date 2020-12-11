function OptionsParametro(handleData, codigo) {
    axios({
        method: 'POST',
        url: Urls.ParametroValor.Options,
        data: { row: new Object({ codigo: codigo }) }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}

function OptionsTipoArticulo(handleData, objeto) {
    axios({
        method: 'POST',
        url: Urls.TipoArticulo.Options_TipoArticulo,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.ListaTipoArticulo);
    });
}

function OptionsArticulo(handleData,objeto) {
    axios({
        method: 'POST',
        url: Urls.Articulo.Options_Articulo,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.ListaArticulo);
    });
}
function OptionsProveedorArticulo(handleData,objeto) {
    axios({
        method: 'POST',
        url: Urls.Articulo.Options_Proveedor_Articulo,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}
function OptionsMarcaPorTipArticulo(handleData, objeto) {
    axios({
        method: 'POST',
        url: Urls.Marca.Options_MarcaPorTipArticulo,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}

function OptionsEmpleado(handleData, objeto) {
    axios({
        method: 'POST',
        url: Urls.Servicio.Options_Empleado,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}

function OptionsModeloPorMarca(handleData, objeto) {
    axios({
        method: 'POST',
        url: Urls.Modelo.Options_ModeloPorMarca,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}

function OptionsPeriodo(handleData, codigo) {
    axios({
        method: 'POST',
        url: Urls.Periodo.Options,
        data: { row: codigo }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}

function OptionsUnidadMedida(handleData,objeto) {
    axios({
        method: 'POST',
        url: Urls.Articulo.Options_UnidadMedida,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.ListaUnidadMedida);
    });
}

function OptionsTipoServicio(handleData, objeto) {
    axios({
        method: 'POST',
        url: Urls.Util.Options_Tipo_Servicio,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}


function OptionsEstado(handleData, objeto) {
    axios({
        method: 'POST',
        url: Urls.Util.Options_Estado,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}

function OptionsCentroCosto(handleData, objeto) {
    axios({
        method: 'POST',
        url: Urls.Servicio.Options_Centro_Costo,
        data: { row: objeto }
    }).then(function (response) {
        handleData(response.data.Lista);
    });
}

function OptionsSecurity(handleData) {
    axios({
        method: 'POST',
        url: Urls.Security.Options_Security
    }).then(function (response) {
        handleData(response.data.security);
    });
}
/*metodos obtener datos de lista*/
function getArticuloSelect(datos, id) {
    var obj = new Object();
    if (datos !== undefined && datos != null) {        
        datos.forEach(function (row) {
            if (row.idArticulo == id) {
                obj = row;
                //break;
            }
        });
    }
    return obj;
}

/*metodos de calculo*/
function DecimalRound(val, r) {
    if (val != undefined) {
        return val.toFixed(r);
    } else {
        return 0;
    }
}
/*calcular costo mensual*/
function getMontoMensualCal(cantidad, valUtil, depreciacion, precio) {
    var calculo = 0.0;
    if (cantidad > 0 && valUtil > 0 && depreciacion > 0 && precio > 0) {
        calculo = (((precio) * (valUtil / 100 + 1)) / depreciacion) * cantidad;
    }
    return calculo.toFixed(3);
}

/*calcular valor comercial*/
function getValorComercial(valorCompra, utilidad, tipoCambio) {
    var calculo = 0.0;
    if (valorCompra > 0 && utilidad > 0 && tipoCambio > 0) {
        calculo = (valorCompra * (1 + utilidad)) * tipoCambio;
    }
    return calculo.toFixed(3);
}

function getValorSubtotalManoObra(SueldBase,Asingfamiliar,he,bn,b1,b2) {
    var sumar = 0.0;
    sumar = (parseFloat(SueldBase) + parseFloat(Asingfamiliar) + parseFloat(he) + parseFloat(bn) + parseFloat(b1) + parseFloat(b2));
    return sumar;
}

function getValorSubTotal2ManoObra(total,bs) {
    var sumar = 0.0;
    sumar = (parseFloat(total) + parseFloat(bs));
    return sumar;
}

function getValorSumaTotalManoObra(total, cant, movilidad) {
    var sumar = 0.0;
    sumar = (parseFloat(total) * parseFloat(cant)) + parseFloat(movilidad);
    return sumar;
}

function getValorComercialArticulo(valorCompra, utilidad) { 
    var calculo = 0.0;
    if (valorCompra > 0 && utilidad > 0) {
        calculo = (valorCompra * utilidad) + valorCompra;
    }
    return calculo.toFixed(3);
}

function getInteger(val) {
    if (val !== undefined && val!=="") {
        return parseInt(val);
    }
    return 0;
}