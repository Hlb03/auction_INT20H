package com.auction.auctions.dto.representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionPhotoDTO {
    private Long id;
    private String image;
}
