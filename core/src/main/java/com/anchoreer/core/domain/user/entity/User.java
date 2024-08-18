package com.anchoreer.core.domain.user.entity;

import com.anchoreer.core.domain.base.BaseEntity;
import com.anchoreer.core.domain.mappings.entity.UserChattingRoom;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Comment(value = "유저 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name= "users")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Long id;

    @Comment(value = "유저 id")
    @Column(length = 50, nullable = false, unique = true)
    private String userId;

    @Comment(value = "이메일")
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserChattingRoom> userChattingRooms = new ArrayList<>();
}
