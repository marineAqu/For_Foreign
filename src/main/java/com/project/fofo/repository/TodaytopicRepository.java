package com.project.fofo.repository;

import com.project.fofo.entity.TodaytopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 파일명: TodaytopicRepository
 * 작성자: 김도연
 **/

public interface TodaytopicRepository extends JpaRepository<TodaytopicEntity, Long> {

    TodaytopicEntity findByNo(Long no);

}
