package com.project.fofo.repository;

/**
 * 파일명: QuizRepository
 * 작성자: 김도연
 **/

import com.project.fofo.entity.WordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<WordsEntity, Long> {

    List<WordsEntity> findByVocaNo(Long vocaNo);
}
