/**
 * 
 */
package com.daniel.vertx.cnab.remessa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import com.daniel.framework.cnab.remessa.CNAB400Cabecalho;
import com.daniel.vertx.cnab.entidades.Banco;
import com.daniel.vertx.cnab.entidades.Beneficiario;
import com.google.common.base.Strings;

/**
 * Implementação da interface {@code CNAB400Cabecalho} para a utilização do projeto Vert-x
 * @author daniel
 *
 */
public class VertxCNAB400Cabecalho implements CNAB400Cabecalho {
	
	private Banco banco;
	private Beneficiario beneficiario;
	
	/**
	 * Será utilizado um sequencial atomico para evitar problemas de concorrencia.
	 * Como é uma aplicação de exemplo, não será feito nenhuma persistencia da informação de sequencial.
	 * Toda vez que a aplicação for reiniciada o sequencial voltará para 1.
	 */
	private AtomicInteger sequencialArquivoRemessa;

	/**
	 * Esta entidade não possoui contrutor padrão. Esta entidade não existe sem
	 * estes campos, por isso não faz sentido cria-lá sem eles.
	 * 
	 * @param banco
	 * @param beneficiario
	 */
	public VertxCNAB400Cabecalho(Banco banco, Beneficiario beneficiario) {
		super();
		this.banco = banco;
		this.beneficiario = beneficiario;
		sequencialArquivoRemessa = new AtomicInteger(1);
	}

	@Override
	public String codigoAgencia() {
		return Strings.padStart(beneficiario.getCodigoAgencia(), 4, '0');//Seguindo regras de campo numerico de acordo com o protocolo
	}

	@Override
	public String codigoBeneficiario() {
		return Strings.padStart(beneficiario.getCodigo(), 6, '0');//Seguindo regras de campo numerico de acordo com o protocolo
	}

	@Override
	public String nomeEmpresa() {
		return Strings.padEnd(beneficiario.getNome(), 30, ' ').toUpperCase();//Seguindo regras de campo alfanumerico de acordo com o protocolo
	}

	@Override
	public String codigoBanco() {
		return Strings.padStart(banco.getCodigo(), 3, '0');//Seguindo regras de campo numerico de acordo com o protocolo
	}

	@Override
	public String nomeBanco() {
		return Strings.padEnd(banco.getNome(), 15, ' ').toUpperCase();//Seguindo regras de campo alfanumerico de acordo com o protocolo
	}

	@Override
	public String dataGeracao() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatoCNAB400 = DateTimeFormatter.ofPattern("yyMMdd");
		return formatoCNAB400.format(hoje);		
	}

	@Override
	public String numeroSequencialA() {
		return Strings.padStart(String.valueOf(sequencialArquivoRemessa.getAndIncrement()), 5, '0');//Seguindo regras de campo numerico de acordo com o protocolo
	}

	/* 
	 * Para evitar mais complexidade no exemplo, foi fixado que este campo sempre irá retornar o valor 000001.
	 */
	@Override
	public String numeroSequencialB() {
		return Strings.padStart("1", 6, '0');//Seguindo regras de campo numerico de acordo com o protocolo
	}

}
