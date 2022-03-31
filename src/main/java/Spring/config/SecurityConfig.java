package Spring.config;

import Spring.service.Oauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 외부 라이브러리 설정 값 변경시
@EnableWebSecurity // 시큐리티 정의시 : extends WebSecurityConfigurerAdapter(기본적인 Web 보안을 활성화 하겠다는 어노테이션)
@RequiredArgsConstructor // final 필드 정의
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Oauth2Service oauth2Service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests() // 클라이언트가 Http 요청 값에 해당하는 보안 설정
                //.antMatchers("/admin/**").hasRole("ADMIN") // 특정 URL 이후의 Role 접속 권한 부여
                .antMatchers("/member/info").hasRole("MEMBER")
                .antMatchers("/**").permitAll() // 이후 모든 URL접근 가능

        .and()
                .logout() // 로그아웃 관련 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("logout")) // 로그아웃 URL 설정
                .logoutSuccessUrl("/") // 로그아웃 성공시
                .invalidateHttpSession(true) // 세션 초기화

        .and() // 연결 메소드
                .csrf() // 사이트 간 요청 위조, html 입력에 관련된 페이지는 무시
                .ignoringAntMatchers("/h2-console/**") // 사이트 간 요청 위조 방지를 제거해서 console 사용
                .ignoringAntMatchers("/login")
                .ignoringAntMatchers("/info")
                .ignoringAntMatchers("/updateinfo")
                .ignoringAntMatchers("/updatecomplete")
                .ignoringAntMatchers("/signup")
                .ignoringAntMatchers("/replywrite")
                .ignoringAntMatchers("/cartadd")
                .ignoringAntMatchers("/boardmodify")
                .ignoringAntMatchers("/boardwrite")

        .and()
               .oauth2Login() // Oauth2 로그인 설정
               .loginPage("/login")
               .userInfoEndpoint()
               .userService(oauth2Service);


        http.headers().frameOptions().disable(); // h2 접근하기 위해 frame 제거

    }
}
