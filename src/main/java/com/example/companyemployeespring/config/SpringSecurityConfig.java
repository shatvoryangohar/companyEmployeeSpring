package com.example.companyemployeespring.config;

import com.example.companyemployeespring.entity.Role;
import com.example.companyemployeespring.security.EmployeeDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeDetailsImpl employeeDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .and().logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.GET, "/company").permitAll()
                .antMatchers(HttpMethod.GET, "/employee").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers("/addEmployee").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers("/addCompany").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers("/deleteCompany/{id}").hasAnyAuthority(Role.ADMIN.name())
                .anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeDetails)
                .passwordEncoder(passwordEncoder());
    }

    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


