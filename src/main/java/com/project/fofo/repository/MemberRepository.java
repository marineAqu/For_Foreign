package com.project.fofo.repository;

/**
 * 파일명: MemberRepository
 * 작성자: 김민정
 **/

import com.project.fofo.entity.MemlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemlistEntity, Long> {
    MemlistEntity findByUid(String uid);
    MemlistEntity findByUserName(String name);

    /**
     * 함수: findByUserPointGreaterThanOrderByUserPointDesc
     * 작성자: 김도연
     * 설명: 학습포인트가 0이 아닌 모든 것을 select하고 내림차 정렬
     **/
    List<MemlistEntity> findByUserPointGreaterThanOrderByUserPointDesc(Integer userPoint);
}
