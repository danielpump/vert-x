/**
 * 
 */
package com.daniel.vertx.cnab.entidades;

/**
 * Classe que presenta o banco(Instituição financeira).
 * 
 * @author daniel
 *
 */
public class Banco {
	
	/**
	 * Esta entidade não possoui contrutor padrão. Esta entidade não existe sem
	 * estes campos, por isso não faz sentido cria-lá sem eles.
	 * 
	 * @param codigo
	 * @param nome
	 */
	public Banco(String codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	
	/**
	 * Código do banco fornecido de acordo com o banco central 
	 */
	private String codigo;
	/**
	 * Nome do banco de acordo com o do protocolo CNAB400
	 */
	private String nome;
	

	public String getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}

}
