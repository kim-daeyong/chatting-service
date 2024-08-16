package com.anchoreer.api.domain.chattingroom.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchoreer.api.domain.chattingroom.dto.request.CreateChattingRoomRequest;
import com.anchoreer.api.domain.chattingroom.service.ChattingRoomService;

import lombok.RequiredArgsConstructor;

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
    public void getChattingRoomList() {
        chattingRoomService.getChattingRoomList();
    }

    @PostMapping("/{id}")
    public void joinChattingRoom(@PathVariable Long id) {
        chattingRoomService.joinChattingRoom();
    }

    @GetMapping("/{id}")
    public void getChattingRoomMessage(@PathVariable Long id) {
        chattingRoomService.getChattingRoomMessage();
    }

    @DeleteMapping("/{id}")
    public void exitChattingRoom(@PathVariable Long id) {
        chattingRoomService.getChattingRoomMessage();
    }
    
}
