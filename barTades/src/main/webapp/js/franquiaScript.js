
function MascaraCep(cep){  
    if(mascaraInteiro(cep)==false){
            event.returnValue = false;
    }       
    return formataCampo(cep, '00000-000', event);
}

function mascaraInteiro() {
	if (event.keyCode < 48 || event.keyCode > 57) {
		event.returnValue = false;
		return false;
	}
	return true;
}

function formataCampo(campo, Mascara, evento) {
	var boleanoMascara;

	var Digitato = evento.keyCode;
	exp = /\-|\.|\/|\(|\)| /g
	campoSoNumeros = campo.value.toString().replace(exp, "");

	var posicaoCampo = 0;
	var NovoValorCampo = "";
	var TamanhoMascara = campoSoNumeros.length;
	;

	if (Digitato != 8) { // backspace
		for (i = 0; i <= TamanhoMascara; i++) {
			boleanoMascara = ((Mascara.charAt(i) == "-")
					|| (Mascara.charAt(i) == ".") || (Mascara.charAt(i) == "/"))
			boleanoMascara = boleanoMascara
					|| ((Mascara.charAt(i) == "(")
							|| (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " "))
			if (boleanoMascara) {
				NovoValorCampo += Mascara.charAt(i);
				TamanhoMascara++;
			} else {
				NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
				posicaoCampo++;
			}
		}
		campo.value = NovoValorCampo;
		return true;
	} else {
		return true;
	}
}