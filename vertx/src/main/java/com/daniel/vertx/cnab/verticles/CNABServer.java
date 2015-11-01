/**
 * 
 */
package com.daniel.vertx.cnab.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.web.Router;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.daniel.vertx.cnab.workers.WorkerDeployment;

/**
 * Classe que inica o servidor de geração de arquivos de remessa
 * @author daniel
 *
 */
public class CNABServer  extends AbstractVerticle {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("CNABServer"));
	
	private AnnotationConfigApplicationContext contextoSpring;
	
	@Override
	public void start() throws Exception {		
		
		logger.info("Iniciando aplicação!");
		//Iniciando contexto Spring
		contextoSpring = new AnnotationConfigApplicationContext(CNABConfig.class);
		
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
		
		registrarWorkers();		
				
		Router roteador = (Router) contextoSpring.getBean(Router.class);
		servidor.requestHandler(roteador::accept).listen(8080);
	}

	/**
	 * Registro dos workers da aplicação criados como beans do Spring
	 * @param workersParaRegistrar
	 */
	private void registrarWorkers() {
		Map<String, AbstractVerticle> workersParaRegistrar = contextoSpring.getBeansOfType(AbstractVerticle.class);
		//Foi carregada a lista de AbstractVerticle, apenas com o intuito de utilizar a funcionalidade de filtro da StreamAPI
		workersParaRegistrar.values().stream().
			filter((verticle) -> verticle instanceof WorkerDeployment).//Filtrando os workers da aplicação
			forEach( (worker) ->
				//Carregando os workers no contexto vertx
				vertx.deployVerticle(worker, ((WorkerDeployment)worker).configuracoesDeDeploy())
			);
	}


}
