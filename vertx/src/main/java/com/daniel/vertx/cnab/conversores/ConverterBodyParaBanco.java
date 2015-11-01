/**
 * 
 */
package com.daniel.vertx.cnab.conversores;

import org.springframework.stereotype.Component;

import io.vertx.core.json.JsonObject;

import com.daniel.vertx.cnab.entidades.Banco;
import com.google.common.base.Strings;

/**
 * Esta classe foi implementada assim, para garantir a geração dos arquivos
 * @author daniel
 *
 */
@Component
public class ConverterBodyParaBanco implements Conversor<Banco> {

	@Override
	public Banco converter(JsonObject jsonBody) {
		
		String codigobanco = jsonBody.getString("codigoBanco");
		String nomebanco = jsonBody.getString("nomeBanco");
		
		if(Strings.isNullOrEmpty(codigobanco)){
			codigobanco = "123";//Feito para garantir a geração dos arquivos
		}
		if(Strings.isNullOrEmpty(nomebanco)){
			nomebanco = "TESTE S/A";//Feito para garantir a geração dos arquivos
		}
		
		return new Banco(codigobanco,nomebanco);
	}

}
