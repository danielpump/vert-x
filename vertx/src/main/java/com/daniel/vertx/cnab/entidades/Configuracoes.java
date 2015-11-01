/**
 * 
 */
package com.daniel.vertx.cnab.entidades;

/**
 * Entidade com as configurações de caminhos e valores que serão utilizados na geração do arquivo CNAB
 * @author daniel
 *
 */
public class Configuracoes {
	
	public String getCaminhoRemessa(){
		return System.getProperty("user.home").concat(System.getProperty("file.separator")).concat("CNAB400")
				.concat(System.getProperty("file.separator")).concat("REMESSA").concat(System.getProperty("file.separator"));
	}

}
