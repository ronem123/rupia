/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:22/12/2025
 * Time:13:24
 */


package com.ram.rupia.filters;

import com.ram.rupia.domain.entity.UserEntity;
import com.ram.rupia.repository.UserRepository;
import com.ram.rupia.service.jwt.JwtAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtAuthService jwtAuthService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        //Read authorization header
        String authHeader = request.getHeader("Authorization");

        System.out.println("JWT-TOKEN-HEADER:" + authHeader);
        //check if authHeader; if null or no Bearer let it continue without authorization
        //This could be public endpoints
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //if the jwt token is not null or has to be authorized, we check extract the jwt from request header
        String accessToken = authHeader.substring(7);
        System.out.println("JWT-TOKEN:" + accessToken);

        try {
            //validate token signature and expiry time
            //if false just return and let spring security handle this
            if (!jwtAuthService.validateAccessToken(accessToken)) {
                filterChain.doFilter(request, response);
                return;
            }

            //If valid extract userId from accessToken
            Long userId = jwtAuthService.getUserIdFromAccessToken(accessToken);

            //now get the UserEntity from DB with same userId returned by jwtAuthService
            UserEntity user = userRepository.findByMobileNumber(String.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            //once user is matched
            //Create Spring Security Authentication object
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name()))
                    );

            //set authenticationToken in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            //Any exception occurred then
            //clear context(EntryPoint / AccessDeniedHandler will handle response)
            //we have created already and is in WebSecurityConfig.java
            SecurityContextHolder.clearContext();
        }

        //continue filter chain
        filterChain.doFilter(request, response);
    }

}