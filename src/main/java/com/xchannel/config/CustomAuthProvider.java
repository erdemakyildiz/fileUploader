package com.xchannel.config;

import com.xchannel.entity.User;
import com.xchannel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Detay on 24.11.2017.
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        if(name==null || password==null){
            return null;
        }else{
            if(userService.authUser(name, password)){
                User user = userService.getUser(name);
                if (StringUtils.isEmpty(user.getRole())){
                    grantedAuths.add(new SimpleGrantedAuthority("USER"));
                }else{
                    grantedAuths.add(new SimpleGrantedAuthority(user.getRole()));
                }

                return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
            }else{
                return null;
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}