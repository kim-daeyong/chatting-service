package com.anchoreer.core.domain.mappings.repository;

import com.anchoreer.core.domain.mappings.entity.UserChattingRoom;
import com.anchoreer.core.domain.mappings.repository.querydsl.UserChattingRoomQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChattingRoomRepository extends JpaRepository<UserChattingRoom, Long>, UserChattingRoomQueryDslRepository {
}
