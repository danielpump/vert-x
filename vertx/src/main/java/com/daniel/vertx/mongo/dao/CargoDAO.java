/**
 * 
 */
package com.daniel.vertx.mongo.dao;

import io.vertx.ext.mongo.MongoClient;

import com.daniel.vertx.mongo.entidades.Cargo;

/**
 * Classe de acesso a dados para a classe {@code Cargo}
 * @author daniel
 *
 */
public class CargoDAO extends MongoDAO<Cargo> {

	public CargoDAO(MongoClient clienteMongo) {
		super(clienteMongo);
	}

	@Override
	protected String getCollectionName() {
		return "cargos";
	}

	@Override
	protected Class getClasseEntidade() {
		return Cargo.class;
	}

}
