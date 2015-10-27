/**
 * 
 */
package com.daniel.vertx.cnab.handlers;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;

/**
 * Trata as mensagens para a finalização de arquivos
 * @author daniel
 *
 */
public class FinalizacaoArquivo implements Handler<AsyncResult<Void>> {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("FinalizacaoArquivo"));

	@Override
	public void handle(AsyncResult<Void> resultado) {
		
		if(resultado.succeeded()){
			logger.debug("Arquivo escrito ou gerado com sucesso");			
		}  else {
			logger.info("Escrita ou criação do arquivo não foi finalizada");
			logger.error("O arquivo não foi escrito ou escrito, motivo: ", resultado.cause());
		}
		
	
		
	}

}
