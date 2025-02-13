package com.tennis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1")
    private Player Player1;

    @ManyToOne
    @JoinColumn(name = "player2")
    private Player Player2;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player Winner;
}
