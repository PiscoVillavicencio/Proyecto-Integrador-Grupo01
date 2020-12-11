/* global swal */

function ViewMessage() {

    this.Show = function (out) {
        if (out.out === 1) {
            swal({text: out.message, type: "success", icon: "success", title: "Exitoso"});
        } else if (out.out === 2) {
            swal({text: out.message, type: "warning", icon: "warning", title: "Validaci\u00F3n"});
        } else if (out.out === 0) {
            swal({text: out.message, type: "warning", icon: "warning", title: "Error"});
        }

    };

    this.ShowConfirm = function (handleData, message) {
        swal({
            text: message, type: "warning", icon: "warning", title: "Confirmaci\u00F3n", buttons: true,
            dangerMode: true})
                .then((result) => {
                    handleData(result);
                });
    };

    this.ShowConfirmInput = function (handleData, message) {
        swal({
            text: message, type: "warning", icon: "warning", title: "Confirmaci\u00F3n", buttons: true,
            content: "input",
            dangerMode: true
        })
                .then((result) => {
                    handleData(result);
                });
    };

    this.ShowRedirect = function (out, message) {
        if (out.outr === 1) {
            swal({text: message, type: "success", icon: "success", title: "Exitoso"})
                    .then((result) => {
                        window.location.href = out.url;
                    });
        } else if (out.outr === 10) {
            swal({text: out.mensaje, type: "warning", icon: "warning", title: "Validaci\u00F3n"});
        } else if (out.outr === 2) {
            swal({text: out.mensaje, type: "warning", icon: "warning", title: "Error"});
        }

    };
}
;

var Message = {
    SUCCESS: 1,
    EXCEPTION: 2,
    ERROR: 0,
    VALIDATION: 10,
    Success_Save: "Guardado correctamente",
    Success_Update: "Actulizado correctamente",
    Success_Delete: "Registro eliminado correctamente",
    EstadoPedido: {
        Id_Generado: 1,
        Id_Solicitado: 2,
        Id_Observar: 4,
        Id_Aprobado: 3,
        Id_Entregado: 5,
        Id_Cerrado: 6,
        Msj_Solicitado: "¿Esta seguro de solicitar pedido?",
        Msj_Aprobar: "¿Esta seguro de aprobar pedido?",
        Msj_DesAprobar: "¿Esta seguro de observar pedido?",
        Msj_Cerrar: "¿Esta seguro de cerrar pedido?",
        Msj_Solicitado_S: "Pedido solicitado correctamente",
        Msj_Aprobado_S: "Pedido aprobado correctamente",
        Msj_Observado_S: "Pedido observado correctamente",
        Msj_Cerrar_S: "Pedido cerrado correctamente",
    },
    EntregadoPedido: {
        Msj_Entrega: "¿Esta seguro de registrar entrega?",
        Msj_Entrega_Update: "¿Esta seguro de actualizar entrega?",
        Msj_Entrega_S: "Pedido entregado correctamente",
        Msj_Entrega_Update_S: "Entrega actualizada correctamente",
    },
    Message: {
        Msj_No_Configure: "Mensaje no configurado",
        Msj_Confirm_Update: "¿Esta seguro de actualizar?"
    }, Pedido: {
        Msj_Tope_Max: "¿Está seguro de solicitar su pedido a pesar de que supero el monto máximo(Tope)?",
        Msj_Generado_Correcto: "Pedido generado  correctamente"
    }, Marca: {
        Msj_Marca_TipoArticulo: "Seleccione tipo de articulo",
        Msj_Marca_nombre: "Ingrese nombre"
    }, Eliminar: {
        Msj_Cliente_Delete: "¿Esta seguro de eliminar cliente?",
        Msj_Proveedor_Delete: "¿Eta seguro de eliminar proveedor?",
        Msj_Sede_Delete: "¿Esta seguro de eliminar sede?",
        Msj_Articulo_Delete: "¿Esta seguro de eliminar articulo?",
        Msj_Facturacion_Delete: "¿Esta seguro de eliminar concepto?",
        Msj_Fact_ManoObra: "¿Esta seguro de eliminar Mano Obra?",
        Msj_Marca_Delete: "¿Esta seguro de eliminar marca?",
        Msj_Modelo_Delete: "¿Esta seguro de eliminar modelo?",
        Msj_Articulo_Delete: "¿Esta seguro de eliminar articulo?",
        Msj_Articulo_Delete: "¿Esta seguro de eliminar articulo?",
        Msj_Facturacion_Delete: "¿Esta seguro de eliminar concepto?",
    }

};