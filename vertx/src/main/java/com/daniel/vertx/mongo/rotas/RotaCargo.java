/**
 * 
 */
package com.daniel.vertx.mongo.rotas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.daniel.vertx.mongo.dao.CargoDAO;
import com.daniel.vertx.mongo.entidades.Cargo;


import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.mongo.MongoClient;

/**
 * @author daniel
 *
 */
public class RotaCargo extends AbstractGeradorDeRota {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("RotaCargo"));

	private CargoDAO cargoDAO;
	
	/**
	 * Construtor padrão que recebe uma rota
	 * @param cargoDAO
	 */
	public RotaCargo(CargoDAO cargoDAO) {
		super();
		this.cargoDAO = cargoDAO;
	}

	@Override
	public void gerarRotas() {
		gerarPost("/cargos", (contexto) -> {			
			Cargo cargo = Json.decodeValue(contexto.getBodyAsString(), Cargo.class);
			cargoDAO.salvar(cargo, (resultado) -> {
				contexto.response().putHeader("content-type", "application/json; charset=utf-8")
			      .end(Json.encodePrettily(resultado.result()));
			});
		});
		gerarRota("/cargos", (contexto) -> {			
			cargoDAO.carregarTodos(new HashMap<String, Object>(), (resultado) -> {				
				contexto.response()
			      .putHeader("content-type", "application/json; charset=utf-8")
			      .end(Json.encodePrettily(resultado.result()));
			});	
		});
	}
	
	

}
