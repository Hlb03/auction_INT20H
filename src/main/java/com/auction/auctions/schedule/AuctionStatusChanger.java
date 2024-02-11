package com.auction.auctions.schedule;

import com.auction.auctions.entity.Auction;
import com.auction.auctions.entity.AuctionState;
import com.auction.auctions.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuctionStatusChanger {

    private final AuctionRepository auctionRepository;

    @Scheduled(cron = "@hourly", zone = "Europe/Kiev")
    public void updateAuctionStates() {
        LocalDateTime time =  LocalDateTime.now().plusHours(2L);
        log.info("STARTED UPDATING AUCTION STATUSES!!! Current time: {}", time);
        List<Auction> auctions = auctionRepository.findAll();
        for (Auction auction : auctions) {
            changeAuctionStatus(time, auction);
        }
        auctionRepository.saveAll(auctions);
    }

    private void changeAuctionStatus(LocalDateTime time, Auction auction) {
        if (!auction.getStartTime().isAfter(time) && auction.getState() == AuctionState.PREPARING) {
            log.info("Updating auction with id {}. Setting state from {} to {}", auction.getId(), auction.getState(), AuctionState.OPEN);
            auction.setState(AuctionState.OPEN);
        }
        else if (!auction.getStartTime().plusHours(1L).isAfter(time) && auction.getState() == AuctionState.OPEN) {
            log.info("Updating auction with id {}. Setting state from {} to {}", auction.getId(), auction.getState(), AuctionState.CLOSED);
            auction.setState(AuctionState.CLOSED);
        }
    }
}
