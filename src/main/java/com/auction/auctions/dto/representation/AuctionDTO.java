package com.auction.auctions.dto.representation;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AuctionDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private BigDecimal startPrice;
    private LocalDateTime startTime;
    private String state;
    private List<AuctionPhotoDTO> photos;
}
