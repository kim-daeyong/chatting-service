package com.anchoreer.api.domain.chat.controller;

import com.anchoreer.api.domain.chat.dto.request.JoinChattingRoomRequest;
import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;
import com.anchoreer.core.domain.mappings.dto.ChattingRoomWithCountAndLatestMessage;
import com.anchoreer.core.domain.message.entity.ChattingMessage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchoreer.api.domain.chat.dto.request.CreateChattingRoomRequest;
import com.anchoreer.api.domain.chat.service.ChattingRoomService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequestMapping("/api/v1/chat/room")
@RestController
@RequiredArgsConstructor
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;

    @PostMapping("")
    public void createChattingRoom(@RequestBody CreateChattingRoomRequest createChattingRoomRequest) {
        chattingRoomService.createChattingRoom(createChattingRoomRequest);
    }

    @GetMapping
    public List<ChattingRoomWithCountAndLatestMessage>  getChattingRoomList() {
        return chattingRoomService.getChattingRoomList();
    }

    @PostMapping("/join")
    public void joinChattingRoom(@RequestBody JoinChattingRoomRequest joinChattingRoomRequest) {
        chattingRoomService.joinChattingRoom(joinChattingRoomRequest);
    }

    @GetMapping("/{id}/message")
    public List<ChattingMessage> getChattingRoomMessage(@PathVariable Long id) {
        return chattingRoomService.getChattingRoomMessage(id);
    }

    @GetMapping("/{id}/count")
    public Long getChattingRoomJoinMemberCount(@PathVariable Long id) {
        return chattingRoomService.getChattingRoomJoinMemberCount(id);
    }
}
