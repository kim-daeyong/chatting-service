package com.anchoreer.core.domain.mappings.repository.querydsl;

import com.anchoreer.core.domain.mappings.dto.ChattingRoomWithCountAndLatestMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChattingRoomQueryDslRepository {
    List<ChattingRoomWithCountAndLatestMessage> findChattingRoomsByRecentActiveUsers();

    Long getChattingRoomJoinMemberCount(Long chatRoomId);
}
