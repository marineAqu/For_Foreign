package com.project.fofo.repository;

/**
 * 파일명: MemberRepository
 * 작성자: 김민정
 **/

import com.project.fofo.entity.MemlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    /**
     * 함수: findUserRank
     * 작성자: 김도연
     * 설명: 사용자보다 학습 포인트가 많은 사람을 찾음(사용자의 순위 체크)
     **/
    @Query("SELECT COUNT(m) + 1 FROM MemlistEntity m WHERE m.userPoint > (SELECT m2.userPoint FROM MemlistEntity m2 WHERE m2.no = :userId)")
    Integer findUserRank(@Param("userId") Integer userId);
}
