package com.busbooking.busbooking.securityconfig;

import com.busbooking.busbooking.entity.User;
import com.busbooking.busbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user!=null){
            String []role=user.getRole().split(" ");
            List<SimpleGrantedAuthority>roles=new ArrayList<>();
            for(String r: role){
                roles.add(new SimpleGrantedAuthority(r));
            }
            UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), roles);
            return userDetails;
        }
        return null;
    }
}
