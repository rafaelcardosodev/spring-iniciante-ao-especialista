package com.rafael.gvendas.gestaovendas.controllers.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão de Vendas")
                        .description("Sistema de gestão de vendas")
                        .version("1.0.0"));
    }
}
