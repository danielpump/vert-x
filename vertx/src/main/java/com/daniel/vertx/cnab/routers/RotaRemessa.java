/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Cria o roteamento para a pagina dos arquivos de remessa.
 * @author daniel
 *
 */
@Controller
public class RotaRemessa extends AbstractGeradorDeRota {	
	
	private static final String DEFAULT_PAGE = "./resource/paginas/remessa.html";
	
	@Autowired
	private EventBus barramentoDeServicos;
		
	@Override
	public void postConstruct(){
		gerarPost(getRota(), (contexto) -> {			
			barramentoDeServicos.send("gerarRemessa", contexto.getBodyAsJson());				
			contexto.response().sendFile(DEFAULT_PAGE);
		});
		gerarRota(getRota(), (contexto) -> {									
			HttpServerResponse response = contexto.response();
			response.sendFile(DEFAULT_PAGE);
		});
	}	

	protected String getRota() {
		return "/remessa";
	}


}
