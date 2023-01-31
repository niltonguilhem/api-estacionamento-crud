<h1> Estacionamento - CRUD </h1>

Aplicação para gestão de estacionamento veicular, o qual o cliente envia uma requisição
HTTP utilizando o protocolo de comunicação REST, nesta aplicação temos três método de requisição, sendo elas o verbo POST 
para criação de uma vaga que só retornará HTTPStatus 201 "CREATED" se o Handler for validado com um dos parceiros aceitos "Star-Park, Center-Park
e Downton-Park", segundo método de requisição é o GET que permite visualizar todas ou individualmente a disponibilidade das vagas, desde que o ID
consultado é existente e que o Handler seja validado com um dos parceiros aceitos "Star-Park, Center-Park e Downton-Park" e o ultima método de requisição
é o PUT que atualizara a disponibilidade da vaga, desde que o ID informado é existente e que o Handler seja validado com um dos parceiros aceitos 
"Star-Park, Center-Park e Downton-Park".

<h4>Tendo como tecnologias para o desenvolvimento dessa API:</h4>

- Spring boot/ Java 11
- JUnit
- REST
- MVC

## Pre-requisites

- Java 11
- Maven 


## Dependencies


**Health Check**

Dependência para verificar a saúde da aplicação

    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-actuator</artifactId>
			<version>2.7.2</version>
		</dependency>
    
**Swagger** 

Dependência para documentar a API

    <dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>${springfox.version}</version>
		</dependency>
    