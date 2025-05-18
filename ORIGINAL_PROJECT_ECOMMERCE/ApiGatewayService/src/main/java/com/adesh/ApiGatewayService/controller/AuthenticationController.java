//package com.adesh.ApiGatewayService.controller;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//import java.security.Principal;
//import java.util.stream.Collectors;
////// it will show the okta authentication page
//@RestController
//@RequestMapping("/authenticate")
//public class AuthenticationController {
//
//    private final ReactiveOAuth2AuthorizedClientService clientService;
//
//    public AuthenticationController(ReactiveOAuth2AuthorizedClientService clientService) {
//        this.clientService = clientService;
//    }
//
//
////    @GetMapping("/login")
////    public ResponseEntity<AuthenticationResponse> login(
////         @AuthenticationPrincipal OidcUser oidcUser,
////            Model model,
////           @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
////
////    ){
////
////        AuthenticationResponse authenticationResponse=
////                AuthenticationResponse.builder()
////                        .userId(oidcUser.getEmail())
////                        .accessToken(client.getAccessToken().getTokenValue())
////                        .refreshToken(client.getRefreshToken().getTokenValue())
////                        .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
////                        .authorityList(oidcUser.getAuthorities()
////                                .stream()
////                                .map(GrantedAuthority::getAuthority)
////                                .collect(Collectors.toList()))
////
////                        .build();
////
////        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
////
////    }
//
//
//    @GetMapping("/print-token")
//    public Mono<String> printToken(Principal principal){
//
//        return clientService.loadAuthorizedClient("auth0",principal.getName())
//                .map(oAuth2AuthorizedClient -> {
//                    OAuth2AccessToken accessToken
//                            = oAuth2AuthorizedClient.getAccessToken();
//
//                    System.out.println("Access Token value:----> "+ accessToken.getTokenValue());
//                    return accessToken.getTokenValue();
//                })
//                .defaultIfEmpty("No Access Token found");
//    }
//}
