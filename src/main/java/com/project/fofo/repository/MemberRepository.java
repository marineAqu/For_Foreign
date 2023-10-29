package com.project.fofo.repository;

/**
 * 파일명: MemberRepository
 * 작성자: 김민정
 **/

import com.project.fofo.entity.MemlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemlistEntity, Long> {
    MemlistEntity findByUid(String uid);
    MemlistEntity findByUserName(String name);
}
