package com.auction.auctions.dto.representation;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class MessageRepresentationDTO implements Serializable {
    private Long id;
    private String text;
    private LocalDateTime sendTime;
    private String userEmail;
    private String userPhotoURL;
}
