package com.auction.auctions.controller;

import com.auction.auctions.dto.creation.AuctionCreationDTO;
import com.auction.auctions.dto.representation.AuctionDTO;
import com.auction.auctions.entity.Auction;
import com.auction.auctions.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping("/{auctionId}")
    @ResponseStatus(HttpStatus.OK)
    public AuctionDTO obtainAuctionViaId(@PathVariable Long auctionId) {
        return auctionService.getAuctionById(auctionId);
    }

    @GetMapping("/createdBy/{userEmail}")
    @ResponseStatus(HttpStatus.OK)
    public List<AuctionDTO> obtainAllUserAuctions(@PathVariable String userEmail) {
        return auctionService.getAllUserAuction(userEmail);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AuctionDTO> obtainListOfAuctions() {
        return auctionService.getAllAuctions();
    }

    @PostMapping("/create/{userEmail}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addAuction(@PathVariable String userEmail, @RequestBody AuctionCreationDTO auction) {
        return auctionService.createNewAuction(userEmail, auction);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Long updateAuctionState(@RequestBody Auction auction) {
        return auctionService.updateAuction(auction);
    }
}
