package com.auction.auctions.repository;

import com.auction.auctions.entity.AuctionPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionPhotosRepository extends JpaRepository<AuctionPhotos, Long> {

    List<AuctionPhotos> getAuctionPhotosByAuctionId(Long auctionId);
}
