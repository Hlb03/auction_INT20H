package com.auction.auctions.repository;

import com.auction.auctions.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getAllByAuction_IdOrderBySendTimeAsc(Long auctionId);
}
