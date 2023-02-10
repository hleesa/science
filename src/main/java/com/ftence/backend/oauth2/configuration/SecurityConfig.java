package com.ftence.backend.oauth2.configuration;

import com.ftence.backend.oauth2.extractor.KakaoAuthoritiesExtractor;
import com.ftence.backend.oauth2.extractor.KakaoPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/login**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .disable()
                .oauth2Login()
                .defaultSuccessUrl("/loginSuccess", true);
        return http.build();
    }

    @Bean
    @Profile("oauth2-extractors-kakao")
    public PrincipalExtractor kakaoPrincipalExtractor() {
        return new KakaoPrincipalExtractor();
    }

    @Bean
    @Profile("oauth2-extractors-kakao")
    public AuthoritiesExtractor kakaoAuthoritiesExtractor() {
        return new KakaoAuthoritiesExtractor();
    }
}
