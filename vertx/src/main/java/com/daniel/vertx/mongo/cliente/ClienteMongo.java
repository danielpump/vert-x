/**
 * 
 */
package com.daniel.vertx.mongo.cliente;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.Json;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;

import com.daniel.vertx.mongo.entidades.Cargo;

/**
 * Cliente para os serviços do MongoDB
 * @author daniel
 *
 */
public class ClienteMongo extends AbstractVerticle {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("ClienteMongo"));
	
	@Override
	public void start() throws Exception {
		
		HttpClient cliente = vertx.createHttpClient(new HttpClientOptions().setDefaultHost("localhost").setDefaultPort(8080)
				.setKeepAlive(false));
		
		Cargo cargo = new Cargo();
		cargo.setCodigo("3");
		cargo.setNome("TesteCargo3");		
		
		String jsonCargo = Json.encode(cargo);
		
		cliente.post("/cargos", resultado -> {			
			logger.info(resultado.statusCode());			
		})
		.putHeader("Content-Type", "application/json")
		.putHeader("Content-Length", Integer.toString(jsonCargo.length()))
		.write(jsonCargo)			
		.end();
				
	}	

}
