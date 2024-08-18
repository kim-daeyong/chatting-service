package com.anchoreer.core.common.support.redis;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RedisTopicManager {
    private static final Map<Long, ChannelTopic> TOPIC_MAP = new HashMap<>();

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber redisSubscriber;

    public void joinChattingRoom(Long chattingRoomId) {
        ChannelTopic topic = getTopic(chattingRoomId);
        if (ObjectUtils.isEmpty(topic)) {
            topic = new ChannelTopic(String.valueOf(chattingRoomId));
            redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
            TOPIC_MAP.put(chattingRoomId, topic);
        }
    }

    public ChannelTopic getTopic(Long chattingRoomId) {
        return TOPIC_MAP.get(chattingRoomId);
    }
}
