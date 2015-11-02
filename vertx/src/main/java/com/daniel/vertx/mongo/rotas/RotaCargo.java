/**
 * 
 */
package com.daniel.vertx.mongo.rotas;

import java.util.ArrayList;
import java.util.List;


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

	@Override
	public void gerarRotas() {
		gerarRota("/cargos", (contexto) -> {			
			clienteMongo.find("cargos", new JsonObject(), (resultado) -> {
				List<Cargo> cargos = new ArrayList<>();
				if(resultado.succeeded()){
					resultado.result().forEach((entidade) -> {						
						cargos.add(Json.decodeValue(entidade.toString(), Cargo.class));						
					});
					contexto.response()
				      .putHeader("content-type", "application/json; charset=utf-8")
				      .end(Json.encodePrettily(cargos));
				} else {					
					contexto.response()
				      .putHeader("content-type", "application/json; charset=utf-8")
				      .end("Erro na busca");
				}
			});
			
		});		
	}
	
	

}
