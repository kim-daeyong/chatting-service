package com.anchoreer.core.domain.message.repository;

import com.anchoreer.core.domain.message.entity.ChattingMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingMessageRepository extends JpaRepository<ChattingMessage, Long> {
}
