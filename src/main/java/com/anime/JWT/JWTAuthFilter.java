package com.anime.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authReader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        if(authReader == null || authReader.isBlank()){
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authReader.substring(7);
        userEmail = jwtUtil.extractUsername(jwtToken);

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(userEmail);

            if(jwtUtil.isTokenValid(jwtToken, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
