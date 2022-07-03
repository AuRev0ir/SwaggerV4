package ru.spring.app.configApp;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;


// login  password
// danil  danil    (user)
// igor   igor     (admin)
// anton  anton    (admin)


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //Авторизация
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/swagger-ui/*")
                .hasAnyAuthority( "ADMIN", "PERSON")
                .antMatchers(HttpMethod.POST, "/api/v2/images/uploadImage")
                .hasAnyAuthority("ADMIN", "PERSON")
                .antMatchers(HttpMethod.GET, "/api/v2/images")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "api/v2/persons/newPerson")
                .hasAnyAuthority("ADMIN", "PERSON")
                .antMatchers(HttpMethod.GET, "/api/v2/persons/{personId}")
                .hasAnyAuthority("ADMIN", "PERSON")
                .antMatchers(HttpMethod.PATCH, "/api/v2/persons/{personId}")
                .hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v2/persons")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v2/persons/findByStatus")
                .hasAuthority("ADMIN")
                .and().formLogin();
        return http.build();
    }

    //Аутентификация
    @Bean
    public InMemoryUserDetailsManager configureAuthentication (){

        List<UserDetails> userDetails = new ArrayList<>();

        List<GrantedAuthority> roleUser = new ArrayList<>();
        List<GrantedAuthority> roleAdmin = new ArrayList<>();


        roleUser.add(new SimpleGrantedAuthority("PERSON"));
        roleAdmin.add(new SimpleGrantedAuthority("ADMIN"));

        userDetails.add(new User("danil",
                "$2a$10$po4G0eyBE.uzl7zFDi44.OmdSB0VF5AcRJHXBL2UOt6QDHKREzGS2",
                roleUser));

        userDetails.add(new User("anton",
                "$2a$10$GtoSk9dOvFWKzfMErxo20e3czq8n3vtIBW7egFC7FDlQHIA0pqINq",
                roleAdmin));

        userDetails.add(new User("igor",
                "$2a$10$Yp58GXHOSewn/NcEb4zTdOFVsajX4w.UaV2gnFjIrFqDMByn3Y3Wm",
                roleAdmin));

        return new InMemoryUserDetailsManager(userDetails);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }






}
