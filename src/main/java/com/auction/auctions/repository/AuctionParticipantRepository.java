package com.auction.auctions.repository;

import com.auction.auctions.entity.AuctionParticipant;
import com.auction.auctions.entity.composite_key.AuctionUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionParticipantRepository extends JpaRepository<AuctionParticipant, AuctionUserKey> {

    List<AuctionParticipant> findAllByUserId(Long userId);
}
