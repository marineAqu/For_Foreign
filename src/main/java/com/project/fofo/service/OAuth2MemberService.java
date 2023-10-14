package com.project.fofo.service;

import com.project.fofo.auth.PrincipalDetails;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.repository.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2MemberService extends DefaultOAuth2UserService {
    private PasswordEncoder passwordEncoder;
    private MemberRepository memberRepository;

    public OAuth2MemberService(@Lazy MemberRepository memberRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String userName = oAuth2User.getAttribute("name");
        String password = passwordEncoder.encode("겟인데어");
        String email = oAuth2User.getAttribute("email"); //minjung@gmail.com
        String role = "ROLE_USER";
        Integer userPoint = 0;
        Integer ranking = 0;

        MemlistEntity memlistEntity = memberRepository.findByUid(email);

        if(memlistEntity == null) {
            memlistEntity = MemlistEntity.builder()
                    .userName(userName)
                    .uid(email)
                    .password(password)
                    .role(role)
                    .userPoint(userPoint)
                    .ranking(ranking)
                    .build();
            memberRepository.save(memlistEntity);
        }
        return new PrincipalDetails(memlistEntity, oAuth2User.getAttributes());
    }
}
