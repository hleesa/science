package com.ftence.backend.config.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class Intra42Service {

    private static final String INTRA42_API_BASE_URL = "https://api.intra.42.fr/v2";

    private final WebClient client = WebClient.create(INTRA42_API_BASE_URL);

    public Mono<Object> getData(String intraId){
        return client.get().uri("/uses/{intraId}/project+users")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

}
