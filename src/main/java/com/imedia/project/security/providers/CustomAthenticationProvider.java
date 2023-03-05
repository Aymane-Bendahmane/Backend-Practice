package com.imedia.project.security.providers;

import com.imedia.project.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAthenticationProvider implements AuthenticationProvider {
    
    @Value("${application.secret-key}")
    private String key ;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        CustomAuthentication ca = (CustomAuthentication) authentication;
        String headerKey = ca.getKey();
        if (key.equals(headerKey)){
//            ca.setAuthenticated(true);
            return new CustomAuthentication(true,null);
        }
         throw new BadCredentialsException("bad credentials !!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
