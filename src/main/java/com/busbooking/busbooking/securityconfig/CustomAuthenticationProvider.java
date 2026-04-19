package com.busbooking.busbooking.securityconfig;

import com.busbooking.busbooking.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getPrincipal().toString();
        String rawPassword=authentication.getCredentials().toString();
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (encoder.matches(rawPassword, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }

            throw new BadCredentialsException("Invalid username or password");

        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
