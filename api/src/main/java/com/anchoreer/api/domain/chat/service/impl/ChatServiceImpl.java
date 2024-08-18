package com.anchoreer.api.domain.chat.service.impl;

import com.anchoreer.api.domain.chat.service.ChatService;
import com.anchoreer.core.common.support.ChatClient;
import com.anchoreer.core.common.support.redis.RedisPublisher;
import com.anchoreer.core.common.support.redis.RedisTopicManager;
import com.anchoreer.core.common.support.redis.dto.request.ChattingMessageBody;
import com.anchoreer.core.domain.message.entity.ChattingMessage;
import com.anchoreer.core.domain.message.repository.ChattingMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final RedisPublisher redisPublisher;
    private final ChatClient chatClient;
    private final RedisTopicManager redisTopicManager;
    private final ChattingMessageRepository chattingMessageRepository;

    @Override
    public void sendMessage(ChattingMessageBody chattingMessageBody) {
        ChattingMessage chattingMessage = chattingMessageRepository.save(ChattingMessage.of(chattingMessageBody));

        redisPublisher.publish(redisTopicManager.getTopic(chattingMessageBody.getChattingRoomId()), chattingMessage);
        chatClient.saveChattingMessage(chattingMessageBody.getChattingRoomId(), chattingMessage);
    }
}
