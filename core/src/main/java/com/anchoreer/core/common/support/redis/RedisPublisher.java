package com.anchoreer.core.common.support.redis;

import com.anchoreer.core.common.support.redis.dto.request.ChattingMessageBody;
import com.anchoreer.core.common.util.JsonHelper;
import com.anchoreer.core.domain.message.entity.ChattingMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisPublisher {
    private final RedisTemplate<String, String> redisTemplate;

    public void publish(ChannelTopic topic, ChattingMessage chattingMessage) {
        try {
            redisTemplate.convertAndSend(topic.getTopic(), JsonHelper.toJson(chattingMessage));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // TODO
        }
    }
}
