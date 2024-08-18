package com.anchoreer.api.domain.chat.controller;

import com.anchoreer.api.domain.chat.service.ChatService;
import com.anchoreer.core.common.support.redis.dto.request.ChattingMessageBody;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void sendMessage(ChattingMessageBody chattingMessageBody) {
        chatService.sendMessage(chattingMessageBody);
    }
}
