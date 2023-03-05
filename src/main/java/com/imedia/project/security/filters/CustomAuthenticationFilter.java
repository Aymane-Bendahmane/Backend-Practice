package com.imedia.project.security.filters;

import com.imedia.project.security.authentication.CustomAuthentication;
import com.imedia.project.security.managers.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@AllArgsConstructor
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    
    
    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        //1 .create an authentication object which is not yet authenticated
        //2 .delegate the authentication object to the auth manager
        //3 .get back the auth objet from the manager
        //4 .if the object is authenticated => send request to the next filter in the chain

        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication customAuthentication = new CustomAuthentication(false,key);

        Authentication a = customAuthenticationManager.authenticate(customAuthentication);
        if(a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }
    }
}
