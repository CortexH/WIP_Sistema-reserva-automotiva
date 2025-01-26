package com.example.SistemaReservaAutomotiva.configurations;

public class EndpointWhitelist {

    public static final String[] NoAuthorizationEndpoints = {
            "/**",
            "/user/register", "/user/login", "/product/find", "/vehicle/top",
            "/h2-console/**", "/favicon.ico"
    };

    public static final String[] ForClientRequests = {
            "/reservation/**", "/alert/get", "/user/logout",
            "/user/changepassword", "/purchase/buy"
    };

    public static final String[] SwaggerEndpoints = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
    };

    public static final String[] ForEmployeeEndpoints = {
        "/vehicle/register", "/alert/getall", "/product/register"
    };

    public static final String[] AdminRequests = {
            "/test/**", "/user/getall", "/vehicle/get"

    };

}
