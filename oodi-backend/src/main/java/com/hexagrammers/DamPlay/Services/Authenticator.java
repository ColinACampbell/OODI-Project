package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.PrincipalUserDetails;
import com.hexagrammers.DamPlay.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Authenticator implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public PrincipalUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadByEmail(username);
    }

    public PrincipalUserDetails loadByEmail(String email) throws UsernameNotFoundException {
        return new PrincipalUserDetails(userRepository.findByEmail(email));
    }
}
