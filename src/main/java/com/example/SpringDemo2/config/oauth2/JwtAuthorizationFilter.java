package com.example.SpringDemo2.config.oauth2;

import com.example.SpringDemo2.config.details.CrmUserDetailsService;
import com.example.SpringDemo2.constants.Constants;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final CrmUserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZATION_KEY);

        if (StringUtils.isEmpty(authorizationHeader) || !authorizationHeader
                .startsWith(Constants.TOKEN_BEARER_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        final String token = authorizationHeader.replace(Constants.TOKEN_BEARER_PREFIX + " ", "");

        String userName = TokenProvider.getUserName(token);
        UserDetails user = userService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, user);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
