package com.example.board.controller;

import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/board/write.do")
    public String openBoardWrite(Model model) {
        String title = "제목";
        String content = "내용";
        String writer = "홍길동";

        model.addAttribute("t", title);
        model.addAttribute("c", content);
        model.addAttribute("w", writer);

        return "board/write";
    }
}
