package com.anchoreer.api.domain.chat.service;

import com.anchoreer.api.domain.chat.dto.request.CreateChattingRoomRequest;
import com.anchoreer.api.domain.chat.dto.request.JoinChattingRoomRequest;
import com.anchoreer.core.domain.mappings.dto.ChattingRoomWithCountAndLatestMessage;
import com.anchoreer.core.domain.message.entity.ChattingMessage;

import java.util.List;

public interface ChattingRoomService {
    
    void createChattingRoom(CreateChattingRoomRequest chattingRoomRequest);

    List<ChattingRoomWithCountAndLatestMessage> getChattingRoomList();

    void joinChattingRoom(JoinChattingRoomRequest joinChattingRoomRequest);

    List<ChattingMessage>  getChattingRoomMessage(Long chattingRoomId);

    Long  getChattingRoomJoinMemberCount(Long chattingRoomId);
}
