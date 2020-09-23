package com.example.Board.controller;

import com.example.Board.dto.BoardDto;
import com.example.Board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    /*
     * showAllBoards : 전체 건수 조회
     * showOneDetail : 개별 건수 조회
     * insertForm : 생성
     * updateForm : 수정
     * deleteBoard : 삭제
     * */

    //Read

    @GetMapping("/")
    public String showAllBoards(Model model, @RequestParam(value="page",defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.findBoardService(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList",boardList);
        model.addAttribute("pageList",pageList);

        return "board/list"; //리턴값 제대로
    }

    @GetMapping("/post/{id}")
    public String showOneDetail(@PathVariable("id") long id,Model model){
        BoardDto boardDto = boardService.findBoardDetailService(id);
        System.out.println(boardDto.getId());
        model.addAttribute("boardDto",boardDto);
        return "board/detail";
    }

    //Create

    @GetMapping("/post")
    public String write() {
        return "board/insert";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        System.out.println(boardDto.getId());
        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    //Update

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.findBoardDetailService(id);
        model.addAttribute("boardDto",boardDto);
        return "board/update";
    }

    @PutMapping("/post/edit/{id}")
    public String updateForm(BoardDto boardDto){
        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    //Delete

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.deleteBoard(id);
        return "redirect:/";
    }

}
