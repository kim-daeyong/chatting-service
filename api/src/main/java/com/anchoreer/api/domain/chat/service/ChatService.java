package com.anchoreer.api.domain.chat.service;

import com.anchoreer.core.common.support.redis.dto.request.ChattingMessageBody;

public interface ChatService {
    void sendMessage(ChattingMessageBody chattingMessage);
}
