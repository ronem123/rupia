/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:22/12/2025
 * Time:11:46
 */


package com.ram.rupia.config;

import com.ram.rupia.exception.JwtAccessDeniedHandler;
import com.ram.rupia.exception.JwtAuthenticationEntryPoint;
import com.ram.rupia.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        httpSecurity
                //disable csrf (Cross-Site Request Forgery, as we are not storing any cookies, and we do authenticate via jwt
                .csrf(AbstractHttpConfigurer::disable)
                //make application stateless
                //✔ No HTTP session created
                //✔ No JSESSIONID cookie
                //✔ Every request must carry JWT
                .sessionManagement(
                        sessionConfig ->
                                sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //check which api endpoint should have to be authorized
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers("/auth/otp-verify").permitAll()
                                .requestMatchers("/customers/register").permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}