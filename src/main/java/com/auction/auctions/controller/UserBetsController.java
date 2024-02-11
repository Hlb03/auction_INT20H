package com.auction.auctions.controller;

import com.auction.auctions.dto.creation.CreateBetDTO;
import com.auction.auctions.dto.representation.RepresentUserBetDTO;
import com.auction.auctions.service.UserBetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserBetsController {

    private final UserBetsService betsService;
    private final SimpMessagingTemplate messagingTemplate;


    @GetMapping("/bets/{auctionId}/all")
    public List<RepresentUserBetDTO> gainAllBetsOfAuction(@PathVariable Long auctionId) {
        return betsService.obtainAllBetsByAuction(auctionId);
    }

    @MessageMapping("/websocket/{auctionId}")  //send to /app/websocket/{auctionId}
    public void processBet(@DestinationVariable Long auctionId, CreateBetDTO bet) {
        messagingTemplate.convertAndSend("/bids/auction/" + auctionId, betsService.saveUserBet(bet));
    }
}