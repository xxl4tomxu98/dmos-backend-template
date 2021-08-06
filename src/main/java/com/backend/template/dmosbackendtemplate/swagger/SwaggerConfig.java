package com.backend.template.dmosbackendtemplate.swagger;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.hateoas.client.LinkDiscoverer;
//import org.springframework.hateoas.client.LinkDiscoverers;
//import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
//import org.springframework.plugin.core.SimplePluginRegistry;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.OAuthBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

//    @Value("${dmos.config.show-swagger-ui}")
//    private boolean showSwaggerUi;
//    @Value("${keycloak.auth-server-url}")
//    private String authURL;
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Bean
//    public Docket apiDocumentation() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(Arrays.asList(securityScheme()))
//                .securityContexts(Arrays.asList(securityContext()))
//                .apiInfo(apiInfo())
//                .enable(true);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Dmos Test Application")
//                .description("Dmos Tempalte Application")
//                .license("")
//                .licenseUrl("http://unlicense.org")
//                .termsOfServiceUrl("")
//                .version("0.0.1")
//                .build();
//    }
//    private SecurityScheme securityScheme() {
//        return new OAuthBuilder()
//                .name("spring_oauth")
//                .grantTypes(grantTypes())
//                .build();
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", new AuthorizationScope[] {})))
//                .forPaths(PathSelectors.regex("/api.*"))
//                .build();
//    }
//
//    private List<GrantType> grantTypes() {
//        GrantType grantType = new ClientCredentialsGrant(authURL + "/realms/" + realm + "/protocol/openid-connect/token");
//        return Arrays.asList(grantType);
//    }
//
//
//    @Bean
//    public LinkDiscoverers discovers() {
//        List<LinkDiscoverer> plugins = new ArrayList<>();
//        plugins.add(new CollectionJsonLinkDiscoverer());
//        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//    }
}