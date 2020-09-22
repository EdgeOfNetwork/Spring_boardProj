package com.example.Board.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data //getter setter 생성해주는 lombok내부 어노테이션
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;
    private String title;
    private String content;


}
