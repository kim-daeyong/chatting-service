package com.anchoreer.core.common.support;

import com.anchoreer.core.common.constant.RedisConstant;
import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;
import com.anchoreer.core.domain.message.entity.ChattingMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChatClient {
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChattingRoom> opsHashChattingRoom;
    private HashOperations<String, String, ChattingMessage> opsHashChattingMessage;

    @PostConstruct
    private void init() {
        opsHashChattingRoom = redisTemplate.opsForHash();
        opsHashChattingMessage = redisTemplate.opsForHash();
    }

    public void createChattingRoom(ChattingRoom chattingRoom) {
        opsHashChattingRoom.put(RedisConstant.CHATTING_ROOM, chattingRoom.getId().toString(), chattingRoom);
    }

    public List<ChattingRoom> getAllChatRoomsOrderedByRLatestJoinMember() {
        return opsHashChattingRoom.values(RedisConstant.CHATTING_ROOM);
    }

    public ChattingRoom findByCattingRoomId(Long chattingRoomId) {
        return opsHashChattingRoom.get(RedisConstant.CHATTING_ROOM, chattingRoomId);
    }

    public void saveChattingMessage(Long chattingRoomId, ChattingMessage message) {
        opsHashChattingMessage.put(RedisConstant.CHATTING_ROOM + chattingRoomId, String.valueOf(message.getId()), message);
    }

    public List<ChattingMessage> findChattingByChattingRoomId(Long chattingRoomId) {
        return opsHashChattingMessage.values(RedisConstant.CHATTING_ROOM + chattingRoomId);
    }
}
