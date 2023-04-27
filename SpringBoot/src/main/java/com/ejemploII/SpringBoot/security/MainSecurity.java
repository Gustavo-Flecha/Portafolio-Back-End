package com.ejemploII.SpringBoot.security;

import com.ejemploII.SpringBoot.security.jwt.JwtEntryPoint;
import com.ejemploII.SpringBoot.security.jwt.JwtTokenFilter;
import com.ejemploII.SpringBoot.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Colegio
 * @version 2.0
 *
 * @see "Esta es la clase que va a controlar practicamente todo el Jwt (JSON Web
 * Token)"
 * @see {@link @EnableGlobalMethodSecurity} "Habilita la opción de que los
 * métodos que dictemos que tenemos que estar logueados van a estar bloqueados
 * por esta anotation y con true le decimos que tenmos que estar logueados
 * previamente"
 * 
 * @see "Para resolver un problema que casi me hace rendirme y no me generaba el token, 
 * es una dependencia nada más, porque desde Java 11 ya no trae internamente:
 *  {@link https://www-studytonight-com.translate.goog/post/solved-javalangnoclassdeffounderror-javaxxmlbindjaxbexception-in-java-11?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=sc}"
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

//Esta clase puede dar errores porque no se podía 
//hacer extends de WebSecurityConfigureAdapter
public class MainSecurity {

    @Autowired
    UserDetailsImpl userDetailsServicesImpl;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    //@Bean// acá no va un Bean 
    private JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean//este ya entiendo un poco más porque está, se lo pasa a SprinBoot
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {//Importación 1 con versión nueva
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { //importación 2 
        // usuario para las credenciales coincidentes 
        // Use BCryptPasswordEncoder
        auth.userDetailsService( userDetailsServicesImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    AuthenticationManager authenticationManager(//Importación 3 y 4 con la versión nueva
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
/*
    
    @Bean
    protected AuthenticationManager authenticationManagerBean() throws Exception {
        return (Authentication authentication) -> {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        };
        //no puedo poner el authenticationManagerBean() al parecer ya no existe
        //está obsoleta 


        // aquí está después de pasar días buscando la solución el tema era una actualización de Sprin Security 
        //* https://backendstory-com.translate.goog/spring-security-how-to-replace-websecurityconfigureradapter/?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=sc  
        //*Actualización del webSecurityAdapter
    }*/
    //Aquí terminamos el back-end  ...yiijaa...

