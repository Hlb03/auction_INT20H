package com.auction.auctions.dto.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class RepresentUserBetDTO implements Serializable {

    private Long id;
    private BigDecimal bid;
    private LocalDateTime betTime;
    private String userEmail;
    private String userPhotoURL;
}
