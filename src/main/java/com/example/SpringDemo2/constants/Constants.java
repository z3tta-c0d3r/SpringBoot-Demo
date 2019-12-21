package com.example.SpringDemo2.constants;

import lombok.Data;

@Data
public class Constants {

    //Details
    public static final String ROLE = "ROLE_";
    
    //oauth2
    public static final String SIGN_KEY = "1234567890";
    public static final String REALM="CRM_REALM";
    public static final int ONE_DAY = 60 * 60 * 24;
    public static final int THIRTY_DAYS = 60 * 60 * 24 * 30;

    //JwtAuthorizationFilter
    public static final String REQUEST_TOKEN_URL = "/login";
    public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";
    public static final String SIGNING_KEY = "KEY_1234";
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 28800;
    public static final String ISSUER_TOKEN = "ISSUER";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer";

    //ResourceServerConfig
    public static final String RESOURCE_ID = "service";
    public static final int ACCESS_SECONDS = 300;
}
