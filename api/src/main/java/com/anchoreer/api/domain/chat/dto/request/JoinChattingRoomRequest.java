package com.anchoreer.api.domain.chat.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinChattingRoomRequest {
    private Long userId;
    private Long chattingRoomId;
    private String nickname;
}
