package com.anchoreer.api.domain.chattingroom.service;

import com.anchoreer.api.domain.chattingroom.dto.request.CreateChattingRoomRequest;

public interface ChattingRoomService {
    
    void createChattingRoom(CreateChattingRoomRequest chattingRoomRequest);

    void getChattingRoomList();

    void joinChattingRoom();

    void getChattingRoomMessage();
}
