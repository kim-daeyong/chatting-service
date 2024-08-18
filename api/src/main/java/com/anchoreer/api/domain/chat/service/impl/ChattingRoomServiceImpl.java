package com.anchoreer.api.domain.chat.service.impl;

import com.anchoreer.api.domain.chat.dto.request.JoinChattingRoomRequest;
import com.anchoreer.core.common.support.ChatClient;
import com.anchoreer.core.common.support.redis.RedisTopicManager;
import com.anchoreer.core.domain.chattingroom.dto.CreateChattingRoomDTO;
import com.anchoreer.core.domain.chattingroom.dto.JoinChattingRoomDTO;
import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;
import com.anchoreer.core.domain.chattingroom.repository.ChattingRoomRepository;
import com.anchoreer.core.domain.mappings.dto.ChattingRoomWithCountAndLatestMessage;
import com.anchoreer.core.domain.mappings.entity.UserChattingRoom;
import com.anchoreer.core.domain.mappings.repository.UserChattingRoomRepository;
import com.anchoreer.core.domain.message.entity.ChattingMessage;
import com.anchoreer.core.domain.user.entity.User;
import com.anchoreer.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.anchoreer.api.domain.chat.dto.request.CreateChattingRoomRequest;
import com.anchoreer.api.domain.chat.service.ChattingRoomService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class ChattingRoomServiceImpl implements ChattingRoomService {
    private final UserRepository userRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserChattingRoomRepository userChattingRoomRepository;
    private final ChatClient chatClient;
    private final RedisTopicManager redisTopicManager;

    @Override
    public void createChattingRoom(CreateChattingRoomRequest chattingRoomRequest) {
        ChattingRoom chattingRoom = chattingRoomRepository.save(ChattingRoom.of(CreateChattingRoomDTO.builder()
                .name(chattingRoomRequest.getName())
                .build()));
        chatClient.createChattingRoom(chattingRoom);
    }

    @Override
    public List<ChattingRoomWithCountAndLatestMessage> getChattingRoomList() {
        return userChattingRoomRepository.findChattingRoomsByRecentActiveUsers();
    }

    @Override
    public void joinChattingRoom(JoinChattingRoomRequest joinChattingRoomRequest) {
        User user = userRepository.findById(joinChattingRoomRequest.getUserId())
                .orElseThrow(() -> new NoSuchElementException("not found user")); // TODO
        ChattingRoom chattingRoom = chattingRoomRepository.findById(joinChattingRoomRequest.getChattingRoomId())
                .orElseThrow(() -> new NoSuchElementException("not found chatting room")); // TODO
        userChattingRoomRepository.save(UserChattingRoom.of(JoinChattingRoomDTO.builder()
                .chattingRoom(chattingRoom)
                .user(user)
                .nickname(joinChattingRoomRequest.getNickname())
                .build()));
        redisTopicManager.joinChattingRoom(joinChattingRoomRequest.getChattingRoomId());
    }

    @Override
    public List<ChattingMessage> getChattingRoomMessage(Long chattingRoomId) {
        return chatClient.findChattingByChattingRoomId(chattingRoomId);
    }

    @Override
    public Long getChattingRoomJoinMemberCount(Long chattingRoomId) {
        return userChattingRoomRepository.getChattingRoomJoinMemberCount(chattingRoomId);
    }

}
