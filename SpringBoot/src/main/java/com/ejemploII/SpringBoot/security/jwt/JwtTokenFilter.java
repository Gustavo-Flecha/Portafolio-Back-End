package com.ejemploII.SpringBoot.security.jwt;

import com.ejemploII.SpringBoot.security.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Colegio
 * @see "Esta clase va a usar la clase del provider para validar el token de
 * nuevo, por ejemplo cuando queremos modificar algo se va a ejecutar esta clase
 * primero y si no lo está nos va a pedir que nos loguemos"
 * @see {@link OncePerRequestFilter} nos realiza una petición por ves
 * 
 * @version 2.0
 */
public class JwtTokenFilter extends OncePerRequestFilter {

   // @SuppressWarnings("FieldNameHidesFieldInSuperclass")este parar que deje de mandarmen el warning
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailsImpl userDetailsServiceImpl;

    @Override
    //@SuppressWarnings("UseSpecificCatch")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken auth
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (UsernameNotFoundException e) {//antes aquí era catch (Exception e) {
            logger.error("Falló el método doFilterInternal");
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer", "");
        }
        return null;
    }
}
