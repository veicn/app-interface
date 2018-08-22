//package org.activiti.conf;
//
//import org.activiti.rest.security.BasicAuthenticationProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity  //SpringBoot会根据依赖的jar包而自动配置相应的功能。Activiti Modeler中却引用了Spring Security的jar，所以访问任意地址都要验证。 不需要身份验证，加入此注解
//@EnableWebMvcSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return new BasicAuthenticationProvider();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authenticationProvider(authenticationProvider())
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .csrf().disable()
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic();
//    }
//}