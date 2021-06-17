package yju.wdb.finalterm.service;

import yju.wdb.finalterm.dto.FriendDTO;
import yju.wdb.finalterm.entity.Friend;

import java.util.List;

public interface FriendService {
    void register(FriendDTO dto);

    void update(FriendDTO dto);

    void delete(Long id);

    FriendDTO show(Long id);

    List<FriendDTO> getList();

    default Friend convertDTO2Entity(FriendDTO dto) {

        Friend friend = Friend.builder()
                .id(dto.getId())
                .name(dto.getName())
                .birthYear(dto.getBirthYear())
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .build();

        return friend;
    }

    default FriendDTO convertEntity2DTO(Friend entity) {

        FriendDTO dto = FriendDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthYear(entity.getBirthYear())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
