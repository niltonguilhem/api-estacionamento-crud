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


<img width="246" alt="image" src="https://user-images.githubusercontent.com/48165776/215806935-8dfc6847-f6b9-4c95-9618-5d0fe104d364.png">


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
		
**MicrometerPrometheus**

Dependência para monitorar a aplicação

		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
			<version>${micrometer.version}</version>
		</dependency>

    
**Swagger** 

Dependência para documentar a API

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>
		
    
## Running application

Para rodar a aplicação localmente, o seguinte comando maven pode ser utilizado:

```gradle
./mvn clean install run
```

## Endpoints


**GET /v1/estacionamento**

```gradle
curl 'http://localhost:8080/api/v1/estacionamento'
```

**GET ID /v1/estacionamento/**

```gradle
curl 'http://localhost:8080/api/v1/estacionamento/(numero do id)'
```

**POST /v1/estacionamento**

```gradle
curl -i -X POST -H "Content-Type:application/json" -H "partner:Downton-Park" -d "{\"disponivel\":\"true\"}" http://localhost:8080/api/v1/estacionamento
```

**PUT /v1/estacionamento**

```gradle
curl -i -X PUT -H "Content-Type:application/json" -H "partner:Downton-Park" -d "{\"disponivel\":\"false\"}" http://localhost:8080/api/v1/estacionamento/(numero do id)
```

**Health Check**

```gradle
 curl 'http://localhost:8080/actuator/health'  
```


