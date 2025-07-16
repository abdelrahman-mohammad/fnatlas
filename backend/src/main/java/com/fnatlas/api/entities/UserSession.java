package com.fnatlas.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserSession(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
    }
}
