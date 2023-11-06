package com.project.fofo.auth;

import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        MemlistEntity memlistEntity = memberRepository.findByUid(uid);

        if (memlistEntity != null) {
            return new PrincipalDetails(memlistEntity);
        }
        return null;
    }
}
