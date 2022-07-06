package com.dailynews.Newsdaily.config;
import com.dailynews.Newsdaily.security.JwtConfigurer;
import com.dailynews.Newsdaily.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(@Lazy UserDetailsService userDetailsService, UserDetailsService userDetailsService1, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService1;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//               .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/testUser").permitAll()
                .antMatchers("/api/testPost").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/employees").hasRole("ADMIN")
                .antMatchers("/api/employees/*").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/students/all").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
/*
   @Bean
   PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
   }

*/

}
