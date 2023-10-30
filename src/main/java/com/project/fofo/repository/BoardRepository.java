package com.project.fofo.repository;

/**
 * 파일명: BoardRepository
 * 작성자: 김현지
 **/

import com.project.fofo.entity.WordsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BoardRepository extends CrudRepository<WordsEntity, Long> {
    @Override
    ArrayList<WordsEntity> findAll();
}