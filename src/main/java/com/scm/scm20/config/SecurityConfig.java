
package com.scm.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.scm.scm20.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // user create and login using java code with in memory service

    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user1 = User kk
    // .withDefaultPasswordEncoder()
    // .username("admin123")
    // .password("admin123")
    // .roles("ADMIN", "USER")
    // .build();

    // UserDetails user2 = User
    // .withDefaultPasswordEncoder()
    // .username("user123")
    // .password("password")
    // // .roles(null)
    // .build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
    // return inMemoryUserDetailsManager;

    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

//    @Autowired
//    private AuthFailureHandler authFailureHandler;

    // configuraiton of authentication provider for spring security
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail service ka object:
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration

        // urls configure kiya hai ki koun se public ranhenge aur koun se private rahenge

        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
//
//        // form default login
//        httpSecurity.formLogin(Customizer.withDefaults());
//        // agar hame kuch bhi change karna hua to hama yaha ayenge: form login se
//        // related
//        httpSecurity.formLogin(formLogin -> {
//

//            formLogin.loginPage("/login");
//            formLogin.loginProcessingUrl("/authenticate");
//            formLogin.successForwardUrl("/user/profile");
//            // formLogin.failureForwardUrl("/login?error=true");
//            // formLogin.defaultSuccessUrl("/home");
//            formLogin.usernameParameter("email");
//            formLogin.passwordParameter("password");
//
//            // formLogin.failureHandler(new AuthenticationFailureHandler() {
//
//            // @Override
//            // public void onAuthenticationFailure(HttpServletRequest request,
//            // HttpServletResponse response,
//            // AuthenticationException exception) throws IOException, ServletException {
//            // // TODO Auto-generated method stub
//            // throw new UnsupportedOperationException("Unimplemented method
//            // 'onAuthenticationFailure'");
//            // }
//
//            // });
//
//            // formLogin.successHandler(new AuthenticationSuccessHandler() {
//
//            // @Override
//            // public void onAuthenticationSuccess(HttpServletRequest request,
//            // HttpServletResponse response,
//            // Authentication authentication) throws IOException, ServletException {
//            // // TODO Auto-generated method stub
//            // throw new UnsupportedOperationException("Unimplemented method
//            // 'onAuthenticationSuccess'");
//            // }
//
//            // });
//            formLogin.failureHandler(authFailtureHandler);
//
//        });


//      for using logout url we have to disable csrf which is by default enable when using EnableWebSecurity
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

//        // oauth configurations
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });
//
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });
//
        return httpSecurity.build();

//
//    }

    }

}
