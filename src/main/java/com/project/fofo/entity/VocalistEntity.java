package com.project.fofo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Vocalist")
public class VocalistEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long no; //단어장 번호

    @Column
    private String bookTitle; //단어장 이름

    @Column
    private Long userNo; //유저 번호

}
