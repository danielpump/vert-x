/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;

import com.daniel.vertx.cnab.handlers.requisicoes.TratarRequisoesCNABRemessa;

/**
 * Cria o roteamento para a pagina dos arquivos de remessa.
 * @author daniel
 *
 */
public class RotaRemessa extends AbstractGeradorDeRota {
	
	private TratarRequisoesCNABRemessa tratamento;

	public RotaRemessa(Router roteador, EventBus barramentoDeServicos) {
		super(roteador, barramentoDeServicos);
		tratamento = new TratarRequisoesCNABRemessa(barramentoDeServicos);
		gerarPost(getRota(), tratamento.tratarPost());
		gerarRota(getRota(), tratamento.tratarRota());
	}

	protected String getRota() {
		return "/remessa";
	}


}
