package kr.co.mybatis.controller;

import kr.co.mybatis.domain.Board;
import kr.co.mybatis.service.BoardServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardServcie boardServcie;

    // 목록리턴
    @GetMapping
    public List<Board> getList() {
        return boardServcie.getList();
    }

    // 상세정보 리턴
    @GetMapping("/{boardSeq}")
    public Board get(@PathVariable int boardSeq) {
        return boardServcie.get(boardSeq);
    }

    // 등록/수정 처리
    @GetMapping("/save")
    public int save(Board parameter) {
        boardServcie.save(parameter);
        return parameter.getBoardSeq();
    }

    // 삭제처리
    @GetMapping("/delete/{boardSeq}")
    public boolean delete(@PathVariable int boardSeq) {
        Board board = boardServcie.get(boardSeq);
        if(board == null) {
            return false;
        }
        boardServcie.delete(boardSeq);
        return true;
    }
}
