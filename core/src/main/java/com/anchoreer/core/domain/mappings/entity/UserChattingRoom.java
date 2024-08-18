package com.anchoreer.core.domain.mappings.entity;

import com.anchoreer.core.domain.base.BaseEntity;
import com.anchoreer.core.domain.chattingroom.dto.JoinChattingRoomDTO;
import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;
import com.anchoreer.core.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Comment(value = "유저 채팅방 mapping")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name= "users_chatting_room")
@Entity
public class UserChattingRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Long id;

    @Comment(value = "닉네임")
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_room", referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ChattingRoom chattingRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users", referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    public static UserChattingRoom of(JoinChattingRoomDTO joinAlertGroupDTO) {
        return UserChattingRoom.builder()
                .chattingRoom(joinAlertGroupDTO.getChattingRoom())
                .user(joinAlertGroupDTO.getUser())
                .nickname(joinAlertGroupDTO.getNickname())
                .build();
    }
}
