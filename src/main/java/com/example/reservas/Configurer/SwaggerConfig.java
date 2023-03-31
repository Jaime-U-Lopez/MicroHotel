package com.example.reservas.Configurer;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build();
    }

}

    /*


    @Bean
    public  Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                // .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())

//                .apis(RequestHandlerSelectors.basePackage("com.example.pruebaswager.Controller"))
  //              .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfoMetaData());
    }



    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("NAME OF SERVICE")
                .description("API Endpoint Decoration")
                .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}

    /*

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.codmind.swaggerapi.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }



    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Order Service API",
                "Order Service API Description",
                "1.0",
                "http://codmind.com/terms",
                new Contact("Codmind", "https://codmind.com", "apis@codmind.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }

}
*/
