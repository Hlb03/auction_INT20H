package com.auction.auctions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_bets")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBets implements Serializable {

    @Id
    @Column(name = "bet_id")
    @SequenceGenerator(sequenceName = "user_bets_bet_id_seq", name = "user_bets_bet_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_bets_bet_id_seq", strategy = GenerationType.SEQUENCE)
    private Long betId;

    private BigDecimal bid;
    @Column(name = "bet_time")
    private LocalDateTime betTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;
}
