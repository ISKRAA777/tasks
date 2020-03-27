package com.crud.tasks.config;

//import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.web.client.RestTemplate;
////
////@Configuration
////public class CoreConfiguration {
////    @Bean
////    public RestTemplate restTemplate() {
////        return new RestTemplate();
////    }
////
////    @Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2).select()
////                .apis(RequestHandlerSelectors
////                        .any()).paths(PathSelectors
////                        .any()).build();
////    }
////}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableScheduling
@EnableSwagger2
@Configuration
public class CoreConfiguration implements WebMvcConfigurer{



    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }




    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.crud.tasks.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathProvider(new BasePathAwareRelativePathProvider("http://ISKRAA777.github.io"))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API",
                "My 1st REST API created ...1902...during Kodilla course.",
                "API_1.0",
                "Terms of service",
                new Contact("Aleksander ISKRA", "http://ISKRAA777.github.io", "${admin.mail}"),
                "", "", Collections.emptyList());
    }



    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
// Required by Swagger UI configuration
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/").setCachePeriod(0);
        registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCachePeriod(0);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(0);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    class BasePathAwareRelativePathProvider extends AbstractPathProvider {
        private String basePath;

        public BasePathAwareRelativePathProvider(String basePath) {
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, ""))
                            .build().toString());
        }
    }
}
