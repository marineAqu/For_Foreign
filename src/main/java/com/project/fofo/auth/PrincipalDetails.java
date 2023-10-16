package com.project.fofo.auth;

import com.project.fofo.entity.MemlistEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {
    private final MemlistEntity memlistEntity;
    private Map<String, Object> attributes;

    // 일반 로그인
    public PrincipalDetails(MemlistEntity memberEntity) {
        this.memlistEntity = memberEntity;
    }

    // OAuth 로그인
    public PrincipalDetails(MemlistEntity memberEntity, Map<String, Object> attributes) {
        this.memlistEntity = memberEntity;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    //해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memlistEntity.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return memlistEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return memlistEntity.getUid();
    }

    // 계정 만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 기간 오래 지났는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return memlistEntity.getUserName();
    }
}
