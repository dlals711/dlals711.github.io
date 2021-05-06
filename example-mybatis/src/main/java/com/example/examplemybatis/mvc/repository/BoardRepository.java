package com.example.examplemybatis.mvc.repository;

import com.example.examplemybatis.mvc.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 게시판 서비스
 * @author 이영민
 */
@Repository
public interface BoardRepository {
    List<Board> getList();

    Board get(int boardSeq);

    void save(Board board);

    void update(Board board);

    void delete(int boardSeq);
}
