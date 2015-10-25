/**
 * 
 */
package com.daniel.vertx.cnab.entidades;

/**
 * Classe que presenta o beneficiário de transações financeiras.
 * 
 * @author daniel
 *
 */
public class Beneficiario {

	/**
	 * Esta entidade não possoui contrutor padrão. Esta entidade não existe sem
	 * estes campos, por isso não faz sentido cria-lá sem eles.
	 * 
	 * @param codigo
	 * @param nome
	 */
	public Beneficiario(String codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	/**
	 * Código da Agência de Vinculação do Beneficiário definido pelo banco
	 */
	private String codigoAgencia;

	/**
	 * Código do beneficiário
	 */
	private String codigo;
	/**
	 * Nome do beneficiário
	 */
	private String nome;

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

}
