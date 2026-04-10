package backend.laakelista;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig  {
	
	// using lambdas 
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
      http
      .authorizeHttpRequests(authorize -> authorize
        	.requestMatchers("/css/**").permitAll() // Enable css when logged out
            .requestMatchers("/css/**").permitAll()// Login ja signup näkyvät kaikille
            .requestMatchers("/login", "/signup", "/saveuser").permitAll() 
            .requestMatchers("/potilas/poista/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
      )
      .formLogin(formlogin -> formlogin
          .loginPage("/login")
          .defaultSuccessUrl("/potilaat", true)
          .permitAll()
      )
      .logout(logout -> logout
          .permitAll()
      );
      return http.build();
    }
    
     @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}