package com.ejemploII.SpringBoot.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author Colegio 
 * @see "Esta clase va a chequear si hay un toquen válido"
 *
 * @version 2.0
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    /**
     *
     * @param request
     * @return
     */

    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        //HttpServletRequest token = getToken(request);
        //System.out.println("Valor del token: " + token);
        logger.error("Fallo el metodo commence, osea el toquen no te deja acceder");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    
}
