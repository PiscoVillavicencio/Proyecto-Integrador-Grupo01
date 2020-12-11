function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};

function valNull(val) {
    if (val !== undefined && val != '' && val !== null && val !== 'null') {
        return true;
    }
    return false;
}
function getNull(val) {
    if (val !== undefined && val != '' && val !== null && val !== 'null') {
        return val;
    }
    return null;
}
function validarEmail(elemento) {

    var texto = document.getElementById(elemento.id).value;
    var regex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;

    if (!regex.test(texto)) {
        document.getElementById("resultado").innerHTML = "Correo invalido";
    } else {
        document.getElementById("resultado").innerHTML = "";
    }

}

var utilDate = new Object();
utilDate.cons_YYYY_MM_DD = 1;
utilDate.cons_YYYY_DD_MM = 2;
utilDate.cons_MM_DD_YYYY = 2;

utilDate.convertDate = function (date, type, space) {
    if (date.length === 10) {
        var dia = date.substring(0, 2);
        var mes = date.substring(3, 5);
        var anio = date.substring(6, 10);
        if (type === utilDate.cons_YYYY_MM_DD) {
            return anio + space + mes + space + dia;
        } else if (type === utilDate.cons_YYYY_DD_MM) {
            return anio + space + dia + space + mes;
        } else if (type === utilDate.cons_MM_DD_YYYY) {
            return mes + space + dia + space + anio;
        } else {
            return null;
        }
    } else {
        return null;
    }
};
utilDate.formatDate = function (date, type) {
    if (date.length === 10) {
        var dia = "";
        var mes = "";
        var anio = "";
        date = date.replace("/", "").replace("-", "");
        date = date.replace("-", "");
        if (type === utilDate.cons_YYYY_MM_DD) {
            anio = date.substring(0, 4);
            mes = date.substring(4, 6);
            dia = date.substring(6, 8);
        }
        return dia + "/" + mes + "/" + anio;
    }
};
utilDate.fecha = function () {
    var today = new Date().toLocaleDateString(undefined, {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    });
    return today;
};

var patronDecimal = /^[0-9.]$/;///^(\d|-)?(\d|.)*\.?\d{0,2}$/; // /[0-9]+(\.[0-9][0-9]?)?/; // /[0-9]+(\.[0-9][0-9]?)?/;
var patronNumerico = /^[0-9]$/; // Patron para solo numeros
var patronAlfabetico = /^[A-Za-z \s\ñ\Ñ]$/; // Patron para solo caracteres alfabeticos, incluye espacio
var patronAlfaNumerico = /^[a-zA-Z0-9 \s\ñ\Ñ]$/; // Patron para solo caracteres alfabeNumerico, incluye espacio
var patronCorreo = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3,4})+$/; // Patron para solo correo
function validateInput(regexPatron) {
    var theEvent = window.event || event;
    var key = theEvent.keyCode || theEvent.which;

    //if (key >= 46) {
    key = String.fromCharCode(key);
    var regex = new RegExp(regexPatron);
    if (!regex.test(key)) {
        theEvent.returnValue = false;
        if (theEvent.preventDefault) {
            theEvent.preventDefault();
        }
    }
    //}
}

function filterFloat(evt, input) {
    var key = window.Event ? evt.which : evt.keyCode;
    var chark = String.fromCharCode(key);
    var tempValue = input.value + chark;
    if (key >= 48 && key <= 57) {
        if (filter(tempValue) === false) {
            return false;
        } else {
            return true;
        }
    } else {
        if (key == 8 || key == 0) {
            return true;
        } else if (key == 46) {
            if (filter(tempValue) === false) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
function filter(__val__) {
    var preg = /^([0-9]+\.?[0-9]{0,3})$/;
    if (preg.test(__val__) === true) {
        return true;
    } else {
        return false;
    }

}

function showModal(modal) {
    $('#' + modal).modal('show');
}
function hideModal(modal) {
    $('#' + modal).modal('hide');
}