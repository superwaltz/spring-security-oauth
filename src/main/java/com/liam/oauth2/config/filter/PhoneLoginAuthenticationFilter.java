package com.liam.oauth2.config.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liangzy
 * @date: 2019/03/14 下午4:25
 * @desc:
 */
public class PhoneLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String SPRING_SECURITY_RESTFUL_MOBILE_KEY = "mobile";

    private boolean postOnly = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }

}
