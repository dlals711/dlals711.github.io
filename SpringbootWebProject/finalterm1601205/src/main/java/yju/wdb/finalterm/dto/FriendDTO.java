package yju.wdb.finalterm.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendDTO {
    private Long id;

    private String name;
    private int birthYear;
    private String gender;
    private String phone;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
