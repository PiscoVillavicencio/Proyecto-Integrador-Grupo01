Vue.component('autocomplete', {
    props: ['props'],
    template: '<div v-bind:id="props.id" ><div class="autoComplete"><span v-on:click="props.Init()" class="form-control" style="cursor:pointer;">{{props.select.name}}<i v-on:click="props.Init()" style="position: absolute;right: 0px;" class="material-icons">expand_more</i></span><div class="conte" v-if="props.active"><input v-bind:id="props.idinput" v-model="props.filter" v-on:keydown.enter.keyup.keypress="props.Search();"  /> <div v-if="props.active" class="uldiv"><div v-for="item in props.result" v-on:click="props.Select(item)"  ><label>{{ item.name }}</label></div></div></div></div></div>'
});

function SelectMultiple() {
    this.select = new Object();
    this.result = [];
    this.data = [];
    this.filter = "";
    this.active = false;
    this.id = "idAuto";
    this.idinput = "idinput";
    this.setDate = function (rows) {
        this.result = rows;
        this.data = rows;
    };

    this.Select = function (row) {
        this.select = row;
        this.active = false;
        //this.filter = row.name;
    };
    this.Init = function () {
        this.active = !this.active;
        if (this.active) {
            //document.getElementById("idinput").focus();
        }
    }
    this.Search = function () {
        var Lista = [];
        var FilterN = this.filter.toLowerCase();
        this.data.forEach(function (row) {
            if (row.name.toLowerCase().indexOf(FilterN) > -1) {
                Lista[Lista.length] = row;
            }
        });
        this.result = Lista;
    };
}

function convertOptionArticulo(items) {
    var select = [];
    items.forEach(function (row) {
        select[select.length] = new Object({ label: row.nombre , value: row.idArticulo});
    });
    return select;
}