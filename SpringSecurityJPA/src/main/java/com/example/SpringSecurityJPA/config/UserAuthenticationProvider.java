package com.example.SpringSecurityJPA.config;

import com.example.SpringSecurityJPA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserDetailsService userDetailsService;
    //the AuthorizationFilter constructs a Supplier that retrieves an Authentication from the SecurityContextHolder.
    //This method is called when spring runs filter chain and is a part of filterchain
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //initially authentication holds credentials
        //later fill it with principal
        String enteredUsername=authentication.getName();
        String enteredCredentials= authentication.getCredentials().toString();

        UserDetails actualUserDetails= userDetailsService.loadUserByUsername(enteredUsername);

       // if(authenticationsuccess)
        if(encoder.matches(enteredCredentials,actualUserDetails.getPassword())) {
            System.out.print("Password matches");
            System.out.println("Authority is "+ actualUserDetails.getAuthorities());
            //push userDetails to authentication
            //// Create an authentication token with the principal (userDetails) and null credentials (usually for username/password-based authentication)
            Authentication authPrincipal=new UsernamePasswordAuthenticationToken(actualUserDetails,null,actualUserDetails.getAuthorities());
            // Set the authentication object to the SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authPrincipal;
        }
        else
            throw new BadCredentialsException("Incorrect password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
