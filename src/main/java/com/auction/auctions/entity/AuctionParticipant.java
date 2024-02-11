package com.auction.auctions.entity;

import com.auction.auctions.entity.composite_key.AuctionUserKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "auction_participants")
@AllArgsConstructor
@NoArgsConstructor
public class AuctionParticipant {

    @EmbeddedId
    private AuctionUserKey id;

    @ManyToOne
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
}
