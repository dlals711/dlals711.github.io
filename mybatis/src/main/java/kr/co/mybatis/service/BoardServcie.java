package kr.co.mybatis.service;

import kr.co.mybatis.domain.Board;
import kr.co.mybatis.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServcie {
    @Autowired
    private BoardRepository repository;

    // 목록리턴
    public List<Board> getList() {
        return repository.getList();
    }

    // 상세정보 리턴
    public Board get(int boardSeq) {
        return repository.get(boardSeq);
    }

    // 등록처리
    public void save(Board parameter) {
        Board board = repository.get(parameter.getBoardSeq());
        if(board == null) {
            repository.save(board);
        } else {
            repository.update(parameter);
        }
    }

    // 업뎃처리
    public void update(Board board) {
        repository.update(board);
    }

    // 삭제처리
    public void delete(int boardSeq) {
        repository.delete(boardSeq);
     }
}
