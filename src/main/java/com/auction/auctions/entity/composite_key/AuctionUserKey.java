package com.auction.auctions.entity.composite_key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class AuctionUserKey implements Serializable {

    @Column(name = "auction_id")
    private Long auctionId;

    @Column(name = "user_id")
    private Long userId;
}
