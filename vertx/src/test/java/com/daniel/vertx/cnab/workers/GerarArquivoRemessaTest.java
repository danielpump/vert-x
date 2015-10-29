package com.daniel.vertx.cnab.workers;

import static org.junit.Assert.*;

import java.io.File;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.daniel.vertx.cnab.verticles.CNABServer;

@RunWith(VertxUnitRunner.class)
public class GerarArquivoRemessaTest {
	private Vertx vertx;
	
	/**
	 * Inicializa o container Vert-x e garante o deploy do worker
	 * @param context
	 */
	@Before
	public void setUp(TestContext context) {
		vertx = Vertx.vertx();//Inicializando Vert-x
		vertx.deployVerticle(GerarArquivoRemessa.class.getName(), context.asyncAssertSuccess());
	}

	/**
	 * Para o servidor Vert-x inicializado
	 * @param context
	 */
	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	/**
	 * Validação bem tosca apenas para identificar se foi criado ou não o item na pasta.
	 * A idéia é ver a quantidade de itens antes de depois do processo de criação do arquivo de remessa.
	 * Essa validaçãonão é operacional no mundo real, foi feita desta forma apenas para efeito de testes.
	 * @param context
	 */
	@Test
	public void testantoServidorNoArAPartirDaPaginaCarregadaComOsCampos(TestContext context) {
		final Async async = context.async();
		
		String caminhoArquivoRemessa = caminhoArquivoRemessa();
		
		File pasta = new File(caminhoArquivoRemessa);
		
		Integer quantidadeDeArquivosAntes = pasta.listFiles().length;
		
		vertx.eventBus().send("gerarRemessa", new JsonObject("{}"));
		
		pasta = new File(caminhoArquivoRemessa);
		
		Integer quantidadeDeArquivosDepois = pasta.listFiles().length;
		
		assertEquals((quantidadeDeArquivosAntes++), quantidadeDeArquivosDepois);
		
		async.complete();
	}

	/**
	 * 
	 */
	private String caminhoArquivoRemessa() {
		return System.getProperty("user.home").concat(System.getProperty("file.separator")).concat("CNAB400")
		.concat(System.getProperty("file.separator")).concat("REMESSA").concat(System.getProperty("file.separator"));
	}
	
}
