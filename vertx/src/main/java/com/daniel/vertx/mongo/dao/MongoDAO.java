/**
 * 
 */
package com.daniel.vertx.mongo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import com.daniel.vertx.mongo.entidades.Cargo;
import com.daniel.vertx.mongo.entidades.EntidadeMongo;

/**
 * Classe de funcionalidades padrões das entidades do mongo
 * @author daniel
 *
 */
public abstract class MongoDAO<T extends EntidadeMongo> {
	
	protected MongoClient clienteMongo;	
	
	public MongoDAO(MongoClient clienteMongo) {
		super();
		this.clienteMongo = clienteMongo;
	}

	/**
	 * Recupera o nome da coleção que será utilizada para a entidade
	 * @return
	 */
	protected abstract String getCollectionName();
	
	/**
	 * Recupera a classe da entidade que será utilizada
	 * @return
	 */
	protected abstract Class getClasseEntidade();
	
	/**
	 * Carrega todas as entidades de um tipo dado os parametros chave valor passados
	 * @param parametros
	 * @return
	 */
	public void carregarTodos(HashMap<String, Object> parametros, Handler<AsyncResult<List<T>>> tratarResultado){
		clienteMongo.find(getCollectionName(), new JsonObject(parametros), (resultado) -> {			
			List<T> entidades = new ArrayList<>();
			if(resultado.succeeded()){
				resultado.result().forEach((entidade) -> {						
					entidades.add((T) Json.decodeValue(entidade.toString(), getClasseEntidade()));						
				});				
				tratarResultado.handle(Future.succeededFuture(entidades));
			}
		});
	}
	

}
