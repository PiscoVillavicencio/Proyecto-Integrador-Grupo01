var ctx="/security";
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global axios, ctx */
function Usuario() {
    this.idUsuario = null;
    this.nombres = null;
    this.usuario = "";
    this.password = "";
    this.apellidos = "";
    this.flagPass = false;
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

function Return() {
    this.out = null;
    this.message = null;
}

var app = new Vue({
    el: '#appBcp',
    data: {
        result: [],
        filter: new Usuario(),
        record: new Usuario(),
        out: new Return(),
        frmResult: true,
        frmInsert: false,
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
        }, showInsert: function () {
            let vm = this;
            vm.out = new Return();
            vm.frmResult = false;
            vm.frmInsert = true;
        }, closeInsert: function () {
            let vm = this;
            vm.frmResult = true;
            vm.frmInsert = false;
            vm.out = new Return();
        }, showUpdate: function (row) {
            let vm = this;
            vm.record = row;
            vm.showInsert();
        }, select: function () {
            let vm = this;
            var row = new Object({});
            axios({
                method: 'post',
                url: ctx + '/selectUsuario',
                data: row
            }).then(function (response) {
                vm.result = response.data.data;
            });
        },
        exeInsert: function () {
            let vm = this;
            axios({
                method: 'post',
                url: ctx + '/exeUsuario',
                data: vm.record
            }).then(function (response) {
                var out = response.data.data;
                console.log(out);
                vm.out = out;
                if (out.out === 1) {
                    vm.select();
                    vm.closeInsert();
                }
            });
        }
    }
});