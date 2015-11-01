/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import org.springframework.stereotype.Controller;

/**
 * Rota para os arquivos estáticos como imagens, CSS e JS.
 * @author daniel
 *
 */
@Controller
public class RotaEstaticos  extends AbstractGeradorDeRota {
	
	@Override
	public void postConstruct() {
		gerarRota(getRota(), (contexto) -> {
			contexto.response().sendFile('.' + (contexto.request().path()));		
		});		
	}
	
	protected String getRota() {
		return "/resource/*";
	}

}
