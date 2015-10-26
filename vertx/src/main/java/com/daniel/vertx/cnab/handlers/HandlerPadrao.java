/**
 * 
 */
package com.daniel.vertx.cnab.handlers;

import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.web.RoutingContext;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

/**
 * @author daniel
 *
 */
public abstract class HandlerPadrao {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("HandlerPadrao"));
	
	/**
	 * Converte o corpo da requisição para um mapa com as chaves dos objetos 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected HashMap<String, String> converterBodyParaMapa(RoutingContext context) {
		HashMap<String, String> mappedJson = null; 
		String corpoRequest = context.getBodyAsString();
		corpoRequest = tratarRequisicoesJQueryStringify(corpoRequest);
		try {
			mappedJson = new ObjectMapper().readValue(corpoRequest, HashMap.class);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return mappedJson;
	}
	
	/**
	 * Quando é utilizada a função do JQuery.stringify, ele adiciona as chaves no começo da lista e isso quebra a execução.
	 * @param corpoRequest
	 * @return
	 */
	protected String tratarRequisicoesJQueryStringify(String corpoRequest) {
		if (corpoRequest.startsWith("[")) {
			corpoRequest = corpoRequest.substring(1);
		}
		if (corpoRequest.endsWith("]")) {
			corpoRequest = corpoRequest.substring(0,
					(corpoRequest.length() - 1));
		}
		return corpoRequest;
	}
	
	protected boolean validarCamposDoMapa(HashMap<String, String> camposParaValidar){
		for (String campoDaLista : camposParaValidar()) {
			if(Strings.isNullOrEmpty(camposParaValidar.get(campoDaLista))){
				throw new IllegalArgumentException(MessageFormat.format("Campo {0} está com valor inválido branco ou inexistente", campoDaLista));
			}
		}
		return true;
	}

	protected abstract List<String> camposParaValidar();

}
