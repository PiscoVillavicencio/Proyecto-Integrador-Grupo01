function Articulo() {
    this.idArticulo = null;
    this.idTipoArticulo = null;
    this.nombre = null;
    this.idUnidadMedida = null;

    this.idMarca = null;
    this.idModelo = null;
    this.valorComercial = null;
    this.valorCompra = null;

    this.costoMantenimiento = null;
    this.despreciacionMensuales = null;
    this.descripcionArticulo = null;
    this.codigoSap = null;
    this.codigoSil = null;

    this.tipoArticulo = null;
    this.unidadMedida = null;
    this.marca = null;
    this.modelo = null;
    this.idKey = null;
};

function getArticulo(row) {
    return new Object({
        idArticulo: row.idArticulo,
        idTipoArticulo: row.idTipoArticulo,
        nombre: row.nombre,
        idUnidadMedida: row.idUnidadMedida,

        idMarca: row.idMarca,
        idModelo: row.idModelo,
        valorComercial: row.valorComercial,
        valorCompra: row.valorCompra,
        costoMantenimiento: row.costoMantenimiento,
        despreciacionMensuales: row.despreciacionMensuales,
        descripcionArticulo: row.descripcionArticulo,
        codigoSap: row.codigoSap,
        codigoSil: row.codigoSil,

        tipoArticulo: row.tipoArticulo,
        unidadMedida: row.unidadMedida,
        marca: row.marca,
        modelo: row.modelo,
        idKey: row.idKey
    });
}

function Marca() {
    this.IdeMarc = null;
    this.IdeTipArti = null;
    this.NomMarc = null;
    this.nomTipoArticulo = null;
    this.idKey = null;
};
function getMarca(row) {
    return new Object({
        IdeMarc: row.IdeMarc,
        IdeTipArti: row.IdeTipArti,
        NomMarc: row.NomMarc,
        nomTipoArticulo: row.tipoArticulo,
        idKey: row.idKey
    });
}
function Modelo() {
    this.IdeMode = null;
    this.IdeMarc = null;
    this.NomMode = null;
    this.idKey = null;
};
function getModelo(row) {
    return new Object({
        IdeMode: row.IdeMode,
        IdeMarc: row.IdeMarc,
        NomMode: row.NomMode,
        idKey: row.idKey
    });
}

function CatalagoPrecio() {
    this.idCatalogoPrecio = null;
    this.idArticulo = null;
    this.idProveedor = null;
    this.idTipoMoneda = null;
    this.valorComercial = null;
    this.valorCompra = null;
    this.fecha = null;
    this.nombre = null;
    this.proveedor = null;
    this.tipoMoneda = null;
    this.idKeyArticulo = null;
    this.idKey = null;
    this.valorCambio = null;
};

function getCatalagoPrecio(row) {
    return new Object({
        idCatalogoPrecio: row.idCatalogoPrecio,
        idArticulo: row.idArticulo,
        idProveedor: row.idProveedor,
        idTipoMoneda: row.idTipoMoneda,
        valorComercial: row.valorComercial,
        valorCompra: row.valorCompra,
        fecha: row.fecha,
        nombre: row.nombre,
        proveedor: row.proveedor,
        tipoMoneda: row.tipoMoneda,
        idKeyArticulo: row.idKeyArticulo,
        idKey: row.idKey,
        valorCambio: row.valorCambio
    });
}

function Prefacturacion() {
    this.Tipofactura = null;
    this.CodCliente = null;
    this.NombreCliente = null;
    this.sede = null;
    this.direccion = null;
    this.servicio = null;
    this.periodo = null;
    this.monto = null;
    this.total = null;
    this.estado = null;
}

function getPrefacturacion(row) {
    return new Object({
        Tipofactura: row.Tipofactura,
        CodCliente: row.CodCliente,
        NombreCliente: row.NombreCliente,
        sede: row.sede,
        direccion: row.direccion,
        servicio: row.servicio,
        periodo: row.periodo,
        monto: row.monto,
        total: row.total,
        estado: row.estado
    });
}

function Facturacion() {
    this.IdServicio = null;
    this.IdTipoFactura = null;
    this.IdClient = null;
    this.idSedes = null;
    this.idPeriodo = null;
    this.IdEstado = null;
    this.EstRegistrado = null;
    this.AudUsuarioCrea = null;
    this.Tercrea = null;
    this.AudFechaCrea = null;
    this.idKey = null;
    this.diasfactura = null;
    this.indicativo = null;
    this.ordencompra = null;
    this.hes = null;
    this.idemanoobra = null;
    this.idcostoservicio = null;
    this.Observacion = null;
    this.costototal = null;
    this.nombrepuesto = null;
    this.codpuesto = null;
    this.nombrevalor = null;
    this.idManoObra = null;
    this.nomTipoArticulo = null;
    this.TotalCosto = null;
    this.Row1 = null;
    this.parametrovalor = null;
    this.montoConcepto = null;
    this.MontoPrincipal = null;
    this.gastosadministrativos = null;
    this.Utilidades = null;
    this.codigo = null;
    this.Idparametrovalor = null;
    this.IdFactura = null;
    this.listadoCodigo = null;
}

function getFacturacion(row) {
    return new Object({
        IdServicio: row.IdServicio,
        IdTipoFactura: row.IdTipoFactura,
        IdClient: row.IdClient,
        idSedes: row.idSedes,
        idPeriodo: row.idPeriodo,
        IdEstado: row.IdEstado,
        EstRegistrado: row.EstRegistrado,
        AudUsuarioCrea: row.AudUsuarioCrea,
        Tercrea: row.Tercrea,
        AudFechaCrea: row.AudFechaCrea,
        idKey: row.idKey,
        diasfactura: row.diasfactura,
        indicativo: row.indicativo,
        ordencompra: row.ordencompra,
        hes: row.hes,
        idemanoobra: row.idemanoobra,
        idcostoservicio: row.idcostoservicio,
        Observacion: row.Observacion,
        costototal: row.costototal,
        nombrepuesto: row.nombrepuesto,
        codpuesto: row.codpuesto,
        nombrevalor: row.nombrevalor,
        idManoObra: row.idManoObra,
        nomTipoArticulo: row.nomTipoArticulo,
        TotalCosto: row.TotalCosto,
        Row1: row.Row1,
        parametrovalor: row.parametrovalor,
        montoConcepto: row.montoConcepto,
        MontoPrincipal: row.MontoPrincipal,
        gastosadministrativos: row.gastosadministrativos,
        Utilidades: row.Utilidades,
        codigo: row.codigo,
        Idparametrovalor: row.Idparametrovalor,
        IdFactura: row.IdFactura,
        listadoCodigo: row.listadoCodigo
    });
}

function ManoObra() {
    this.idservicio = null;
    this.idetapa = null;
    this.idtipopuesto = null;
    this.idturno = null;
    this.idfrecuencia = null;
    this.idparttime = null;
    this.idfulltime = null;
    this.sueldobase = null;
    this.asignacionfamiliar = null;
    this.horasExtas = null;
    this.bonoNocturno = null;
    this.bono1 = null;
    this.bono2 = null;
    this.bonosocial = null;
    this.cantPues = null;
    this.movilidad = null;
    this.totalModM = null;
    this.subTotalM1 = null;
    this.subTotalM2 = null;
    this.estadRegistro = null;
    this.Row1 = null;
    this.idKey = null;
    this.idManoObra = null;
    this.nombrevalor = null;
}


function getManoObra(row) {
    return new Object({
        idservicio: row.idservicio,
        idetapa: row.idetapa,
        idtipopuesto: row.idtipopuesto,
        idturno: row.idturno,
        idfrecuencia: row.idfrecuencia,
        idparttime: row.idparttime,
        idfulltime: row.idfulltime,
        sueldobase: row.sueldobase,
        asignacionfamiliar: row.asignacionfamiliar,
        horasExtas: row.horasExtas,
        bonoNocturno: row.bonoNocturno,
        bono1: row.bono1,
        bono2: row.bono2,
        bonosocial: row.bonosocial,
        cantPues: row.cantPues,
        movilidad: row.movilidad,
        totalModM: row.totalModM,
        subTotalM1: row.subTotalM1,
        subTotalM2: row.subTotalM2,
        estadRegistro: row.estadRegistro,
        Row1: row.Row1,
        idKey: row.idKey,
        idManoObra: row.idManoObra,
        nombrevalor: row.nombrevalor
    });
}

function Cliente() {
    this.idCliente = null;
    this.ruc = null;
    this.nombre = null;
    this.giroNegocio = null;
    this.direccionFiscal = null;
    this.idTipoCliente = null;
    this.contacto = null;
    this.cargo = null;
    this.telefono = null;
    this.correo = null;
    this.idCategoria = null;
    this.idSegmento = null;
    this.idGrupo = null;
    this.codigoSap = null;
    this.idKey = null;
}


function getCliente(row) {
    return new Object({
        idCliente: row.idCliente,
        ruc: row.ruc,
        nombre: row.nombre,
        giroNegocio: row.giroNegocio,
        direccionFiscal: row.direccionFiscal,
        idTipoCliente: row.idTipoCliente,
        contacto: row.contacto,
        cargo: row.cargo,
        telefono: row.telefono,
        correo: row.correo,
        idCategoria: row.idCategoria,
        idSegmento: row.idSegmento,
        idGrupo: row.idGrupo,
        codigoSap: row.codigoSap,
        idKey: row.idKey,
        tipoCliente: row.tipoCliente
    });
}

function Proveedor() {
    this.idProveedor = null;
    this.nombre = null;
    this.ruc = null;
    this.idTipoProveedor = null;
    this.contacto = null;
    this.direccion = null;
    this.fax = null;
    this.telefono = null;
    this.correo = null;
    this.codigoSap = null;
    this.idKey = null;
    this.precio = null;
    this.moneda = null;
    this.tipoProveedor = null;
    this.idArticulo = null;
    this.codArticulo = null;
};

function getProveedor(row) {
    return new Object({
        idProveedor: row.idProveedor,
        nombre: row.nombre,
        ruc: row.ruc,
        idTipoProveedor: row.idTipoProveedor,
        contacto: row.contacto,
        direccion: row.direccion,
        fax: row.fax,
        telefono: row.telefono,
        correo: row.correo,
        codigoSap: row.codigoSap,
       
        idKey: row.idKey,
        precio: row.precio,
        moneda: row.moneda,
        tipoProveedor: row.tipoProveedor
    });
}


function Sede() {
    this.idSede = null;
    this.idCliente = null;
    this.nombre = null;
    this.direccion = null;
    this.telefono = null;
    this.idDepartamento = null;
    this.idProvincia = null;


    this.cliente = null;
};


function getSede(row) {
    return new Object({
        idSede: row.idSede,
        idCliente: row.idCliente,
        cliente: row.cliente,
        nombre: row.nombre,
        direccion: row.direccion,
        telefono: row.telefono,
        idDepartamento: row.idDepartamento,
        idProvincia: row.idProvincia,

        cliente: row.cliente
    });
}

function Usuario() {
    this.idUsuario = null;
    this.usuario = null;
    this.nombre = null;
    this.apePaterno = null;
    this.apeMaterno = null;
    this.nroDocumento = null;
    this.tipDocu = null;
    this.tipCarg = null;
    this.email = null;
    this.clave = null;
    this.tipDocuS = null;
};

function getUsuario(row) {
    return new Object({
        idUsuario: row.idUsuario,
        usuario: row.usuario,
        nombre: row.nombre,
        apePaterno: row.apePaterno,
        apeMaterno: row.apeMaterno,
        nroDocumento: row.nroDocumento,
        tipDocu: row.tipDocu,
        tipCarg: row.tipCarg,
        email: row.email,
        clave: row.clave,
        tipDocuS: row.tipDocuS
    });
}