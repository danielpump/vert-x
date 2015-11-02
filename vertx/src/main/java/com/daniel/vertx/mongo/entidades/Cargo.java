/**
 * 
 */
package com.daniel.vertx.mongo.entidades;

import com.google.common.base.Strings;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

/**
 * @author daniel
 *
 */
public class Cargo implements EntidadeMongo {
	
	private String _id;
	
	private String nome;

	@Override
	public String get_id() {
		return _id;
	}

	@Override
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public JsonObject toJSON() {
		
		JsonObject objetoJSON = new JsonObject();
		if(!Strings.isNullOrEmpty(get_id())){
			objetoJSON.put("_id", get_id());
		}
		if(!Strings.isNullOrEmpty(nome)){
			objetoJSON.put("nome", nome);
		}		
		
		return objetoJSON;
	}


}
