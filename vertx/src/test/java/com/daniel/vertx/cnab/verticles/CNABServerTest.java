package com.daniel.vertx.cnab.verticles;

import static org.junit.Assert.*;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.daniel.vertx.AppMaven;

/**
 * Classe de testes para a inicialização do servidor CNAB
 * @author daniel
 *
 */
@RunWith(VertxUnitRunner.class)
public class CNABServerTest {

	private Vertx vertx;
	
	/**
	 * Inicializa o container Vert-x e garante que o servidor subiu assíncronamente
	 * @param context
	 */
	@Before
	public void setUp(TestContext context) {
		vertx = Vertx.vertx();//Inicializando Vert-x
		vertx.deployVerticle(CNABServer.class.getName(), context.asyncAssertSuccess());
	}

	/**
	 * Para o servidor Vert-x inicializado
	 * @param context
	 */
	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void testantoServidorNoArAPartirDaPaginaCarregadaComOsCampos(TestContext context) {
		final Async async = context.async();

		vertx.createHttpClient().getNow(8080, "localhost", "/remessa", response -> {
			response.handler(body -> {
				context.assertTrue(body.toString().contains("name=\"codigoBeneficiario\""));
				context.assertTrue(body.toString().contains("name=\"nomeBeneficiario\""));
				context.assertTrue(body.toString().contains("name=\"codigoAgencia\""));
				context.assertTrue(body.toString().contains("name=\"codigoBanco\""));
				context.assertTrue(body.toString().contains("name=\"nomeBanco\""));
				
				async.complete();
			});
		});
	}

}
