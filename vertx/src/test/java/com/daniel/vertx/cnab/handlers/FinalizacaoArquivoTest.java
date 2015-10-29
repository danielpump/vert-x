package com.daniel.vertx.cnab.handlers;

import io.vertx.core.AsyncResult;

import java.io.FileNotFoundException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe para testar o handler de finalização de manipulaçãode arquivo.
 * @author daniel
 *
 */
public class FinalizacaoArquivoTest {
	AsyncResult<Void> result;
	Mockery mockContext;
	
	/**
	 * Inicializa os mocks que serão utilizados
	 * @param context
	 */
	@Before
	public void setUp() {
		mockContext = new Mockery();
		result = mockContext.mock(AsyncResult.class);
	}
	
	@Test
	public void testarArquivoEscritoComSucesso() {
				
		FinalizacaoArquivo finalizacaoArquivo = new FinalizacaoArquivo();

		mockContext.checking(new Expectations(){{
			oneOf(result).succeeded();
			will(returnValue(Boolean.TRUE));
		}});

		finalizacaoArquivo.handle(result);
		
		mockContext.assertIsSatisfied();
		
		
	}

	@Test
	public void testarArquivoEscritoComFalha() {
		
		FinalizacaoArquivo finalizacaoArquivo = new FinalizacaoArquivo();
		
		mockContext.checking(new Expectations(){{
			oneOf(result).succeeded();
			will(returnValue(Boolean.FALSE));	
			oneOf(result).cause();
			will(returnValue(new FileNotFoundException("Teste de arquivo não encontrado!")));	
		}});
		
		finalizacaoArquivo.handle(result);
		
		mockContext.assertIsSatisfied();
	
	
	}

}
