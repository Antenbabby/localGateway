/*
package com.antennababy.localgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize-> {
                    try {
                        authorize
                                // 放行登录接口
                                .requestMatchers("/api/auth/login").permitAll()
                                // 放行资源目录
                                .requestMatchers("/static/**", "/resources/**").permitAll()
                                // 其余的都需要权限校验
                                .anyRequest().authenticated()
                                // 防跨站请求伪造
                                .and().csrf(csrf -> csrf.disable());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        ).build();
    }
}
*/
