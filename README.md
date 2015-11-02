# vert-x
Projeto utilizando as funcionalidades básicas do framework vert-x na linguagem Java.

Utilizando vert-x na versão 3.1.0

Java 8

Maven 3

Spring 4

#Executar o projeto
Para executar o projeto o gerenciador de dependecias maven e o Java na versão 8 devem estar configurados na máquina.

Entrar no diretorio vertx do projeto e executar o comando:
> mvn clean package

Após a finalização do comando acima, executar o seguinte comando:
>java -jar target/vex-1.0-SNAPSHOT-fat.jar 

Após a mensagem:
>INFORMAÇÕES: Succeeded in deploying verticle

Acessar a URL:
>[http://localhost:8080/](http://localhost:8080/)

E navegar pela aplicação

#Integração CNAB
As classes referentes a integrão CNAB400 está disponíveis a parit da raiz de pacote: 
>com.daniel.vertx.cnab

Afim de testar as funcionalidades de escrita de arquivo e o processamento rest do Vert-x, foi desenvolvida a funcionalidade de escrita do
arquivo de remessa do protocolo CNAB400(Documento em anexo). 

Como é um exemplo foi desenvolvida apenas a escrita do header do arquivo, mas com isso já foi possivel ver como o framework se comporta. A
escrita funciona a partir de uma requisição rest post. Tambem foi desenvolvida uma tela simples que é acessada a partir 
da [URL local /remessa] (http://localhost:8080/remessa)

Após o framework Spring ter sido adicionado, a forma como o sistema de roteamento é carregado, passou a ser feita a partir da injecção 
dedependencia. A partir de agora a forma como os routers e os workers são registrados se tornou implícita, o simples fato de registra-los 
com as classes/interfaces respectivas de cada tipo já faz com que o contexto da aplicação os e registre e utilize.
 
#Persistência com restfull


#Repositório Verticle
Repositório com exemplos da versão 3.1.0:

[Repositório de exemplos](https://github.com/vert-x3/vertx-examples)



