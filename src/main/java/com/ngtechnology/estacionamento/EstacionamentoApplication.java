package com.ngtechnology.estacionamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.management.remote.JMXServerErrorException;
import javax.naming.NamingException;

@SpringBootApplication
@EnableWebMvc
public class EstacionamentoApplication {

	private static Logger logger = LoggerFactory.getLogger(EstacionamentoApplication.class);

	public static void main(String[] args)throws NamingException, JMXServerErrorException {
		logger.info("Iniciando a api controle de vagas");
		SpringApplication.run(EstacionamentoApplication.class, args);
		logger.info("API de controle de vaga iniciada e pronta para receber requisições");
	}

}
