package com.ngtechnology.estacionamento.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.ngtechnology.estacionamento.controller"))
                    .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Vagas", "Gerencia Vagas de Estacionamento"));
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Estacionamento API")
                .description("API aberta para consulta de vagas disponíveis.")
                .version("1.1")
                .contact(new Contact(
                        "NGTechnology", "https://www.ngtechnology.com","contato@ngtechnology.com"))
                .build();
    }
}
