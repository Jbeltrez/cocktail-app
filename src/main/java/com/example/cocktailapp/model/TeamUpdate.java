package com.example.cocktailapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class TeamUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The update message content
    private String content;

    // Automatically set the timestamp when the update is created
    @CreationTimestamp
    private LocalDateTime timestamp;

    // Each update belongs to a team (this field is required)
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
