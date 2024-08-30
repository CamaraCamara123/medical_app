package ma.medical_app.medical_app.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class JwtSecurityConfiguration {

        @Autowired
        private JwtAuthFilter jwtAuthFilter;

        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Autowired
        private LogoutHandler logoutHandler;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf((csrf) -> csrf.disable())
                                .authorizeHttpRequests(
                                                authz -> authz
                                                                .requestMatchers("/api/v1/auth/**")
                                                                .permitAll()
                                                                .anyRequest()
                                                                .authenticated())
                                .exceptionHandling(
                                                exception -> exception
                                                                .authenticationEntryPoint(authenticationEntryPoint()))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> logout
                                                .logoutUrl("/api/v1/auth/logout")
                                                .addLogoutHandler(logoutHandler).logoutSuccessHandler(
                                                                (request, response,
                                                                                authentication) -> SecurityContextHolder
                                                                                                .clearContext()));
                return http.build();
        }

        @Bean
        AuthenticationEntryPoint authenticationEntryPoint() {
                return new CustomAuthenticationEntryPoint();
        }

}
