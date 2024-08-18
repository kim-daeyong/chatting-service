package com.anchoreer.core.common.support.redis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ChattingMessageBody {
    private String nickname;
    private Long userId;
    private Long chattingRoomId;
    private String message;
}
