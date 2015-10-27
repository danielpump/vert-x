/**
 * 
 */
package com.daniel.vertx.cnab.conversores;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;

import com.daniel.vertx.cnab.entidades.Banco;

/**
 * @author daniel
 *
 */
public class ConverterBodyParaBanco implements Conversor<Banco> {

	@Override
	public Banco converterPara(JsonObject jsonBody) {

		return new Banco(null, null);
	}

}
