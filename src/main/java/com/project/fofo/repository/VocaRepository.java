package com.project.fofo.repository;

/**
 * 파일명: VocaRepository
 * 작성자: 김도연
 **/

import com.project.fofo.entity.VocalistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocaRepository extends JpaRepository<VocalistEntity, Long>  {

    List<VocalistEntity> findByUserNo(Long userNo);
}
