/**
 * 
 */
package com.daniel.framework.cnab.remessa;

import com.google.common.base.Strings;

/**
 * Interface para escrita dos registros do cabeçalho do arquivo de remessa CNAB400.
 * Os metodos da interface retornam os campos do cabecalho.
 * A implementação da interface deve retornar os campos devidamente formatados, assim como os campos default da interface já fazem.
 * @author daniel
 *
 */
public interface CNAB400Cabecalho {
	
	/**Código Identificador do tipo de Registro no Arquivo
	 * @return
	 */
	default String codigoRegistro(){
		return "0";//Padrão definido na documentação
	}
	/**Código Identificador da Remessa para a CAIXA 
	 * @return
	 */
	default String codigoRemessa(){
		return "1";//Padrão definido na documentação
	}
	/**Literal Correspondente ao Código da Remessa 
	 * @return
	 */
	default String literalRemessa(){
		return "REM.TST";//Como é um exemplo o padrão foi definido para que seja sempre em modo de teste, como define o documento. 
	}
	/**Código Identificador do Tipo de Serviço 
	 * @return
	 */
	default String codigoServico(){
		return "01";//Padrão definido na documentação
	}
	/**Literal Correspondente ao Código de Serviço
	 * @return
	 */
	default String literalServico(){
		return Strings.padStart("COBRANCA", 15, ' ').toUpperCase();//Como determina a documentacao o valor padrão para remessas é COBRANCA
	}
	/**Código da Agência de vinculação do Beneficiário 
	 * @return
	 */
	String codigoAgencia();
	/**Código Identificador da Empresa na CAIXA
	 * @return
	 */
	String codigoBeneficiario();
	/**Uso Exclusivo CAIXA 
	 * @return
	 */
	default String usoExclusivo1(){
		return Strings.padStart(" ", 10, ' ');//Retornar 10 posições em branco como define o documento
	}
	/**Nome por extenso da Empres
	 * @return
	 */
	String nomeEmpresa();
	/**Código do Banco na Compensação
	 * @return
	 */
	String codigoBanco();
	/**Nome do Banco 
	 * @return
	 */
	String nomeBanco();
	/**Data de Geração do Arquivo
	 * @return
	 */
	String dataGeracao();
	/**Uso Exclusivo CAIXA
	 * @return
	 */
	default String usoExclusivo2(){
		return Strings.padStart(" ", 289, ' ');//Retornar 289 posições em branco como define o documento
	}
	/**Número Seqüencial do Arquivo Remessa 
	 * @return
	 */
	String numeroSequencialA();
	/**Número Sequencial do Registro no Arquivo
	 * @return
	 */
	String numeroSequencialB();
	

}
