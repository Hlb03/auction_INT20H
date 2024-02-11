package com.auction.auctions.repository;

import com.auction.auctions.entity.UserBets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBetsRepository extends JpaRepository<UserBets, Long> {


    List<UserBets> findAllByAuctionIdOrderByBetTimeDesc(Long auctionId);
}
