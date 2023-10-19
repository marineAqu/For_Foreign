package com.project.fofo.repository;

import com.project.fofo.entity.VocalistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocaRepository extends JpaRepository<VocalistEntity, Long>  {

    List<VocalistEntity> findByUserNo(Long userNo);
}
