package com.auction.auctions.service;

import com.auction.auctions.dto.creation.CreateMessageDTO;
import com.auction.auctions.dto.representation.MessageRepresentationDTO;

import java.util.List;

public interface MessageService {

    void saveMessage(CreateMessageDTO message);

    List<MessageRepresentationDTO> getAllAuctionMessages(Long auctionId);
}
