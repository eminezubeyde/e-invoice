package com.example.einvoice.core.security.detail;

import com.example.einvoice.core.security.user.CustomUserDetails;
import com.example.einvoice.entity.User;
import com.example.einvoice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByIdentity(identity);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("identity not found.");
        }
        CustomUserDetails userDetails=new CustomUserDetails();
        userDetails.setUser(optionalUser.get());
        return userDetails;
    }
}
