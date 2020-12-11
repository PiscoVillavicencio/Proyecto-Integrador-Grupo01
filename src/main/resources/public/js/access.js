
/* global ctx, axios */


var app = new Vue({
    el: '#appLogin',
    data: {
        usuario: null,
        clave: null,
        messaje: null
    }, mounted() {

    }, methods: {
        login: function () {
            let vm = this;
            var row = new Object({usuario: vm.usuario, clave: vm.clave});
            axios({
                method: 'post',
                url: ctx + '/security/login',
                data: row
            }).then(function (response) {
                vm.messaje = null;
                var out = response.data;
                console.log(out);
                if (out.status === 1) {
                    location.href = ctx + out.data;
                } else {
                    vm.messaje = out.mensaje;
                }
            });
        }
    }
});
