package com.example.SpringSecurityJPA.service;

import com.example.SpringSecurityJPA.model.MyUserDetails;
import com.example.SpringSecurityJPA.model.User;
import com.example.SpringSecurityJPA.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//actually interacts with repo to get userdata
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDetailsRepo userRepo;

    //this UserDetails which is returned here is a standard spring object
    //so convert custom user details from repo to standard spring object
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user=userRepo.findByUserName(username);
       user.orElseThrow(()->new UsernameNotFoundException("Not found" + username));
       //conversion
       return user.map(MyUserDetails::new).get();
    }

    public User registerUser(User user){

        return userRepo.save(user);
    }
}
