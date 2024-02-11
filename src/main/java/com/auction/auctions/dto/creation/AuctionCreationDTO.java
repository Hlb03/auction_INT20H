package com.auction.auctions.dto.creation;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AuctionCreationDTO {

    private String title;
    private String description;
    private BigDecimal startPrice;
    private LocalDateTime startTime;
}
