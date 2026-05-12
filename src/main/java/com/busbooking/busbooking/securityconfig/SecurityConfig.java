package com.busbooking.busbooking.securityconfig;


import com.busbooking.busbooking.filters.RoleGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;
@Configuration
public class SecurityConfig {

    @Autowired
    RoleGenerator roleGenerator;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests((request)->{



            request.requestMatchers("/login","/signup").permitAll();
            request.requestMatchers("/error").permitAll();

            request.requestMatchers("/buses","/book-seat","/viewTrips/**","/buses2","/add-trip","/add-trip/").hasAnyRole("ADMIN", "USER");
            request.requestMatchers("/add-bus","/remove-bus","/remove-bus/**","/xyz","/pqr").hasRole("ADMIN");
            request.anyRequest().authenticated();
        });
        http.cors(cors -> cors.configurationSource(
                request -> {
                    CorsConfiguration configuration = new CorsConfiguration();

                    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
                    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setAllowCredentials(true);

                    // 🔥 THIS IS WHAT YOU ARE MISSING
                    configuration.setExposedHeaders(List.of("Authorization"));

                    return configuration;
                }
        ));
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
