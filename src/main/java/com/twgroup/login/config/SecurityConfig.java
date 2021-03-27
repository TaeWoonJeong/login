package com.twgroup.login.config;

import com.twgroup.login.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 이게 있어야 SecurityConfig.java에서
//시큐리티 필터를 걸어서 어떤거는 들어올수 있고 어떤거는 안되게 막을 수 있다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크 하겠다.
//위의 3개 어노테이션이 거의 세트처럼 따라옴
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인 해주는데 password를 가로채기를 한뒤
    // 해당 password가 어떻게 해쉬가 되어서 회원가입이 됬는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// csrf 토큰 비활성화(테스트시 걸어두면 좋음) 시큐리티 사용해서 보안이 빡세짐
                .authorizeRequests() //인증 요청이들어오면
                .antMatchers("/index","/auth/**","/js/**") // 만약 /auth/**의 주소라면
                .permitAll() //인증없이 들어올 수 있다.
                .anyRequest() //그렇지 않은 다른 모든 요청에는
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/login") //인증이 필요하다.
                .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당주소로 요청오는 로그인 가로채서 대신 로그인함
                .defaultSuccessUrl("/index");
    }
}
