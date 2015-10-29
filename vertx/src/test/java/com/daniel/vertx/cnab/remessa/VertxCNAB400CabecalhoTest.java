package com.daniel.vertx.cnab.remessa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.daniel.vertx.cnab.entidades.Banco;
import com.daniel.vertx.cnab.entidades.Beneficiario;

public class VertxCNAB400CabecalhoTest {

	private VertxCNAB400Cabecalho cabecalho;
	
	@Before
	public void setUp(){
		Beneficiario beneficiario = new Beneficiario("23", "Beneficiario");
		beneficiario.setCodigoAgencia("56");
		Banco banco = new Banco("89","Banco");
		cabecalho =new VertxCNAB400Cabecalho(banco, beneficiario);
	}
	
	@Test
	public void testeDeIntegridadeDeCamposParametrizadosInseridosCorretamente(){
		
		assertEquals("089", cabecalho.codigoBanco());
		assertEquals("0056", cabecalho.codigoAgencia());
		assertEquals("000023", cabecalho.codigoBeneficiario());
		assertEquals("BANCO          ", cabecalho.nomeBanco());
		assertEquals("BENEFICIARIO                  ", cabecalho.nomeEmpresa());
		
		
	}


}
