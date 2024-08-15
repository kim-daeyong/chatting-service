package com.anchoreer.core.domain.chattingroom.entity;

import com.anchoreer.core.domain.chattingroom.dto.CreateChattingRoomDTO;
import com.anchoreer.core.domain.mappings.entity.UserChattingRoom;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Comment(value = "채팅방")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name= "chatting_room")
@Entity
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Long id;

    @Comment(value = "이름")
    @Column(length = 50,nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chattingRoom")
    private List<UserChattingRoom> groupMemberInfoEntities = new ArrayList<>();

    public static ChattingRoom of(CreateChattingRoomDTO createAlertGroupDTO) {
        return ChattingRoom.builder()
                .name(createAlertGroupDTO.getName())
                .build();
    }
}
