/**
 * 
 */
package com.daniel.vertx.mongo.servidor;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.ArrayList;
import java.util.List;

import com.daniel.vertx.mongo.dao.CargoDAO;
import com.daniel.vertx.mongo.entidades.Cargo;
import com.daniel.vertx.mongo.rotas.AbstractGeradorDeRota;
import com.daniel.vertx.mongo.rotas.RotaCargo;

/**
 * Inicialização do servidor do mongo.
 * @author daniel
 *
 */
public class ServidorMongo extends AbstractVerticle {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("ServidorMongo"));
	
	private HttpServer servidorHttp;

	@Override
	public void start() throws Exception {
		
		servidorHttp = vertx.createHttpServer();
	
		servidorHttp.requestHandler(gerarRoteamento()::accept).listen(8080);
		

	}

	/**
	 * Gera o roteamento das URLs da aplicação
	 */
	private Router gerarRoteamento() {
		Router roteador = Router.router(vertx);		
		roteador.route().handler(BodyHandler.create());//Necessario para habilitar a recuperação do corpo das requisições
		
		JsonObject configuracaoMongo = criarConfiguracaoDeConexao();
		
		List<AbstractGeradorDeRota> rotas = new ArrayList<AbstractGeradorDeRota>();
		rotas.add(new RotaCargo(new CargoDAO(MongoClient.createShared(vertx, configuracaoMongo))));
		
		rotas.forEach((rota) -> {
			rota.setRoteador(roteador);			
			rota.gerarRotas();
		});
		return roteador;
	}

	/**
	 * Cria as configurações para realizar a conexão com o mongo.
	 * @return
	 */
	private JsonObject criarConfiguracaoDeConexao() {
		JsonObject configuracaoMongo = new JsonObject();
		
		configuracaoMongo.put("db_name", "vertx_db");
		return configuracaoMongo;
	}

}
