/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.ext.web.Router;

import com.daniel.vertx.cnab.handlers.TratarRequisoesCNABRemessa;

/**
 * Cria o roteamento para a pagina dos arquivos de remessa.
 * @author daniel
 *
 */
public class RotaRemessa extends AbstractGeradorDeRota {

	public RotaRemessa(Router roteador) {
		super(roteador);
		gerarPost(getRota(), TratarRequisoesCNABRemessa.tratarPost());
		gerarRota(getRota(), TratarRequisoesCNABRemessa.tratarRota());
	}

	protected String getRota() {
		return "/remessa";
	}


}
