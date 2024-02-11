package com.auction.auctions.dto.creation;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateBetDTO {

    private BigDecimal bid;
    private String userEmail;
    private Long auctionId;
}
