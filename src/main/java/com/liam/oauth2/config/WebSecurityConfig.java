//package com.liam.oauth2.config;
//
//import com.liam.oauth2.config.filter.PhoneLoginAuthenticationFilter;
//import com.liam.oauth2.service.MyUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//开启在方法上的保护功能
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private MyUserDetailService userServiceDetail;
//
//
//
//    //请求安全验证
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .addFilterBefore(getPhoneLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                // 配置登陆页/login并允许访问
//                .formLogin().loginPage("/login").permitAll()
//                // 登出页
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/backReferer")
//                // 其余所有请求全部需要鉴权认证
//                .and().authorizeRequests().anyRequest().authenticated()
//                // 由于使用的是JWT，我们这里不需要csrf
//                .and().csrf().disable();
//    }
//
//
//    //验证用户信息源和密码加密策略
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userServiceDetail).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    protected PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public PhoneLoginAuthenticationFilter getPhoneLoginAuthenticationFilter(){
//        PhoneLoginAuthenticationFilter filter = new PhoneLoginAuthenticationFilter();
//        try {
//            filter.setAuthenticationManager(authenticationManagerBean());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        filter.setAuthenticationSuccessHandler(new MyLoginAuthSuccessHandler());
////        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
//        return filter;
//    }
//}
