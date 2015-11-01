package com.daniel.vertx.cnab.workers;

import static org.junit.Assert.assertEquals;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import java.io.File;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.daniel.vertx.cnab.conversores.Conversor;
import com.daniel.vertx.cnab.entidades.Banco;
import com.daniel.vertx.cnab.entidades.Beneficiario;

@RunWith(VertxUnitRunner.class)
public class GerarArquivoRemessaTest {
	
	private Vertx vertx;
	private Mockery mockContext;
	
	private Conversor<Banco> conversorBanco;
	private Conversor<Beneficiario> conversorBeneficiario;
	
	private Handler<AsyncResult<Void>> tratamentoFinalizacaoArquivo;
	
	/**
	 * Inicializa o container Vert-x e garante o deploy do worker
	 * @param context
	 */
	@Before
	public void setUp(TestContext context) {
		vertx = Vertx.vertx();//Inicializando Vert-x
		mockContext = new JUnit4Mockery(){{
			setThreadingPolicy(new Synchroniser());
		}};
		tratamentoFinalizacaoArquivo = mockContext.mock(Handler.class);
		conversorBanco = mockContext.mock(Conversor.class,"conversorBanco");
		conversorBeneficiario = mockContext.mock(Conversor.class,"conversorBeneficiario");
		
		GerarArquivoRemessa gerarArquivoRemessa = new GerarArquivoRemessa();
		gerarArquivoRemessa.setTratamentoFinalizacaoArquivo(tratamentoFinalizacaoArquivo);
		gerarArquivoRemessa.setConversorBanco(conversorBanco);
		gerarArquivoRemessa.setConversorBeneficiario(conversorBeneficiario);
		
		vertx.deployVerticle(gerarArquivoRemessa, gerarArquivoRemessa.configuracoesDeDeploy(), context.asyncAssertSuccess());		
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
		
		mockContext.checking(new Expectations(){{			
			oneOf(conversorBanco).converter(with(any(JsonObject.class)));
			will(returnValue(new Banco("123","TesteUnitBanco")));
			
			oneOf(conversorBeneficiario).converter(with(any(JsonObject.class)));
			Beneficiario beneficiario = new Beneficiario("456","TesteunitBeneficiario");
			beneficiario.setCodigoAgencia("789");
			will(returnValue(beneficiario));
			
			oneOf(tratamentoFinalizacaoArquivo).handle(with(any(AsyncResult.class)));
			
			oneOf(tratamentoFinalizacaoArquivo).handle(with(any(AsyncResult.class)));
			
		}});
		
		vertx.eventBus().send("gerarRemessa", new JsonObject("{}"));
		
		async.complete();
		
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {}
		
		pasta = new File(caminhoArquivoRemessa);
		
		Integer quantidadeDeArquivosDepois = pasta.listFiles().length;		
		
		assertEquals((++quantidadeDeArquivosAntes), quantidadeDeArquivosDepois);	
		
	}

	/**
	 * 
	 */
	private String caminhoArquivoRemessa() {
		return System.getProperty("user.home").concat(System.getProperty("file.separator")).concat("CNAB400")
		.concat(System.getProperty("file.separator")).concat("REMESSA").concat(System.getProperty("file.separator"));
	}
	
}
