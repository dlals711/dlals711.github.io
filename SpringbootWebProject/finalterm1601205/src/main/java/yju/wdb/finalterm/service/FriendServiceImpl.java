package yju.wdb.finalterm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yju.wdb.finalterm.dto.FriendDTO;
import yju.wdb.finalterm.entity.Friend;
import yju.wdb.finalterm.repository.FriendRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository repository;

    @Override
    public void register(FriendDTO dto) {

    }

    @Override
    public void update(FriendDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public FriendDTO show(Long id) {
        return null;
    }

    @Override
    public List<FriendDTO> getList() {
        List<Friend> list = repository.findAll();
        List<FriendDTO> dtoList = list.stream().map(entity -> convertEntity2DTO(entity)).collect(Collectors.toList());

        return dtoList;
    }
}
