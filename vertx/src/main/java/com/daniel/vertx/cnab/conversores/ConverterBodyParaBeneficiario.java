/**
 * 
 */
package com.daniel.vertx.cnab.conversores;

import io.vertx.core.json.JsonObject;

import com.daniel.vertx.cnab.entidades.Beneficiario;

/**
 * @author daniel
 *
 */
public class ConverterBodyParaBeneficiario implements Conversor<Beneficiario> {

	@Override
	public Beneficiario converterPara(JsonObject jsonBody) {

		Beneficiario beneficiario = new Beneficiario("1", "1");
		beneficiario.setCodigoAgencia("1");
		return beneficiario;
	}

}
