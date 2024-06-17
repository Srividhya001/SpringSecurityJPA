package com.example.SpringSecurityJPA.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private  String password;
    private  String userName;

    private boolean active;
    private List<GrantedAuthority> authorities;

    //populate userdetails fom my repo
    public MyUserDetails(User u){
        this.userName=u.getUserName();
        this.password=u.getPassword();
        this.active=u.isActive();
        this.authorities= Arrays.stream
                        (u.getRoles().split(","))
                        .map(role->new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
