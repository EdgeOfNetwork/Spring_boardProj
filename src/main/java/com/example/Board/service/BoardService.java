package com.example.Board.service;


import com.example.Board.dto.BoardDto;
import com.example.Board.model.Board;
import com.example.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardDto> findBoardService(){
        List<Board> boardList = boardRepository.findAll(); //List로 받는데
        System.out.println(boardList);
        List<BoardDto> testList = new ArrayList<>();
        for(int i = 0 ; i < boardList.size() ; i++){ testList.add(convertToDto(boardList.get(i))); }
        return testList;
    }

    public Optional<BoardDto> findBoardDetailService(long id){
        Optional<Board> board = boardRepository.findById(id);
        return board.map(this::convertToDto);
        //
    }







    public Board convertToBoard(BoardDto boardDto){
        Board board = new Board();
        board.setId(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        return board;
    }

    public BoardDto convertToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        return boardDto;
    }
}

