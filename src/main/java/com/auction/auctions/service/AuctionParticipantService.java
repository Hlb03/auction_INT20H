package com.auction.auctions.service;

import com.auction.auctions.dto.representation.AuctionDTO;
import com.auction.auctions.dto.representation.ParticipateInAuctionDTO;

import java.util.List;

public interface AuctionParticipantService {

    String participateInAuction(ParticipateInAuctionDTO participate);

    List<AuctionDTO> getAllUserParticipateAuctions(String userEmail);
}
