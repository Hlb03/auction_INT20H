package com.auction.auctions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @SequenceGenerator(sequenceName = "message_id_seq", name = "message_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "message_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String text;
    @Column(name = "send_time")
    private LocalDateTime sendTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;
}
