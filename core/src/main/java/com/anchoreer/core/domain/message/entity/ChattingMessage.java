package com.anchoreer.core.domain.message.entity;

import com.anchoreer.core.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Comment(value = "채팅메세지")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name= "chatting_message", indexes = {
        @Index(name = "idx_chatting_message_nickname", columnList = "nickname"),
        @Index(name = "idx_chatting_message_room_id", columnList = "chatting_room_id")
})
@Entity
public class ChattingMessage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Long id;

    @Comment(value = "메세지")
    private String message;

    @Comment(value = "nickname")
    private String nickname;

    @Comment(value = "채팅방 id")
    private Long chattingRoomId;

}
