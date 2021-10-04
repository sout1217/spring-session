package com.example.springsession.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Profile("local")
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{bcryte} 1234").roles("USER").and()
//                .withUser("admin").password("{bcryte} 1234").roles("ADMIN")
//        ;


//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, true "+
//                        "from member where username=?")
//                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from member where username=?");


        final String usernameQuery = "SELECT username, password, enable FROM member WHERE username=?";
        final String authQuery = "SELECT a.username, b.role_name as authority FROM member a , roles b WHERE a.id = b.id AND a.username = ?";
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usernameQuery)
                .authoritiesByUsernameQuery(authQuery)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/index",
                            "/invalid",
                            "/expired",
                            "/sign-in/**",
                            "/sign-up/**",
                            "/success/**",
                            "/failure/**",
                            "/favicon.ico",
                            "/h2-console",
                            "/h2-console/**"
                    ).permitAll()
                    .anyRequest().authenticated()
        ;

        http
                .formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/sign-in")
                .defaultSuccessUrl("/success")
                .failureUrl("/failure")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
        ;

        /** 만료된 세션 */
        http
                .sessionManagement()
                    .maximumSessions(1)
                        .maxSessionsPreventsLogin(false) // true 는 로그인 불가능하게 하기
                            .expiredUrl("/expired")
        ;

        /** 유효하지 않은 세션 */
//        http
//                .sessionManagement()
//                .invalidSessionUrl("/invalid")
//                    .maximumSessions(1)
//                        .maxSessionsPreventsLogin(false) // true 는 로그인 불가능하게 하기
//                            .expiredUrl("/expired")
//
//        ;

    }
}
