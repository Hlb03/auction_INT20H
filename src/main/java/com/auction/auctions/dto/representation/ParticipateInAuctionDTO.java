package com.auction.auctions.dto.representation;

import lombok.Data;

@Data
public class ParticipateInAuctionDTO {

    private String userEmail;
    private Long auctionId;
}
