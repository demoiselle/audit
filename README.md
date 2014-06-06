# Demoiselle Audit

É o componente do Framework Demoiselle para gravação de trilhas das ações dos usuários que pode ser usado para realização de Auditoria, sua arquitetura foi projetada para ser flexível assim como o Framework Demoiselle.

Se você tem a necessidade de responder a algumas perguntas do que esta acontecendo na sua camada de dados como:

- Quem fez a operação?
- Horário da operação?
- IP de origem
- Nome do Sistema
- O que foi realizado?
- Qual objeto foi manipulado?
- Quais foram os dados atingidos pela operação?

Então esse componente serve para você.

Esse componente foi desenhado para ser multi-sistemas podendo ser instalado em um servidor a parte e vários sistemas enviarem para ele as trilhas de auditoria.

## Processors
Um Processor tem como finalidade dar um destino que você deseja para o objeto que representa a trilha de auditoria.

Atualmente estamos disponibilizando um Processor de REST para atender a questão de ser multi-sistema e outro Processor para MONGO.

### Processor REST

Para utilizar esse Processor é necessário adicionar no pom.xml do seu projeto o seguinte trecho:

```xml
    <dependency>
        <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
        <artifactId>demoiselle-audit-processors-rest</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

#### Configuração demoiselle.properties

Para o correto funcionamento é necessário adicionar no demoiselle.properties as seguintes configurações:

Chave     | Descrição
----------|------------------------------------------------
frameworkdemoiselle.audit.processor.rest.server.url | URL do serviço REST para o envio das trilhas.

### Processor MONGO

Para utilizar esse Processor é necessário adicionar no pom.xml do seu projeto o seguinte trecho:

```xml
    <dependency>
        <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
        <artifactId>demoiselle-audit-processors-mongo</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

#### Configuração demoiselle.properties

Para o correto funcionamento é necessário adicionar no demoiselle.properties as seguintes configurações:

Chave     | Descrição
----------|------------------------------------------------
frameworkdemoiselle.audit.processor.mongo.server.url        | URL do servidor onde se encontra o MongoDB
frameworkdemoiselle.audit.processor.mongo.database.name     | Nome do database
frameworkdemoiselle.audit.processor.mongo.collection.name   | Nome da coleção
frameworkdemoiselle.audit.processor.mongo.database.user     | Usuário (se necessário)
frameworkdemoiselle.audit.processor.mongo.database.password | Senha
    
### Criando seu próprio Processor

Você poderá criar o seu próprio ponto de extensão, podendo ser um JMS, FTP, Banco de Dados NoSql, você tem a liberdade de criar, para que isso sejá possível você deve criar um projeto Maven e utilizar o parent no seu pom.xml:

```xml
<parent>
    <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
    <artifactId>demoiselle-audit-parent</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</parent>
```

Criar uma classe que extenda de **br.gov.frameworkdemoiselle.component.audit.implementation.processor.AbstractProcessor** e implementar o método **public void execute(@Observes @AuditProcessor Trail trail)**.

É dentro do bloco de código você implementará o destino que você deseja dar ao objeto Trail.

Para o bom funcionamento do seu Processor você deverá seguir as seguintes recomendações:

- Antes de implementar seu código é necessário chamar o método **super.execute(trail)** para que o componente possa saber qual é a classe do Processor que esta sendo processada, isso é necessário para a situação de falha na execução do seu Processor e o componente possa reprocessar novamente no futuro;
- Quando houver algum tratamento de exceção no seu código como blocos try e catch você deverá além de realizar seu próprio tratamento a chamada do método **fail([String com a mensagem de Erro], [Objeto Trail]);** para que o componente possa reprocessar novamente no futuro;

Você pode basear sua implementação no código do RESTProcessor no caminnho *impl/processors/rest/src/main/java/br/gov/frameworkdemoiselle/component/audit/processors/rest/RESTProcessors.java*

## Auditors

O objetivo dos Auditors é estabelecer a camada que será auditada, atualmente exste dois Auditors: camada de Persistência chamada PersistenceAuditor e para Visão chamado ViewAuditor.

Um Auditor tem como objetivo executar a coleta de dados, preenchimento do objeto Trail com dados e disparar evento para o Processors.

Os dados coletados pelos Auditor são:

- Quem fez a operação?
- Horário da operação?
- IP de origem
- Nome do Sistema
- O que foi realizado?
- Qual objeto foi manipulado?
- Quais foram os dados atingidos pela operação?

### Auditor de Persistência

Para utilizar esse Auditor é necessário adicionar no pom.xml do seu projeto o seguinte trecho:

```xml
    <dependency>
        <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
        <artifactId>demoiselle-audit-auditors-persistence</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

### Auditor de Visão

Para utilizar esse Auditor é necessário adicionar no pom.xml do seu projeto o seguinte trecho:

```xml
    <dependency>
        <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
        <artifactId>demoiselle-audit-auditors-view</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

### Criando seu próprio Auditor

Se você tiver interesse em auditar outra camada da sua aplicação você deverá:

- Criar um projeto Maven e utilizar o parent no seu pom.xml

```xml
<parent>
    <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
    <artifactId>demoiselle-audit-parent</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</parent>
```

- Criar uma classe que extenda de **br.gov.frameworkdemoiselle.component.audit.implementation.auditors.AbstractAuditor**;
- Implementar os métodos que irão interceptar o ciclo de vida da camada;
- Criar um objeto do tipo Trail e preencher seus dados básicos;
- Chamar o método da **consume([Objeto Trail])**;

Caso seu Processor precise ser configurado, você deverá criar uma Classe que utilize a funcionalidade @Configuration do Demoiselle http://demoiselle.sourceforge.net/docs/framework/reference/2.4.0/html/configuracao.html, 
como exemplo você poderá se basear na classe *impl/processors/rest/src/main/java/br/gov/frameworkdemoiselle/component/audit/processors/rest/RESTConfig.java*

Apartir desse momento o componente estará apto a repassar esse objeto para os Processors definidos no pom.xml do seu projeto.

Na sua implementação do seu sistema você deverá informar as seguintes informações:

Dado      | Local de Preenchimento
----------|------------------------------------------------
Profile   | user.setAttribute("PROFILE", [Profile/Perfil do usuário no sistema])
Where     | user.setAttribute("IP", [IP do usuário no sistema])
UserName  | user.setAttribute("NAME", [Login/Nome/Identificador do usuário no sistema])

Maiores informações no link http://demoiselle.sourceforge.net/docs/framework/reference/2.4.0/html/security.html

Um exemplo de um Auditor esta na classe *impl/auditors/persistence/src/main/java/br/gov/frameworkdemoiselle/component/audit/auditors/persistence/PersistenceAuditor.java*

## Como utilizar

### pom.xml
Em seu projeto que você deseja auditar, você deve adicionar no seu pom.xml no campo de dependências do projeto as seguintes linhas:

```xml
<dependencies>
    ...
    <!-- Auditor -->
    <dependency>
        <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
        <artifactId>demoiselle-audit-auditors-persistence</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
        <artifactId>demoiselle-audit-auditors-view</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

    <!-- Processor -->
    <dependency>
       	<groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
      	<artifactId>demoiselle-audit-processors-rest</artifactId>
       	<version>1.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>br.gov.frameworkdemoiselle.component.audit</groupId>
        <artifactId>demoiselle-audit-processors-mongo</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    ...
</dependencies>
```

No código acima estamos utilizando o Auditor para a camada de Persistência e para a camada de Visão e o Processor para REST e o Processor para MONGO, você poderá adicionar quantos Processors e quantos Auditors você quiser.

### demoiselle.properties

No seu arquivo demoiselle.properties você precisará adicionar as seguintes linhas:

Chave                                         | Valor
--------------------------------------------------- | --------------------------
frameworkdemoiselle.audit.system                    | Nome do seu Sistema
frameworkdemoiselle.audit.scheduler.repeat.interval | Intervalo de tempo em milisegundos para o reprocessamento das trilhas
frameworkdemoiselle.audit.scheduler.start.time      | Tempo em milisegundos de quando será executado o primeiro reprocessamento após o inicio da aplicação
frameworkdemoiselle.audit.folder.fail.objects       | Pasta onde a aplicação terá direito de escrita e leitura para o armazenamento das trilhas que apresentaram problemas no processamento

Para o Processor de REST você deverá adicionar a seguinte linha:

Chave                         | Valor
------------------------------| --------------------------
frameworkdemoiselle.audit.processor.rest.server.url | Endereço do Serviço REST (Ex.: http://localhost:8080/dash)

Para o Processor de MONGO você deverá adicionar a seguinte linha:

Chave                         | Valor
------------------------------| --------------------------
frameworkdemoiselle.audit.processor.mongo.server.url      | Endereço do MongoDB
frameworkdemoiselle.audit.processor.mongo.database.name   | Nome do Database
frameworkdemoiselle.audit.processor.mongo.collection.name | Nome da Coleção
frameworkdemoiselle.audit.processor.mongo.database.user     | Usuário (se necessário)
frameworkdemoiselle.audit.processor.mongo.database.password | Senha

### PersistenceAuditor
Você poderá escolher quais serão suas Entidades JPA que serão auditadas na camada de persistência, para isso adicione a anotação do JPA @EntityListeners com o valor de PersistenceAuditor.class.

Exemplo

```java
@Entity
@EntityListeners(value = PersistenceAuditor.class)
public class Bookmark implements Serializable {
    ...
}
```

## Dashboard

Para facilitar a busca de informações do que foi feito no sistema contruímos um Dashboard que traz 4 visões diferentes dos mesmos dados:

- Visão de Sistema que se aprofunda em Pessoa e a Funcionalidade executada;
- Visão da Pessoa que se aprofunda no Sistema e a Funcionalidade executada;
- Visão de Funcionalidade que se aprofunda no Sistema e a Pessoa que executou;
- Visão de Rastreamento que através de filtros como Sistema, Objeto e Identificador do Objeto mostrar o histórico de operações realizadas no Objeto;

O Dashboard também fornece um serviço de REST para o Processor RESTProcessor, caso você utilize este processor no seu projeto e não queira implementar seu próprio serviço de REST você poderá utilizar o Dashboard para essa finalidade.

### Como instalar o Dashboard

Faça um clone do projeto e acesse a pasta **demoiselle-audit/impl/dashboard**, você precisará alterar o arquivo persistence.xml de acordo com as configurações do seu banco de dados onde serão armazenados as trilhas de auditoria, após feito essa configuração abra um terminal na pasta do "dashboard" e execute o comando maven:

```
    $mvn clean compile package
```

Esse comando irá gerar um arquivo chamado **demoiselle-audit-dashboard.war** na pasta target.

Agora que você já tem o arquivo .war você poderá fazer o deploy no seu servidor.

## Exemplo de Aplicação

Você encontrará no link https://github.com/demoiselle/laboratory/tree/master/examples o projeto BookmarkAudit que utiliza o componente de auditoria;

## Recomendações

Por se tratar de um componente de auditoria alguns pontos devem ser observados:

- O servidor onde serão armazenadas as trilhas de auditoria deverá ser blindado e com acesso restrito para que seja garantida a confiabilidade das informações armazenadas;
- Seguir as recomendações do NIST no link
http://csrc.nist.gov/publications/nistpubs/800-92/SP800-92.pdf

## Links úteis

- Documentação: Aprenda agora! *(por enquanto o próprio README.md, estamos trabalhando para criar uma documentação no formato docbook)*
- [Fórum](https://github.com/demoiselle/laboratory/issues): Abra um issue para discutirmos um assunto.
- [Bugs e Sugestões](https://github.com/demoiselle/laboratory/issues): Abra um issue para submeter e acompanhar Bugs, enviar dúvidas, propor melhorias ou novas funcionalidades
- Catálogo de Arquétipos Snapshot: Para versões de desenvolvimento (snapshot)
- [Aplicação de Exemplo](https://github.com/demoiselle/laboratory/tree/master/examples/bookmark-audit)

## Repositório Maven

A publicar

## Todo

- Melhoria nos testes;
- Arquétipos para Auditors e Processors;

## Contribuindo

Faça um clone do projeto e ajude a crescer esse projeto :)