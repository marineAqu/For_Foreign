package com.project.fofo.entity;
/**
 * 파일명: TodaytopicEntity
 * 작성자: 김도연
 **/


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Setter
@Getter
@Table(name = "Todaytopic")
public class TodaytopicEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long no; //번호

    @Column
    private String topic; //주제
}
