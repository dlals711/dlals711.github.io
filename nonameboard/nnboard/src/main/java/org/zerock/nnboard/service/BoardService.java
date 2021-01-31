package org.zerock.nnboard.service;

import org.springframework.data.domain.Page;
import org.zerock.nnboard.dto.BoardDTO;
import org.zerock.nnboard.dto.PageRequestDTO;
import org.zerock.nnboard.dto.PageResultDTO;
import org.zerock.nnboard.entity.Board;

public interface BoardService {
    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);
    
    void removeWithReplies(Long bno); // 댓글삭제

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto) {
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        return board;
    }

    // BoardService 인터페이스에 추가하는 entityToDto()
    default BoardDTO entityToDTO(Board board, Long replyCount) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCount(replyCount.intValue()) // long 으로 나오므로 int
                .build();
        return boardDTO;
    }
}
