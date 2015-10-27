/**
 * 
 */
package com.daniel.vertx.cnab.conversores;

import io.vertx.core.json.JsonObject;

import com.daniel.vertx.cnab.entidades.Beneficiario;
import com.google.common.base.Strings;

/**
 * @author daniel
 *
 */
public class ConverterBodyParaBeneficiario implements Conversor<Beneficiario> {

	@Override
	public Beneficiario converterPara(JsonObject jsonBody) {

		String codigoBeneficiario = jsonBody.getString("codigoBeneficiario");
		String nomeBeneficiario = jsonBody.getString("nomeBeneficiario");
		String codigoAgencia = jsonBody.getString("codigoAgencia");
		
		if(Strings.isNullOrEmpty(codigoBeneficiario)){
			codigoBeneficiario = "789";//Feito para garantir a geração dos arquivos
		}
		if(Strings.isNullOrEmpty(nomeBeneficiario)){
			nomeBeneficiario = "Teste ltda";//Feito para garantir a geração dos arquivos
		}
		if(Strings.isNullOrEmpty(codigoAgencia)){
			codigoAgencia = "456";//Feito para garantir a geração dos arquivos
		}
		
		Beneficiario beneficiario = new Beneficiario(codigoBeneficiario, nomeBeneficiario);		
		beneficiario.setCodigoAgencia(codigoAgencia);
		return beneficiario;
	}

}
