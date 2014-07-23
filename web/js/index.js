/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
   $('#showJsonBtn').click(function (e) {
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: "webresources/collections"
        }).done(function(data, status, jqXHR) {
            $('#result').html(data.nome + ' ' + data.idade);
        }).fail(function(data, status, jqXHR) {
            alert('Erro');
        });
    }); 
});


