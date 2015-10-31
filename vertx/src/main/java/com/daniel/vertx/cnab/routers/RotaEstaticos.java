/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;

import com.daniel.vertx.cnab.handlers.requisicoes.TratarRequisoesCNABRemessa;
import com.daniel.vertx.cnab.handlers.requisicoes.TratarRequisoesEstaticas;

/**
 * Rota para os arquivos estáticos como imagens, CSS e JS.
 * @author daniel
 *
 */
public class RotaEstaticos  extends AbstractGeradorDeRota {
	
	public RotaEstaticos(Router roteador, EventBus barramentoDeServicos) {
		super(roteador, barramentoDeServicos);
			
		gerarRota(getRota(), (contexto) -> {
			contexto.response().sendFile('.' + (contexto.request().path()));		
		});
	}

	protected String getRota() {
		return "/resource/*";
	}

}
