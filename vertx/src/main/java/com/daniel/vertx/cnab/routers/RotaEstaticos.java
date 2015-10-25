/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.ext.web.Router;

import com.daniel.vertx.cnab.handlers.TratarRequisoesCNABRemessa;
import com.daniel.vertx.cnab.handlers.TratarRequisoesEstaticas;

/**
 * Rota para os arquivos estáticos como imagens, CSS e JS.
 * @author daniel
 *
 */
public class RotaEstaticos  extends AbstractGeradorDeRota {
	
	public RotaEstaticos(Router roteador) {
		super(roteador);
		gerarRota(getRota(), TratarRequisoesEstaticas.tratarRota());
	}

	protected String getRota() {
		return "/resource/*";
	}

}
