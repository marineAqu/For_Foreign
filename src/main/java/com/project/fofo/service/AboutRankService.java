package com.project.fofo.service;

/**
 * 파일명: AboutRankService
 * 작성자: 김도연
 **/

import com.project.fofo.DTO.MemberDTO;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AboutRankService {
    private final MemberRepository memberRepository;

    public List<MemberDTO> getRankingList() {

        List<MemlistEntity> memlistEntityList = memberRepository.findByUserPointGreaterThanOrderByUserPointDesc(0);

        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemlistEntity memlistEntity : memlistEntityList) memberDTOList.add(MemberDTO.toMemberDTO(memlistEntity));

        return memberDTOList;
    }
}
