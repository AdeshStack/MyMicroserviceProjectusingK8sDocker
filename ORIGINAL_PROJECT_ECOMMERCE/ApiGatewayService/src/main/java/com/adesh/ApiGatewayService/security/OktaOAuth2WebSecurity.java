//package com.adesh.ApiGatewayService.security;
//
//import lombok.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
//import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
//import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Consumer;
//
//@Configuration
//@EnableWebFluxSecurity
//@ConfigurationProperties(prefix = "auth0")
//public class OktaOAuth2WebSecurity {
//
//    private String audience;
//
//    private  final ReactiveClientRegistrationRepository repository;
//
//    public OktaOAuth2WebSecurity(ReactiveClientRegistrationRepository repository) {
//        this.repository = repository;
//    }
//
//    @Bean
//    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
//
//
//        return httpSecurity
//                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
//                .oauth2Login(oAuth2LoginSpec ->
//                        oAuth2LoginSpec.authorizationRequestResolver(
//                                authorizationRequestResolver(repository)
//                        ))
//
//                .build();
//
//    }
//
//    private ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(
//         ReactiveClientRegistrationRepository repository
//    ){
//        DefaultServerOAuth2AuthorizationRequestResolver resolver
//                =new DefaultServerOAuth2AuthorizationRequestResolver(repository);
//        resolver.setAuthorizationRequestCustomizer(builderCustomizer());
//
//        return resolver;
//
//
//    }
//    private Consumer<OAuth2AuthorizationRequest.Builder> builderCustomizer(){
//
//        return customizer ->customizer
//                .additionalParameters(
//                        params->params.put("audience",audience)
//
//                );
//    }
//}
