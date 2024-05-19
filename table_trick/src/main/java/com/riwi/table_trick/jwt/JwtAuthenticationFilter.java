package com.riwi.table_trick.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.util.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //Todos los filtros relacionados al token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos el token
        final String token = getTokenFromRequest(request);

        if (token ==null){
            //Si el token es null, le vamos a dar el control a la cadena de filtros
            filterChain.doFilter(request, response);
            return;
        }

    }

    //Nos devuelve el token
    //En el encabezado del request es donde obtenemos el token
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader  = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return  authHeader.substring(7);
        }
        return null;
    }
}
