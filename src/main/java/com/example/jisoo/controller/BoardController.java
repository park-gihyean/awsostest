package com.example.jisoo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jisoo.mapper.BoardMapper;
import com.example.jisoo.model.Board;
import com.example.jisoo.model.User;

@Controller
@RequestMapping("board")
public class BoardController {


    @Autowired
    BoardMapper boardMapper;

    @GetMapping("boardList")
    public String boardList(HttpSession session, Model model) {
        ArrayList<Board> boardList = boardMapper.boardList();
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    @GetMapping("boardCreate")
    public String boardCreate(Board board) {


        return "board/boardCreate";
    }

    @PostMapping("boardCreate")
    public String boardCreate(HttpSession session, Board board) {
        User user = (User) session.getAttribute("user");
        board.setBoardWriter(user.getUserId());
        boardMapper.boardCreate(board);
        return "redirect:/board/boardList";
    }

    @GetMapping("boardDetail")
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo) {
        ArrayList<Board> boardList = boardMapper.boardList();
        for (Board board : boardList) {
            if (board.getBoardNo() == boardNo) {
                model.addAttribute("board", board);
            }
        }
        return "board/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Model model, @RequestParam("boardNo") int boardNo) {
        ArrayList<Board> boardList = boardMapper.boardList();
        for (Board board : boardList) {
            if (board.getBoardNo() == boardNo) {
                model.addAttribute("board", board);
            }
        }
        return "board/boardUpdate";
    }

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board board){
        boardMapper.boardUpdate(board);
        return "redirect:/board/boardList";
    }

    @GetMapping("boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo){
        boardMapper.boardRemove(boardNo);
        return "redirect:/board/boardList";
    }

   

}
