package org.zerock.nnboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.nnboard.dto.BoardDTO;
import org.zerock.nnboard.dto.PageRequestDTO;
import org.zerock.nnboard.dto.PageResultDTO;
import org.zerock.nnboard.entity.Board;
import org.zerock.nnboard.repository.BoardRepository;
import org.zerock.nnboard.repository.ReplyRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository repository; // 자동주입 final
    private final ReplyRepository replyRepository;
    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (Long)en[1]));

        Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Long) arr[1]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        // 댓글부터삭제
        replyRepository.deleteByBno(bno);

        repository.deleteById(bno);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = repository.getOne(boardDTO.getBno());

        if(board != null) {
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            repository.save(board);
        }
    }
}
