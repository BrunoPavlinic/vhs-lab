package com.example.vhs.security;

import com.example.vhs.entity.MyUserDetails;
import com.example.vhs.entity.User;
import com.example.vhs.repository.UserRepository;
import com.example.vhs.service.VhsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    @Autowired
    public void setUserRepository(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user =  getUserRepository().findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found - " + userName));
        return user.map(MyUserDetails::new).get();
    }
}
