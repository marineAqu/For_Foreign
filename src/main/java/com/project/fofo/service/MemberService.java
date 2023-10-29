package com.project.fofo.service;

/**
 * 파일명: MemberService
 * 작성자: 김민정
 **/

import com.project.fofo.DTO.MemberDTO;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(MemberDTO memberDTO) {
        MemlistEntity memlistEntity = MemlistEntity.createMember(memberDTO, passwordEncoder);
        validateDuplicateMember(memlistEntity);
        memberRepository.save(memlistEntity);
    }

    public void validateDuplicateMember(MemlistEntity memberEntity) {
        MemlistEntity findMember = memberRepository.findByUid(memberEntity.getUid());

        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public MemlistEntity findByMember(String uid) {
        MemlistEntity memlistEntity = memberRepository.findByUid(uid);
        return memlistEntity;
    }
}
