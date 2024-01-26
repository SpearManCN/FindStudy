package findstudy.Config;

import findstudy.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService userDetailsService;
    //
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests() //권한 설정
                    .antMatchers("/static/**","/login_join","/loginForm","/home","/loginJoinProc","/login_find","/login_findProc","/login_changePw","/changePwProc").permitAll() //인증이 필요없이 들어올수 있는곳 설정
                    .anyRequest().authenticated() //위 제외하고는 모든곳에는 인증이 필요하다고 설정
                    .and()
                .formLogin() // 폼기반 로그인 활성화
                    .loginPage("/loginProc") // 사용자 지정 로그인 페이지 경로
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll() // 로그인 페이지에는 모두가 접속할수있도록 해줌
                    .and()
                .logout()
                    .logoutUrl("/logoutProc") // 사용자 지정 로그아웃 경로
                    .logoutSuccessUrl("/home") // 사용자 지정 로그아웃 성공 후 이동 경로
                    .permitAll()
                    .and();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}






















//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("cn1056")
//                .password("{noop}password") // {noop}은 NoOpPasswordEncoder를 사용하겠다는 의미
//                .roles("USER");
//    }
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception


//1. 로그인화면에서 입력하는 아이디/패스워드 값을 다루려면 어떡함?
//2. 세션에 저장이되면 해당 세션에서 현재 로그인사용자의 정보를 어케가져옴
