package com.anchoreer.core.domain.message.entity;

import com.anchoreer.core.common.support.redis.dto.request.ChattingMessageBody;
import com.anchoreer.core.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

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
public class ChattingMessage extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Long id;

    @Comment(value = "메세지")
    private String message;

    @Comment(value = "nickname")
    private String nickname;

    @Comment(value = "유저 id")
    private Long userId;

    @Comment(value = "채팅방 id")
    private Long chattingRoomId;

    public static ChattingMessage of(ChattingMessageBody chattingMessageBody) {
        return ChattingMessage.builder()
                .chattingRoomId(chattingMessageBody.getChattingRoomId())
                .nickname(chattingMessageBody.getNickname())
                .message(chattingMessageBody.getMessage())
                .userId(chattingMessageBody.getUserId())
                .build();
    }

}
