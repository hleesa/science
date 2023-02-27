package com.ftence.backend.config.oauth2;

import com.ftence.backend.config.oauth2.dto.SessionUser;
import com.ftence.backend.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HelloController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    private final Intra42Service intra42Service;

    private final HttpSession httpSession;

    @GetMapping("/callTest")
    public String callTest(@LoginUser User user, OAuth2AuthenticationToken authentication) {
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

            WebClient webClient = WebClient.builder()
                    .baseUrl("https://api.intra.42.fr/v2/users/salee2/projects_users")
                    .defaultCookie("cookieKey", "cookieValue")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue())
                    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                    .build();
            webClient.method(HttpMethod.GET);

        Mono<Object> objectMono = webClient.get().retrieve().bodyToMono(Object.class);
        Object block = objectMono.share().block();

//        Mono<Test> objectMono2 = webClient.get().retrieve().bodyToMono(Test.class);
//        Test block2 = objectMono2.share().block();

        return "world";
    }

    @GetMapping("/")
    public String index(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("name", user.getIntraId());
        }
        return "loginSuccess";
    }


    @GetMapping("/world")
    public String world()
    {
        intra42Service.getData("salee2");
        return "world";
    }

    @GetMapping("/logout")
    public String logout()
    {
        return "logout";
    }

    @Data
    static class Test
    {
        private String id;
//        private Boolean validated;
//        subject sub;
    }

    static class subject
    {
        private Long id;
        private String name;
        private String slug;
        private String parent_id;
    }
}

