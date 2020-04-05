package com.demo.auth;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.common.JwtAuthConfig;
import com.demo.common.JwtCredentialAuthFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	 @Autowired JwtAuthConfig config;

	    @Bean
	    public JwtAuthConfig jwtConfig() {
	        return new JwtAuthConfig();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        auth.inMemoryAuthentication()
	                .withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER").and()
	                .withUser("demo").password(encoder.encode("demo")).roles("USER");
	    }

	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity
	                .csrf().disable()
	                .logout().disable()
	                .formLogin().disable()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                    .anonymous()
	                .and()
	                    .exceptionHandling().authenticationEntryPoint(
	                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	                .and()
	                    .addFilterAfter(new JwtCredentialAuthFilter(config, authenticationManager()),
	                            UsernamePasswordAuthenticationFilter.class)
	                .authorizeRequests()
	                    .antMatchers(config.getUrl()).permitAll()
	                    .anyRequest().authenticated();
	    }
}
