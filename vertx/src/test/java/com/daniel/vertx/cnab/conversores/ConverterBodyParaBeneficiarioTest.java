package com.daniel.vertx.cnab.conversores;

import static org.junit.Assert.assertEquals;
import io.vertx.core.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import com.daniel.vertx.cnab.entidades.Beneficiario;
/**
 * Teste de conversão da classe {@code Beneficiario}
 * @author daniel
 *
 */
public class ConverterBodyParaBeneficiarioTest {
	
	private ConverterBodyParaBeneficiario converter;

	/**
	 * Inicializa o conversor
	 * @param context
	 */
	@Before
	public void setUp() {
		converter = new ConverterBodyParaBeneficiario();
	}
	
	@Test
	public void conversaoComParametros(){
		
		Beneficiario beneficiario = converter.converterPara(new JsonObject("{\"codigoBeneficiario\":\"963\", \"nomeBeneficiario\":\"TesteBeneficiario\", \"codigoAgencia\":\"147\"}"));
		
		
		assertEquals("963", beneficiario.getCodigo());
		assertEquals("TesteBeneficiario", beneficiario.getNome());
		assertEquals("147", beneficiario.getCodigoAgencia());
		
	}
	
	@Test
	public void conversaoComSemParametrosParaBeneficiarioDeTeste(){
		
		Beneficiario beneficiario = converter.converterPara(new JsonObject("{\"codigoBeneficiario\":\"\", \"nomeBeneficiario\":\"\", \"codigoAgencia\":\"\"}"));
		
		
		assertEquals("789", beneficiario.getCodigo());
		assertEquals("Teste ltda", beneficiario.getNome());
		assertEquals("456", beneficiario.getCodigoAgencia());
		
	}

}
