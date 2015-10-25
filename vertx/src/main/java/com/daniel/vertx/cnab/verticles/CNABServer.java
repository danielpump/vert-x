/**
 * 
 */
package com.daniel.vertx.cnab.verticles;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.daniel.vertx.cnab.routers.AbstractGeradorDeRota;
import com.daniel.vertx.cnab.routers.RotaEstaticos;
import com.daniel.vertx.cnab.routers.RotaRemessa;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * 
 * @author daniel
 *
 */
public class CNABServer  extends AbstractVerticle {
	
	public static  List<Class> rotas;
	
	static{
		rotas = new ArrayList<Class>();
		
		rotas.add(RotaRemessa.class);
		rotas.add(RotaEstaticos.class);
	}
	
	@Override
	public void start() throws Exception {
		
		HttpServer servidor = vertx.createHttpServer();
		Router roteador = Router.router(vertx);
		
		registrarRotas(roteador);
		
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
			classeDeRoteamento.getDeclaredConstructor(Router.class).newInstance(roteador);//Só pra usar metaprog de curtição...
		}
	}

}
