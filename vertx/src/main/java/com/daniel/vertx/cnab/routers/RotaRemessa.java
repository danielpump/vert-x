/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import com.daniel.vertx.cnab.handlers.requisicoes.TratarRequisoesCNABRemessa;

/**
 * Cria o roteamento para a pagina dos arquivos de remessa.
 * @author daniel
 *
 */
public class RotaRemessa extends AbstractGeradorDeRota {	
	
	private static final String DEFAULT_PAGE = "./resource/paginas/remessa.html";

	public RotaRemessa(Router roteador, EventBus barramentoDeServicos) {
		super(roteador, barramentoDeServicos);	
		gerarPost(getRota(), (contexto) -> {
			HttpServerResponse response = contexto.response();
			response.sendFile(DEFAULT_PAGE);
		});
		gerarRota(getRota(), (contexto) -> {									
			barramentoDeServicos.send("gerarRemessa", contexto.getBodyAsJson());				
			contexto.response().sendFile(DEFAULT_PAGE);
		});
	}

	protected String getRota() {
		return "/remessa";
	}


}
