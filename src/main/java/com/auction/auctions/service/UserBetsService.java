package com.auction.auctions.service;

import com.auction.auctions.dto.creation.CreateBetDTO;
import com.auction.auctions.dto.representation.RepresentUserBetDTO;

import java.util.List;

public interface UserBetsService {

    RepresentUserBetDTO saveUserBet(CreateBetDTO bet);

    List<RepresentUserBetDTO> obtainAllBetsByAuction(Long auctionId);
}
