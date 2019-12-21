package com.example.SpringDemo2.config.oauth2;

import com.example.SpringDemo2.config.details.CrmUserDetailsService;
import com.example.SpringDemo2.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    private final CrmUserDetailsService crmUserDetailsService;
    private final ClientDetailsService clientDetailsService;

    @Value("${admin.oauth2.token.username}")
    private String adminUsername;
    @Value("${admin.oauth2.token.password}")
    private String adminPassword;

    @Value("${user.oauth2.token.username}")
    private String userUserName;
    @Value("${user.oauth2.token.password}")
    private String userPassword;

    /**
     * Converter token with Jwt
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(Constants.SIGN_KEY);
        return converter;
    }

    /**
     * Store token jwt
     * @param tokenConverter JwtAccessTokenConverter
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter tokenConverter) {
        return new JwtTokenStore(tokenConverter());
    }

    /**
     * Services of get token
     * @param tokenStore  TokenStore
     * @param tokenConverter JwtAccessTokenConverter
     * @return DefaultTokenServices
     */
    @Bean
    DefaultTokenServices tokenServices(TokenStore tokenStore, JwtAccessTokenConverter tokenConverter) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setTokenEnhancer(tokenConverter);
        return tokenServices;
    }

    /**
     *  Handler approve new user
     * @param tokenStore TokenStore
     * @param clientDetailsService ClientDetailsService
     * @return TokenStoreUserApprovalHandler
     */
    @Bean
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore, ClientDetailsService clientDetailsService) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    /**
     * Store user approved
     * @param tokenStore TokenStore
     * @return ApprovalStore
     * @throws Exception
     */
    @Bean
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }
    // jwt

    /**
     * Configure an initial user for oauth authentication
     * @param clients ClientDetailsServiceConfigurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(adminUsername)
                .secret(adminPassword)
                .resourceIds(Constants.RESOURCE_ID)
                .authorizedGrantTypes("client_credentials","refresh_token")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "ROLE_ADMIN")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(Constants.ACCESS_SECONDS)
                .refreshTokenValiditySeconds(Constants.THIRTY_DAYS)
                .and()
                .withClient(userUserName)
                .secret(userPassword)
                .resourceIds(Constants.RESOURCE_ID)
                .authorizedGrantTypes("client_credentials","refresh_token")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "ROLE_USER")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(Constants.ACCESS_SECONDS)
                .refreshTokenValiditySeconds(Constants.THIRTY_DAYS);
    }

    /**
     * Configure authentication for a token services
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenServices(tokenServices);
    }

    /**
     * Permit token access is authenticated
     * @param oauthServer AuthorizationServerSecurityConfigurer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //oauthServer.realm(REALM);
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                //.passwordEncoder(this.passwordEncoder)
                .allowFormAuthenticationForClients();

    }
}