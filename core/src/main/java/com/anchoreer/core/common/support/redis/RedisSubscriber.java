package com.anchoreer.core.common.support.redis;

import com.anchoreer.core.common.support.redis.dto.request.ChattingMessageBody;
import com.anchoreer.core.common.util.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisSubscriber implements MessageListener {
    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

        @Override
        public void onMessage(Message message, byte[] pattern) {
            ChattingMessageBody chattingRequest = null;
            try {
                String pubMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
                chattingRequest = JsonHelper.fromJson(pubMessage, ChattingMessageBody.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e); // TODO
            }
            messagingTemplate.convertAndSend("/sub/chat/room/" + chattingRequest.getChattingRoomId(), chattingRequest);
        }
}
