package com.auction.auctions.controller;

import com.auction.auctions.dto.representation.AuctionDTO;
import com.auction.auctions.dto.representation.ParticipateInAuctionDTO;
import com.auction.auctions.service.AuctionParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class AuctionParticipantController {

    private final AuctionParticipantService participantService;

    @GetMapping("/user/{userEmail}")
    public List<AuctionDTO> getAllUserParticipatedAuctions(@PathVariable String userEmail) {
        return participantService.getAllUserParticipateAuctions(userEmail);
    }

    @PostMapping("/takePart")
    public String registerForAuction(@RequestBody ParticipateInAuctionDTO participate) {
        return participantService.participateInAuction(participate);
    }
}
