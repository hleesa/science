package com.ftence.backend.config.oauth2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final OAuth2AuthorizedClientService authorizedClientService;
    @GetMapping("/callTest")
    public String callTest(OAuth2AuthenticationToken authentication) {
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

            WebClient webClient = WebClient.builder()
                    .baseUrl("https://api.intra.42.fr/v2/users/salee2/projects_users")
                    .defaultCookie("cookieKey", "cookieValue")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue())
                    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                    .build();
            webClient.method(HttpMethod.GET);


        String result = webClient.get().retrieve().bodyToMono(String.class).block();


        ObjectMapper objectMapper = new ObjectMapper();

//        Map<String, Object> map = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {});
        System.out.println(result);

        return "world";
    }

//    static class test
//    {
//        private
//    }
/*
    @GetMapping("/world")
    public String world()
    {
        return "world";
    }

    @GetMapping("/logout")
    public String logout()
    {
        return "logout";
    }

    */
}
