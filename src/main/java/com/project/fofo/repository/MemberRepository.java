package com.project.fofo.repository;

import com.project.fofo.entity.MemlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemlistEntity, Long> {
    MemlistEntity findByUid(String uid);
}
