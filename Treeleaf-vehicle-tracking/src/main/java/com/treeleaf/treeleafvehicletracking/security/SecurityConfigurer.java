package com.treeleaf.treeleafvehicletracking.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;


@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint entryPoint;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//         String[] disallowedAntMatchers = {"/users/**","/locations/**","/camera/**","/vehicle/**","/vehiclemovement/**"};
         String[] allowedAntMatchers = {"/","/greetings","/login","/swagger-ui.html/**","/webjars/**","/v2/api-docs",
                 "/swagger-resources",
                 "/swagger-resources/configuration/ui",
                 "/swagger-resources/configuration/security"};

        http.authorizeRequests()
                .antMatchers(allowedAntMatchers)
                    .permitAll()
                .and()
                    .authorizeRequests().anyRequest().authenticated()
                .and()
                    .httpBasic().authenticationEntryPoint(entryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf()
                .disable();

        // cors and csrf disbaled
//        http.cors().and().csrf().disable();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        SecureRandom secureRandom = new SecureRandom("samip".getBytes());
        return new BCryptPasswordEncoder(10,secureRandom);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
