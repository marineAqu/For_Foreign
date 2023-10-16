package com.project.fofo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Todaygram")
public class TodaygramEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long no; //번호

    @Column
    private String gramContent; //문법 내용

    @Column
    private String gramEx; //예문

}
