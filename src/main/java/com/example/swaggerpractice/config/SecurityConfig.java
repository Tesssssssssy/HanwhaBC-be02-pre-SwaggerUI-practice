package com.example.swaggerpractice.config;

import com.example.swaggerpractice.config.filter.JwtFilter;
import com.example.swaggerpractice.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.swaggerpractice.member.service.MemberService;
import com.example.swaggerpractice.member.service.UserOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberService memberService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final UserOAuth2Service userOAuth2Service;


    @Value("${jwt.secret-key}")
    private String secretKey;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {

            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/jwt/*").permitAll()
                    .antMatchers("/member/*").permitAll()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/test/*").hasRole("USER")
                    .anyRequest().authenticated();
            http.addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class);
            http.formLogin().disable();
            http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.oauth2Login()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .userInfoEndpoint()
                    .userService(userOAuth2Service);

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
