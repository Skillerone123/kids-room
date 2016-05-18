package ua.softserveinc.tc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@ComponentScan(basePackages = "ua.softserveinc.tc.service")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers(HttpMethod.GET, "/registration").anonymous()
                .antMatchers(HttpMethod.GET, "/mykids").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/registerkid").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/registerkid").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/editmykid").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/editmykid").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/mybookings").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/report").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/allBookings").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/allkidslist").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/listChildren").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/allBookingsPerParent").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/adm-add-city").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-add-city").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/adm-add-manager").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-add-manager").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/adm-add-room").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-add-room").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/adm-edit-city").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-edit-city").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/adm-edit-manager").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-edit-manager").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/adm-edit-room").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-edit-room").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/adm-update-manager").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-update-manager").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/adm-update-room").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/adm-update-room").hasRole("ADMINISTRATOR")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()


            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .and()

            .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout");
        http.rememberMe().
                key("rem-me-key").
                rememberMeParameter("remember-me").
                rememberMeCookieName("my-remember-me").
                tokenValiditySeconds(286400);
    }

    @Bean
    public PasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
