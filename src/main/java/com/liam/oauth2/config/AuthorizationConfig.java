//package com.liam.oauth2.config;
//
//import com.liam.oauth2.service.MyUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//
//import javax.sql.DataSource;
//
///**
// * @author Liangzy
// * @date 2018/7/21 11:25
// * @Description: 授权服务配置
// */
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Bean
//    public TokenStore tokenStore(){
//        return new JwtTokenStore(jwtTokenEnhancer());
//    }
//
//    @Bean
//    protected JwtAccessTokenConverter jwtTokenEnhancer(){
//        KeyStoreKeyFactory keyStoreKeyFactory =
//                new KeyStoreKeyFactory(new ClassPathResource("lzy-jwt.jks"),"lzy123".toCharArray());
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("lzy-jwt"));
//        return converter;
//    }
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public JdbcClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//
//    @Autowired
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private MyUserDetailService userDetailsService;
//
//
//    /**
//     * 配置客户端信息（如：配置某种授权模式）
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService());
//    }
//
//
//    /**
//     * token管理（如：保存）
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        //jwt存储
//        endpoints
//                .tokenStore(tokenStore())
//                .tokenEnhancer(jwtTokenEnhancer())
//                .authenticationManager(authenticationManager)//只有配置该选项，密码认证才会开启
//                .userDetailsService(userDetailsService);
//
//    }
//
//
//    /**
//     * 配置获取Token的安全策略
//     * @param oauthServer
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
//        oauthServer.allowFormAuthenticationForClients();
//    }
//}
