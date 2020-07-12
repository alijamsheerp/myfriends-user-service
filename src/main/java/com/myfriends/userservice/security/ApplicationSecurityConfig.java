package com.myfriends.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder; //Not needed in real project - Added here to encode raw password for testing

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/","index").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }

    //Below is how we define users to Authenitcate
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        //return super.userDetailsService();
        UserDetails jamsheeUserDetails = User.builder()
                .username("jamshee")
                //.password("gectcr")
                .password(passwordEncoder.encode("gectcr")) // Password needs to be encrypted - Spring will try to decrypt during authentication
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(jamsheeUserDetails);
    }
}
