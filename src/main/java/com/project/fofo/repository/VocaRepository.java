package com.project.fofo.repository;

/**
 * 파일명: VocaRepository
 * 작성자: 김도연
 **/

import com.project.fofo.entity.VocalistEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface VocaRepository extends JpaRepository<VocalistEntity, Long>  {

    List<VocalistEntity> findByUserNo(Long userNo);


    //11/23 김현지 추가(단어장 제목)
    Optional<VocalistEntity> findByNo(Long no);
}


