/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Função para criar um objeto XMLHTTPRequest
 */

/**
 * Função para enviar os dados
 */




function formataReal(valor) {
    return valor.toLocaleString('pt-BR'), event;
}

function formatQtde(obj) {

    var result = "";
    var letra = "";
    var texto = obj.value;

    for (var i = 0; i < texto.length; i++) {
        letra = texto.substring(i, i + 1);
        if (isNumber(letra)) {
            result = result + letra;
        }
    }

    obj.value = result;

}

function isNumber(letra) {

    var result = false;

    if (letra >= 0 || letra <= 9) {
        result = true;
    }

    return result;

}




function moeda(a, e, r, t) {
    let n = ""
            , h = j = 0
            , u = tamanho2 = 0
            , l = ajd2 = ""
            , o = window.Event ? t.which : t.keyCode;
    if (13 == o || 8 == o)
        return !0;
    if (n = String.fromCharCode(o),
            -1 == "0123456789".indexOf(n))
        return !1;
    for (u = a.value.length,
            h = 0; h < u && ("0" == a.value.charAt(h) || a.value.charAt(h) == r); h++)
        ;
    for (l = ""; h < u; h++)
        -1 != "0123456789".indexOf(a.value.charAt(h)) && (l += a.value.charAt(h));
    if (l += n,
            0 == (u = l.length) && (a.value = ""),
            1 == u && (a.value = "0" + r + "0" + l),
            2 == u && (a.value = "0" + r + l),
            u > 2) {
        for (ajd2 = "",
                j = 0,
                h = u - 3; h >= 0; h--)
            3 == j && (ajd2 += e,
                    j = 0),
                    ajd2 += l.charAt(h),
                    j++;
        for (a.value = "",
                tamanho2 = ajd2.length,
                h = tamanho2 - 1; h >= 0; h--)
            a.value += ajd2.charAt(h);
        a.value += r + l.substr(u - 2, u)
    }
    return !1
}

var count = 2;
var remover;

function setRemover(texto) {
    remover = texto;
}

function addRow(texto) {
    var doc = document.getElementById("formularioPedido"); //.append(addNewRow(count));
    var texto = '<div class="row" id="form_produto' + count + '">' +
            document.getElementById(texto).innerHTML +
            '<div class="col-md-1">' +
            '   <div class="form-group">' +
            '   <div>Remover</div> ' +
            '   <button type="button" data-toggle="modal" data-target="#deleteModal" style="margin-top: 8px; margin-left: 23px" onclick="setRemover(' + count + ')" class="btn btn-outline-danger">-</button>' +
            '</div>' +
            '</div>';

    doc.insertAdjacentHTML("afterbegin", texto.replace('categoriaProduto1','categoriaProduto' + count).replace('produtos1', 'produtos' + count).replace('valorDesconto1','valorDesconto' + count).replace('quantidade1','quantidade'+count));
    zerarProdutos(count);
    
    count = count + 1;

}



function deleteRow() {
    CriarRequest();

    var doc = document.getElementById('formularioPedido' + remover);
    doc.innerHTML = "";

}

