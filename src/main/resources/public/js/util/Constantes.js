/* global ctx */

var Urls = {
    Area: {
        select: ctx + "/area/select",
        option: ctx + "/area/select"
    }, Equipo: {
        select: ctx + "/equipo/select",
        find: ctx + "/equipo/find",
        remove: ctx + "/equipo/remove",
        insert: ctx + "/equipo/insert",
        insertEquipoMasivo: ctx + "/equipo/insertEquipoMasivo2"
    }, Usuario: {
        select: ctx + "/usuario/select",
        find: ctx + "/usuario/find",
        remove: ctx + "/usuario/remove",
        insert: ctx + "/usuario/insert",
        accces: ctx + '/bcp/accessUser',
        acccesMenu: ctx + '/usuario/menuAccess',
        updatePassword: ctx + "/usuario/updateUserPassword",
        selectRol: ctx + "/usuario/selectRol"
    }, Servicio: {
        select: ctx + "/servicio/select",
        option: ctx + "/servicio/select"
    }, Campana: {
        select: ctx + "/campana/select",
        option: ctx + "/campana/select"
    }, Parametro: {
        selectValor: ctx + "/equipo/selectParametroValor",
        option: ctx + "/equipo/selectParametroValor"
    }, Plataforma: {
        select: ctx + "/plataforma/select",
        find: ctx + "/plataforma/select",
        remove: ctx + "/plataforma/remove",
        insert: ctx + "/plataforma/insert",
        option: ctx + "/plataforma/select"
    }, PcInfo: {
        select: ctx + "/equipo/findPcInfo"

    }
};
var Codigos = {
    Parametro: {
        tipoEquipo: "PA001",
        tipoCategoria: "PA002"
    }
};
