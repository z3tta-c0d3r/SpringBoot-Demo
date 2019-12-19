package com.example.SpringDemo2.config.oauth2;

import com.example.SpringDemo2.config.details.CrmUserDetailsService;
import com.example.SpringDemo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    public static final String REQUEST_TOKEN_URL = "/login";

    public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

    public static final String SIGNING_KEY = "KEY_1234";

    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 28800;

    public static final String ISSUER_TOKEN = "ISSUER";

    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

    public static final String TOKEN_BEARER_PREFIX = "Bearer";

    @Autowired
    private CrmUserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader(HEADER_AUTHORIZATION_KEY);

        if (StringUtils.isEmpty(authorizationHeader) || !authorizationHeader
                .startsWith(TOKEN_BEARER_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        final String token = authorizationHeader.replace(TOKEN_BEARER_PREFIX + " ", "");

        String userName = TokenProvider.getUserName(token);
        UserDetails user = userService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, user);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
