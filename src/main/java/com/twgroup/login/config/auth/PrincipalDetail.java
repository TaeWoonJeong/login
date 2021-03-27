package com.twgroup.login.config.auth;

import com.twgroup.login.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails {
    private User user; //PrincipalDetail이 User객체를 품고있다. 이것을보고 컴포지션이라고 한다.

    public PrincipalDetail(User user) {
        this.user=user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정이 가지고 있는 권한 목록을 리턴해준다. (여러개 있을 수 있는데 나는 1개만 씀)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors=new ArrayList<>();
        //제네릭으로 GrantedAuthority를 받았는데 거기에 getAuthority밖에 없어서 람다식 표현 가능
//        collectors.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "ROLE_"+user.getRole();//규칙으로 앞에 "ROLE_"를 붙여야됨
//            }
//        });

        collectors.add(()->{return "ROLE_"+user.getRole();});
        return collectors;
    }
}
