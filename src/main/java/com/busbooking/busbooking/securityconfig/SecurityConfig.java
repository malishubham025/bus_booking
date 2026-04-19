package com.busbooking.busbooking.securityconfig;


import com.busbooking.busbooking.filters.RoleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    RoleGenerator roleGenerator;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests((request)->{



            request.requestMatchers("/login","/signup").permitAll();
            request.requestMatchers("/add-bus","/remove-bus").hasRole("ADMIN");
            request.anyRequest().authenticated();
        });
        http.addFilterAfter(roleGenerator, UsernamePasswordAuthenticationFilter.class);
        http.csrf(csrf->csrf.disable());
        http.httpBasic(basic->basic.disable());
        http.formLogin(form->form.disable());
        return http.build();

    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


}
