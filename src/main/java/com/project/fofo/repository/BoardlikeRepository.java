package com.project.fofo.repository;

import com.project.fofo.entity.BoardlikeEntity;
import com.project.fofo.entity.WritingboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 파일명: BoardlikeRepository
 * 작성자: 김도연
 **/

public interface BoardlikeRepository extends JpaRepository<BoardlikeEntity, Long> {

    void deleteByBoardNoAndUserNo(Long boardNo, Long userNo);
}
