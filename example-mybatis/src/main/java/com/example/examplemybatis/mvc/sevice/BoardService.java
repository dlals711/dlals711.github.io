package com.example.examplemybatis.mvc.sevice;

import com.example.examplemybatis.mvc.domain.Board;
import com.example.examplemybatis.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시판 서비스
 * @author 이영민
 */
@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;

    /**
     * 목록 리턴
     */
    public List<Board> getList() {
        return repository.getList();
    }

    /**
     * 상세정보 리턴
     */
    public Board get(int boardSeq) {
        return repository.get(boardSeq);
    }

    /**
     * 등록 처리
     */
    public void save(Board parameter) {
        Board board = repository.get(parameter.getBoardSeq());
        if(board == null) {
            repository.save(parameter);
        } else {
            repository.update(parameter);
        }
    }

    /**
     * 수정 처리
     */
    public void update(Board board) {
        repository.update(board);
    }

    /**
     * 삭제 처리
     */
    public void delete(int boardSeq) {
        repository.delete(boardSeq);
    }
}
