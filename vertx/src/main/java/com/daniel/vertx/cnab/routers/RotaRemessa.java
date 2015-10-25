/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import com.daniel.vertx.cnab.handlers.TratarRequisoesCNABRemessa;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
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
		gerarRota(getRota(), TratarRequisoesCNABRemessa.tratarRota());
		gerarPost(getRota(), TratarRequisoesCNABRemessa.tratarPost());
	}

	protected String getRota() {
		return "/remessa";
	}

	protected Handler<RoutingContext> getTratarRota() {
		return TratarRequisoesCNABRemessa.tratarRota();
	}


}
