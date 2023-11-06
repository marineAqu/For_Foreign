package com.project.fofo.repository;

import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WritingboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

/**
 * 파일명: WritingRepository
 * 작성자: 김도연
 **/
public interface WritingRepository extends JpaRepository<WritingboardEntity, Long> {

    List<WritingboardEntity> findByCreationDate(Date creationDate);

    /*
    @Query("SELECT w, m.userName FROM WritingboardEntity w INNER JOIN MemlistEntity m ON w.userNo = m.no WHERE w.no = :writingNo")
    Object[] findWritingWithUserName(@Param("writingNo") Long writingNo);
     */
}
