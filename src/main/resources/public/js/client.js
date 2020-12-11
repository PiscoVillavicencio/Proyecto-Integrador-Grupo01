/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global axios, ctx */
var ctx="/security";
function Cliente() {
    this.idUsuario = null;
    this.idCliente = null;
    this.nombres = null;
    this.usuario = "";
    this.flagPass = false;
    this.password = "";
    this.apellidos = "";
    this.idTipoCliente = null;
}

function CuentaTarjeta() {
    this.idCuenta = null;
    this.nroCuenta = null;
    this.fechaInscripcion = null;
    this.saldo = null;
    this.idCliente = null;
    this.idTarjeta = null;
    this.fechaVencimiento = null;
    this.flagNotificar = null;
    this.monto = null;
    this.nroTarjeta = null;
}
function NotiCliente() {
    this.idTipoCliente = null;
    this.titulo = null;
    this.mensaje = null;
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
        result: [],
        filter: new Cliente(),
        record: new Cliente(),
        out: new Return(),
        outCuenta: new Return(),
        outNoti: new Return(),
        frmResult: true,
        frmInsert: false,
        recordCuenta: new CuentaTarjeta(),
        frmCuenta: false,
        frmCuentas: false,
        frmNotificar: false,
        resultCuenta: [],
        optionsTipoCliente: [],
        recordNoti: new NotiCliente(),
        user: new Object()
    }, mounted() {
        this.init();
    }, methods: {
        init: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/accessUser',
                data: new Object({origen: 0})
            }).then(function (response) {
                eval(response.data.eval);
                vm.user = response.data.data;
            });
            vm.select();
            vm.selectTipoCliente();
        }, showInsert: function () {
            let vm = this;
            vm.out = new Return();
            vm.hideForm();
            vm.frmInsert = true;
            vm.record = new Cliente();
        }, closeInsert: function () {
            let vm = this;
            vm.hideForm();
            vm.frmResult = true;
            vm.out = new Return();
        }, showUpdate: function (row) {
            let vm = this;
            vm.hideForm();
            vm.frmInsert = true;
            vm.record = row;
        }, select: function () {
            let vm = this;
            var row = new Object({nombres: vm.filter.nombres});
            axios({
                method: 'post',
                url: ctx + '/selectCliente',
                data: row
            }).then(function (response) {
                vm.result = response.data.data;
            });
        },
        exeInsert: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/exeCliente',
                data: vm.record
            }).then(function (response) {
                vm.out = ResponseService(response);
                if (vm.out.out === 1) {
                    vm.select();
                }
            });
        },
        showCuentas: function (row) {
            let vm = this;
            console.log(row);
            vm.record = new Object({
                idCliente: row.idCliente,
                idUsuario: row.idUsuario,
                nombres: row.nombres,
                apellidos: row.apellidos,
                tipoCliente: row.tipoCliente
            });
            vm.selectCuenta();
            vm.hideForm();
            vm.frmCuentas = true;
        },
        selectCuenta: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/selectCuentaTarjeta',
                data: vm.record
            }).then(function (response) {
                vm.resultCuenta = response.data.data;
            });
        }, closeCuentas: function () {
            let vm = this;
            vm.hideForm();
            vm.frmResult = true;
        },
        showInsertCuenta: function () {
            let vm = this;
            vm.recordCuenta = new CuentaTarjeta();
            vm.outCuenta = new Return();
            vm.hideForm();
            vm.frmCuenta = true;
        },
        showUpdateCuenta: function (row) {
            let vm = this;
            vm.recordCuenta = row;
            vm.outCuenta = new Return();
            vm.hideForm();
            vm.frmCuenta = true;
        },
        exeInsertCuenta: function () {
            let vm = this;
            var row = new Object({
                fechaVencimiento: vm.recordCuenta.fechaVencimiento,
                idCuenta: vm.recordCuenta.idCuenta,
                monto: vm.recordCuenta.monto,
                nroCuenta: vm.recordCuenta.nroCuenta,
                nroTarjeta: vm.recordCuenta.nroTarjeta,
                saldo: vm.recordCuenta.saldo,
                idUsuario: vm.record.idUsuario,
                idCliente: vm.record.idCliente
            });
            console.log(row);
            axios({
                method: 'post',
                url: ctx + '/exeInsertCuentaTarjeta',
                data: row
            }).then(function (response) {
                vm.outCuenta = ResponseService(response);
                if (vm.outCuenta.out === 1) {
                    vm.selectCuenta();
                }
            });
        },
        closeInsertCuenta: function () {
            let vm = this;
            vm.hideForm();
            vm.frmCuentas = true;
        },
        showNotiCliente: function () {
            let vm = this;
            vm.outNoti = new Return();
            vm.recordNoti = new NotiCliente();
            vm.hideForm();
            vm.frmNotificar = true;
        },
        exeNotiCliente: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/exeNotiCliente',
                data: vm.recordNoti
            }).then(function (response) {
                vm.outNoti = ResponseService(response);
            });
        },
        closeNotiCliente: function () {
            let vm = this;
            vm.hideForm();
            vm.frmResult = true;
        }, exeInactivarCliente: function (row, estado) {
            let vm = this;
            var obj = new Object({idUsuario: row.idUsuario, estado: estado});
            axios({
                method: 'post',
                url: ctx + '/exeInactivarUsuario',
                data: obj
            }).then(function (response) {
                console.log(ResponseService(response));
                vm.select();
            });
        },
        selectTipoCliente: function () {
            let vm = this;
            var row = new Object({});
            axios({
                method: 'post',
                url: ctx + '/selectTipoCliente',
                data: row
            }).then(function (response) {
                vm.optionsTipoCliente = response.data.data;
            });
        }, hideForm: function () {
            let vm = this;
            vm.frmResult = false;
            vm.frmInsert = false;
            vm.frmCuenta = false;
            vm.frmCuentas = false;
            vm.frmNotificar = false;
        }
    }
});