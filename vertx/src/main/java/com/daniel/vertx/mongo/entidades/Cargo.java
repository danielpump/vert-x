/**
 * 
 */
package com.daniel.vertx.mongo.entidades;

import com.google.common.base.Strings;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

/**
 * Representa a entidade de um cargo no negócio
 * @author daniel
 *
 */
public class Cargo implements EntidadeMongo {
	
	private String _id;
	
	private String codigo;
	
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	

}
