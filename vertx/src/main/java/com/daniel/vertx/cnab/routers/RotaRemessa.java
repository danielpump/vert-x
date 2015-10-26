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
	
	private TratarRequisoesCNABRemessa tratamento;

	public RotaRemessa(Router roteador) {
		super(roteador);
		tratamento = new TratarRequisoesCNABRemessa();
		gerarPost(getRota(), tratamento.tratarPost());
		gerarRota(getRota(), tratamento.tratarRota());
	}

	protected String getRota() {
		return "/remessa";
	}


}
