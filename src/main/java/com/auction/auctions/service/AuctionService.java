package com.auction.auctions.service;

import com.auction.auctions.dto.creation.AuctionCreationDTO;
import com.auction.auctions.dto.representation.AuctionDTO;
import com.auction.auctions.entity.Auction;

import java.util.List;

public interface AuctionService {

    Long createNewAuction(String userEmail, AuctionCreationDTO auction);

    AuctionDTO getAuctionById(Long id);

    List<AuctionDTO> getAllUserAuction(String userEmail);

    List<AuctionDTO> getAllAuctions();

    Long updateAuction(Auction auction);
}
