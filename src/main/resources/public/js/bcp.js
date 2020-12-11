/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global axios, ctx */
var ctx="/security";

function Cuenta() {
    this.nroCuenta = null;
    this.idCuenta = null;
    this.nombre = "";
    this.monto = null;
    this.saldo = null;
}
function Transferencia() {
    this.monto = 0;
    this.cuentaOrigen = new Cuenta();
}
function Tarjeta() {

}
function Return() {
    this.out = null;
    this.message = null;
}
function ResponseService(response) {
    console.log(response);
    var out = new Return();
    if (response.data.status == 1) {
        out = response.data.data;
    } else {
        out.out = 2;
        out.message = response.data.mensaje;
    }
    return out;
}
var app = new Vue({
    el: '#appBcp',
    data: {
        resultCuenta: [],
        resultTarjeta: [],
        resultNoti: [],
        resultMovimientos: [],
        filterCuenta: new Object(),
        recordCuenta: new Object(),
        recordTransferencia: new Object(),
        frmMaster: true,
        frmCuenta: false,
        frmTrans: false,
        frmNoti: false,
        exeTrans: false,
        frmTarjeta :false,
        outTrans: new Return(),
        msjTransferencia: null,
        msjFilterCuenta: null,
        nroNoti: -1,
        recordTarjeta: new Object(),
        outTarjeta: new Return(),
        flagNotificar: false,
        user: new Object()
    }, mounted() {
        this.init();
    }, methods: {
        init: function () {
            let vm = this;
            vm.selectCuenta();
            vm.selectTarjeta();
            vm.findConfiNoti();
            axios({
                method: 'post',
                url: ctx + '/accessUser',
                data: new Object({origen: 0})
            }).then(function (response) {
                eval(response.data.eval);
                vm.user = response.data.data;
            });
            setInterval(() => vm.selectNoti(), 3000);
        },
        selectCuenta: function () {
            let vm = this;
            var row = new Object({});
            axios({
                method: 'post',
                url: ctx + '/selectCuenta',
                data: row
            }).then(function (response) {
                vm.resultCuenta = response.data.data;
            });
        },
        selectTarjeta: function () {
            let vm = this;
            var row = new Object({});
            axios({
                method: 'post',
                url: ctx + '/selectTarjeta',
                data: row
            }).then(function (response) {
                vm.resultTarjeta = response.data.data;
            });
        },
        showTarjeta: function (select) {
            let vm = this;
            vm.recordTarjeta = select;
            vm.outTarjeta = new Return();
            vm.frmMaster = false;
            vm.frmTarjeta = true;
        },
        showCuenta: function (select) {
            let vm = this;
            vm.recordCuenta = select;
            vm.selectMovimientos();
            vm.filterCuenta = new Cuenta();
            vm.frmCuenta = true;
            vm.frmMaster = false;
        },
        showTransferencia: function () {
            let vm = this;
            vm.recordTransferencia = new Transferencia();
            vm.filterCuenta = new Cuenta();
            vm.outTrans = new Return();
            vm.frmTrans = true;
            vm.frmCuenta = false;
            vm.msjTransferencia = null;
            vm.msjFilterCuenta = null;
        },
        findCuenta: function () {
            let vm = this;
            vm.msjFilterCuenta = null;
            vm.recordTransferencia = new Transferencia();
            var row = new Object({nroCuenta: vm.filterCuenta.nroCuenta + ""});
            if (!valNull(row.nroCuenta)) {
                vm.msjFilterCuenta = "Ingrese n\u00famero cuenta";
            } else {
                axios({
                    method: 'post',
                    url: ctx + '/findCuentaNro',
                    data: row
                }).then(function (response) {
                    console.log(response.data);
                    if (response.data.status === 1) {
                        if (valNull(response.data.data)) {
                            vm.recordTransferencia.cuentaOrigen = response.data.data;
                            vm.recordTransferencia.monto = 5;
                        } else {
                            vm.msjFilterCuenta = "El n\u00famero de cuenta no existe";
                        }
                    } else {
                        vm.msjFilterCuenta = "El n\u00famero de cuenta no existe";
                    }
                });
            }
        },
        exeTransferencia: function () {
            let vm = this;
            vm.msjTransferencia = null;
            var row = new Object({
                idCuentaOrigen: vm.recordCuenta.idCuenta,
                idCuentaDestino: vm.recordTransferencia.cuentaOrigen.idCuenta,
                monto: vm.recordTransferencia.monto
            });
            if (row.monto < 0) {
                vm.msjTransferencia = "Monto incorrecto";
            } else {
                axios({
                    method: 'post',
                    url: ctx + '/exeTransferencia',
                    data: row
                }).then(function (response) {
                    var out = response.data.data;
                    console.log(out);
                    vm.outTrans = out;
                    if (out.out === 1) {
                        vm.selectCuenta();
                    }
                });
            }
        },
        selectMovimientos: function () {
            let vm = this;
            vm.resultMovimientos = [];
            var row = new Object({idCuenta: vm.recordCuenta.idCuenta});
            axios({
                method: 'post',
                url: ctx + '/selectMovimientos',
                data: row
            }).then(function (response) {
                vm.resultMovimientos = response.data.data;
            });
        },
        exeCancelarTrans: function () {
            let vm = this;
            vm.frmMaster = false;
            vm.frmCuenta = true;
            vm.frmTrans = false;
        },
        exeMenu: function () {
            let vm = this;
            vm.frmMaster = true;
            vm.frmCuenta = false;
            vm.frmTrans = false;
            vm.frmTarjeta = false;
        }, selectNoti: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/selectNotificacion',
                data: new Object({nro: 0})
            }).then(function (response) {
                eval(response.data.eval);
                vm.resultNoti = response.data.data;
                //console.log("---------");
                //console.log(vm.nroNoti);
                //console.log(vm.resultNoti.length);
                if (vm.nroNoti >= 0 && vm.resultNoti.length > vm.nroNoti) {
                    //console.log("consultando....");
                    vm.selectCuenta();
                    vm.selectTarjeta();
                }
                vm.nroNoti = vm.resultNoti.length;
            });
        }, exeConfigNoti: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/updateNotificacion',
                data: new Object({flagNotificar: vm.flagNotificar})
            }).then(function (response) {
                //vm.findConfiNoti();
            });
        }, findConfiNoti: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/findUserCliente',
                data: new Object({nro: 0})
            }).then(function (response) {
                var obj = response.data.data;
                vm.flagNotificar = obj.flagNotificar;
            });
        }, exeInsertLimite: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/updateTarjeta',
                data: vm.recordTarjeta
            }).then(function (response) {
                vm.outTarjeta = ResponseService(response);
                if (vm.outTarjeta.out === 1) {
                    vm.selectTarjeta();
                }
            });
        }, exeConfigNotiTarjeta: function (row) {
            axios({
                method: 'post',
                url: ctx + '/updateTarjetaNoti',
                data: row
            }).then(function (response) {
                //vm.findConfiNoti();
            });
        }
    }
});