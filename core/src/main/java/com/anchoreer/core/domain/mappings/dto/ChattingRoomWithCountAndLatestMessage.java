package com.anchoreer.core.domain.mappings.dto;

import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoomWithCountAndLatestMessage {
    private ChattingRoom chattingRoom;
    private Long count;
    private String message;
}
