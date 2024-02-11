package com.auction.auctions.dto.creation;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CreateMessageDTO implements Serializable {

    private String text;
    private String userEmail;
    private Long auctionId;
}
