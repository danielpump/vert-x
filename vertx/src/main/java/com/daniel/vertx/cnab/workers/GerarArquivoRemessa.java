/**
 * 
 */
package com.daniel.vertx.cnab.workers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;

import com.daniel.vertx.cnab.conversores.ConverterBodyParaBanco;
import com.daniel.vertx.cnab.conversores.ConverterBodyParaBeneficiario;
import com.daniel.vertx.cnab.entidades.Banco;
import com.daniel.vertx.cnab.entidades.Beneficiario;
import com.daniel.vertx.cnab.handlers.FinalizacaoArquivo;
import com.daniel.vertx.cnab.remessa.VertxCNAB400Cabecalho;

/**
 * Classe responsavel por escrever o arquivo de remessa
 * @author daniel
 *
 */
public class GerarArquivoRemessa extends AbstractVerticle {
	
	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("EscreverArquivoRemessa"));
	
	FinalizacaoArquivo tratamentoFinalizacaoArquivo = new FinalizacaoArquivo();
	
	@Override
	public void start() throws Exception {
		logger.info("Registrando worker EscreverArquivoRemessa");
		
		vertx.eventBus().consumer("gerarRemessa", new Handler<Message<JsonObject>>() {
			@Override
			public void handle(Message<JsonObject> event) {
				
				Banco banco = new ConverterBodyParaBanco().converterPara(event.body());
								
				Beneficiario beneficiario  = new ConverterBodyParaBeneficiario().converterPara(event.body());
				
				VertxCNAB400Cabecalho cabecalho = new VertxCNAB400Cabecalho(banco, beneficiario);
				
				String arquivoRemessa = gerarNomeArquivo();
				
				vertx.fileSystem().mkdirs(gerarCaminhoArquivo(), tratamentoFinalizacaoArquivo);
				
				vertx.fileSystem().writeFile(arquivoRemessa
						, Buffer.buffer(gerarCabecalho(cabecalho)), tratamentoFinalizacaoArquivo);
			}

			/**
			 * @return
			 */
			private String gerarNomeArquivo() {
				return gerarCaminhoArquivo()
						.concat(String.valueOf(System.currentTimeMillis())).concat(".REM");
			}

			/**
			 * @return
			 */
			private String gerarCaminhoArquivo() {
				return System.getProperty("user.home").concat(System.getProperty("file.separator")).concat("CNAB400")
						.concat(System.getProperty("file.separator")).concat("REMESSA").concat(System.getProperty("file.separator"));
			}

			/**
			 * Retorna o cabeçalho em formatado para escrita
			 * @param cabecalho
			 * @return
			 */
			private String gerarCabecalho(VertxCNAB400Cabecalho cabecalho) {
				return "Teste";
			}
		});
		
	}

	
}
