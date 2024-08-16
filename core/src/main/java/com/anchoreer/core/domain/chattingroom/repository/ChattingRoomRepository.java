package com.anchoreer.core.domain.chattingroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchoreer.core.domain.chattingroom.entity.ChattingRoom;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    
}
