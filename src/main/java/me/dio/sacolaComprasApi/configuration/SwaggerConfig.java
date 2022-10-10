package me.dio.sacolaComprasApi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 10/10/2022.
 */
@Configuration
public class SwaggerConfig {
    //Arquivo de config do swagger
    @Bean
    public Docket getBean() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .paths(PathSelectors.regex("/ifood-dev-week/.*"))
                .build()
                .apiInfo(getInfo());
    }
    private ApiInfo getInfo() {
        return new ApiInfoBuilder()
                .title("Sacola de Compras API")
                .description("Minha primeira API Rest para servir uma Aplicação de Delivery," +
                        "desenvolvida na semana ifood Dev week.")
                .build();
    }
}
