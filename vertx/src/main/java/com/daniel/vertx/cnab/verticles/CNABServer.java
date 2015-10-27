/**
 * 
 */
package com.daniel.vertx.cnab.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.daniel.vertx.cnab.routers.RotaEstaticos;
import com.daniel.vertx.cnab.routers.RotaRemessa;

/**
 * 
 * @author daniel
 *
 */
public class CNABServer  extends AbstractVerticle {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("CNABServer"));
	
	public static  List<Class> rotas;
	
	static{
		rotas = new ArrayList<Class>();
		
		rotas.add(RotaRemessa.class);
		rotas.add(RotaEstaticos.class);
	}
	
	@Override
	public void start() throws Exception {		
		
		logger.info("Iniciando aplicação!");
		
		configurarServidor();
		
	}

	/**
	 * Realiza a configuração do sevidor HTTP
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void configurarServidor() throws InstantiationException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		HttpServer servidor = vertx.createHttpServer();
		Router roteador = Router.router(vertx);
		
		roteador.route().handler(BodyHandler.create());//Necessario para habilitar a recuperação do corpo das requisições
		registrarRotas(roteador);
		
		vertx.deployVerticle("com.daniel.vertx.cnab.workers.GerarArquivoRemessa",
				new DeploymentOptions().setInstances(2).setWorker(true));
		
		servidor.requestHandler(roteador::accept).listen(8080);
	}

	/**
	 * Registra as rotas que serão utilizadas pela aplicação
	 * @param roteador
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void registrarRotas(Router roteador) throws InstantiationException,IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (Class classeDeRoteamento : rotas) {			
			classeDeRoteamento.getDeclaredConstructor(Router.class, EventBus.class).newInstance(roteador, vertx.eventBus());//Só pra usar metaprog de curtição...
		}
	}

}
