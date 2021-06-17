package yju.wdb.finaltermm1601205.service;

import yju.wdb.finaltermm1601205.dto.FriendDTO;
import yju.wdb.finaltermm1601205.dto.PageRequestDTO;
import yju.wdb.finaltermm1601205.dto.PageResultDTO;
import yju.wdb.finaltermm1601205.entity.Friend;

public interface FriendService {

    Long register(FriendDTO dto);

    PageResultDTO<FriendDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    FriendDTO get(Long bno);

    void removeWithReplies(Long bno);

    void modify(FriendDTO boardDTO);

    default Friend dtoToEntity(FriendDTO dto){

       // Member member = Member.builder().email(dto.getWriterEmail()).build();

        Friend friend = Friend.builder()
                .id(dto.getId())
                .name(dto.getName())
                .birthYear(dto.getBirthYear())
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .build();
        return friend;
    }

    default FriendDTO entityToDTO(Friend friend) {

        FriendDTO dto = FriendDTO.builder()
                .id(friend.getId())
                .name(friend.getName())
                .birthYear(friend.getBirthYear())
                .gender(friend.getGender())
                .phone(friend.getPhone())
                .regDate(friend.getRegDate())
                .modDate(friend.getModDate())
                .build();

        return dto;

    }
}