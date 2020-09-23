package com.example.Board.dto;

import com.example.Board.model.Board;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//@Data //이따 잘 돌아가면 실험해보자

@Data
@Getter
@Setter
@Builder
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board convertToEntity(){
        Board board = new Board();
        board.setId(getId());
        board.setTitle(getTitle());
        board.setContent(getContent());
        return board;
    }



//    public Board toEntity(){
//
//        Board board = Board.builder()
//                .id(id)
//                .title(title)
//                .content(content)
//                .build();
//        return board;
//    }

    public BoardDto(Long id, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
