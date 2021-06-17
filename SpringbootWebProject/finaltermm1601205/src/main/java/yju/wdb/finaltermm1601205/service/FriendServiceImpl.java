package yju.wdb.finaltermm1601205.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yju.wdb.finaltermm1601205.dto.BoardDTO;
import yju.wdb.finaltermm1601205.dto.FriendDTO;
import yju.wdb.finaltermm1601205.dto.PageRequestDTO;
import yju.wdb.finaltermm1601205.dto.PageResultDTO;
import yju.wdb.finaltermm1601205.entity.*;
import yju.wdb.finaltermm1601205.repository.BoardRepository;
import yju.wdb.finaltermm1601205.repository.FriendRepository;
import yju.wdb.finaltermm1601205.repository.ReplyRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class FriendServiceImpl implements FriendService{

    private final FriendRepository repository;


    @Override
    public Long register(FriendDTO dto) {

        log.info(dto);

        Friend board  = dtoToEntity(dto);

        repository.save(board);

        return board.getId();
    }

    @Override
    public PageResultDTO<FriendDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], FriendDTO> fn = (en -> entityToDTO((Friend) en[0]));

//        Page<Object[]> result = repository.getBoardWithReplyCount(
//                pageRequestDTO.getPageable(Sort.by("bno").descending())  );
        Page<Object[]> result = repository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())  );


        return new PageResultDTO<>(result, fn);
    }

    @Override
    public FriendDTO get(Long bno) {
        return null;
    }

//    @Override
//    public FriendDTO get(Long bno) {
//
//        Object result = repository.getBoardByBno(bno);
//
//        Object[] arr = (Object[])result;
//
//        return entityToDTO((Friend) arr[0]);
//    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        repository.deleteById(bno);

    }

    @Override
    public void modify(FriendDTO boardDTO) {

    }

//    @Transactional
//    @Override
//    public void modify(FriendDTO friendDTO) {
//
//        Friend friend = repository.getOne(friendDTO.getId());
//
//        if(friend != null) {
//
//            friend.changeTitle(friendDTO.getTitle());
//            friend.changeContent(friendDTO.getContent());
//
//            repository.save(friend);
//        }
//    }
}