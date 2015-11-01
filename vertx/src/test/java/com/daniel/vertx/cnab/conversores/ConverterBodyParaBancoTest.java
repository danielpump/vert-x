package com.daniel.vertx.cnab.conversores;

import static org.junit.Assert.assertEquals;
import io.vertx.core.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import com.daniel.vertx.cnab.entidades.Banco;

/**
 * Teste de conversão da classe {@code Banco}
 * @author daniel
 *
 */
public class ConverterBodyParaBancoTest {
	
	private ConverterBodyParaBanco converter;

	/**
	 * Inicializa o conversor
	 * @param context
	 */
	@Before
	public void setUp() {
		converter = new ConverterBodyParaBanco();
	}
	
	@Test
	public void conversaoComParametros(){
		
		Banco banco = converter.converter(new JsonObject("{\"codigoBanco\":\"789\", \"nomeBanco\":\"TesteBanco\"}"));
		
		
		assertEquals("789", banco.getCodigo());
		assertEquals("TesteBanco", banco.getNome());
		
	}
	
	@Test
	public void conversaoComSemParametrosParaBancoDeTeste(){
		
		Banco banco = converter.converter(new JsonObject("{\"codigoBanco\":\"\", \"nomeBanco\":\"\"}"));
		
		
		assertEquals("123", banco.getCodigo());
		assertEquals("TESTE S/A", banco.getNome());
		
	}

}
