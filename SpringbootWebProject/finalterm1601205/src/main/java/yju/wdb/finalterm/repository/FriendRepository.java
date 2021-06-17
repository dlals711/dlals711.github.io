package yju.wdb.finalterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yju.wdb.finalterm.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
