package me.gladysz.services.cas.infrastrucure.authentication;

import me.gladysz.services.cas.authentication.domain.AuthFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthTokenProperties authTokenProperties;
    private final AuthFacade authFacade;

    SecurityConfig(UserDetailsService userDetailsService,
                   AuthTokenProperties authTokenProperties,
                   AuthFacade authFacade
    ) {
        this.userDetailsService = userDetailsService;
        this.authTokenProperties = authTokenProperties;
        this.authFacade = authFacade;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.cors().and().csrf().disable();
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()
//                .antMatchers("/**/hello/**").permitAll()
//                .antMatchers("/**/register/**").permitAll()
//                .antMatchers("/**" + AuthEndpointPath.AUTH_PATH + "/**").permitAll();
////                .anyRequest().authenticated();
//
//        http.headers()
//                .cacheControl();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/**/hello/**").permitAll()
                .antMatchers("/**/register/**").permitAll()
                .antMatchers("/**" + AuthEndpointPath.AUTH_PATH + "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new AuthTokenFilter(authTokenProperties, authFacade),
                        UsernamePasswordAuthenticationFilter.class);


        http.headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("**");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
