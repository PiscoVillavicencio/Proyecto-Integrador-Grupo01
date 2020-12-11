var ctx="/security";
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global axios, ctx */

var app = new Vue({
    el: '#appBcp',
    data: {
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
        }
    }
});