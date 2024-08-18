package com.anchoreer.core.domain.mappings.repository.querydsl;

import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;
import com.anchoreer.core.domain.mappings.dto.ChattingRoomWithCountAndLatestMessage;
import com.anchoreer.core.domain.message.entity.QChattingMessage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.List;

import static com.anchoreer.core.domain.chattingroom.entity.QChattingRoom.chattingRoom;
import static com.anchoreer.core.domain.mappings.entity.QUserChattingRoom.userChattingRoom;

public class UserChattingRoomQueryDslRepositoryImpl implements UserChattingRoomQueryDslRepository {
    private final JPAQueryFactory factory;

    public UserChattingRoomQueryDslRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.factory = jpaQueryFactory;
    }

    @Override
    public List<ChattingRoomWithCountAndLatestMessage> findChattingRoomsByRecentActiveUsers() {
        QChattingMessage latestMessage = new QChattingMessage("lm");

        LocalDateTime thirtyMinutesAgo = LocalDateTime.now().minusMinutes(30);

        return factory.select(
                    Projections.constructor(ChattingRoomWithCountAndLatestMessage.class,
                            userChattingRoom.chattingRoom,
                            userChattingRoom.count(),
                            JPAExpressions
                                    .select(latestMessage.message)
                                    .from(latestMessage)
                                    .where(latestMessage.chattingRoomId.eq(chattingRoom.id))
                                    .orderBy(latestMessage.createdAt.desc())
                                    .limit(1)
                    )
                )
                .from(userChattingRoom)
                .where(userChattingRoom.createdAt.after(thirtyMinutesAgo))
                .join(userChattingRoom.chattingRoom, chattingRoom)
                .groupBy(userChattingRoom.chattingRoom.id)
                .orderBy(userChattingRoom.count().desc())
                .fetch();
    }

    @Override
    public Long getChattingRoomJoinMemberCount(Long chatRoomId) {
        LocalDateTime thirtyMinutesAgo = LocalDateTime.now().minusMinutes(30);

        return factory.select(userChattingRoom.id.count())
                .from(userChattingRoom)
                .where(userChattingRoom.createdAt.after(thirtyMinutesAgo)
                        .and(userChattingRoom.chattingRoom.id.eq(chatRoomId)))
                .join(userChattingRoom.chattingRoom, chattingRoom)
                .fetchOne();
    }
}
