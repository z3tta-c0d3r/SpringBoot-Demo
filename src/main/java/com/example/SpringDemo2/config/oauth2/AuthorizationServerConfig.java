package com.example.SpringDemo2.config.oauth2;

import com.example.SpringDemo2.config.details.CrmUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private static String REALM="CRM_REALM";
    private static final int ONE_DAY = 60 * 60 * 24;
    private static final int THIRTY_DAYS = 60 * 60 * 24 * 30;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private CrmUserDetailsService crmUserDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;


    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter tokenConverter;


    /**
     * Converter token with Jwt
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("1234567890");
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
                .withClient("crmClient1")
                .secret("{noop}crmSuperSecret")
                .resourceIds("service")
                .authorizedGrantTypes("client_credentials","refresh_token")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "ROLE_ADMIN")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(300)
                .refreshTokenValiditySeconds(THIRTY_DAYS);
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