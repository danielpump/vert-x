/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Cria o roteamento para a pagina dos arquivos de remessa.
 * @author daniel
 *
 */
public class RotaRemessa extends AbstractGeradorDeRota {

	public RotaRemessa(Router roteador) {
		super(roteador);
	}

	@Override
	protected String getRota() {
		return "/remessa";
	}

	@Override
	protected Handler<RoutingContext> getHandler() {
		// TODO Auto-generated method stub
		return null;
	}


}
