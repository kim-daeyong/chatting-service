package com.anchoreer.core.domain.chattingroom.dto;

import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;
import com.anchoreer.core.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinChattingRoomDTO {
    private ChattingRoom chattingRoom;
    private User user;
    private String nickname;
}
