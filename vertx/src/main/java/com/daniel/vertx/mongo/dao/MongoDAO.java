/**
 * 
 */
package com.daniel.vertx.mongo.dao;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.mongo.MongoClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.daniel.vertx.mongo.entidades.EntidadeMongo;

/**
 * Classe de funcionalidades padrões das entidades do mongo
 * @author daniel
 *
 */
public abstract class MongoDAO<T extends EntidadeMongo> {
	
	private static final String MONGO_ATTRIBUTE_ID = "_id";

	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("MongoDAO"));
	
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
	
	
	/**
	 * Salva(Insere/atualiza) a entidade definida.
	 * Caso seja salva com sucesso retorna a entidade com o id gerado: 
	 * Caso de erro:
	 * Na inserção retorna a entidade com o ID null e loga a mensagem de erro.
	 * @param entidade Entidade para ser salva
	 * @param tratarResultado Callback da execução
	 */
	public void salvar(T entidade, Handler<AsyncResult<T>> tratarResultado){
		String entidadeJsonficada = Json.encode(entidade);
		JsonObject entidadeJson = new JsonObject(entidadeJsonficada);
		removeIDCasoSejaNulo(entidadeJson);
		clienteMongo.save(getCollectionName(), entidadeJson, (resultado) -> {			
			if(resultado.succeeded()){
				entidade.set_id(resultado.result());
			} else {
				logger.error("Erro ao persistir: ", resultado.cause());
			}
			tratarResultado.handle(Future.succeededFuture(entidade));
		});
	}

	/**
	 * @param entidadeJson
	 */
	private void removeIDCasoSejaNulo(JsonObject entidadeJson) {
		if(entidadeJson.getString(MONGO_ATTRIBUTE_ID) == null){
			entidadeJson.remove(MONGO_ATTRIBUTE_ID);
		}
	}

}
