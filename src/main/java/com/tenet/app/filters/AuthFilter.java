package com.tenet.app.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, HttpClientErrorException {
        String token = request.getHeader("token");
        try {
            LOGGER.info("AuthFilter caught exception, ", token);
            if (token != null) {
                List<String> authorities = new ArrayList<>();

                UsernamePasswordAuthenticationToken auth = new
                        UsernamePasswordAuthenticationToken("Welcome", null, authorities.stream()
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            }
        } catch (HttpClientErrorException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "REST signature failed validation.");
            return;
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);

    }

}
