package com.anchoreer.api.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChattingMessageRequest {
    private String nickname;
    private Long chattingRoomId;
    private String message;
}
