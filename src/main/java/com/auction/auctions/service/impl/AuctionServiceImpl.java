package com.auction.auctions.service.impl;

import com.auction.auctions.dto.creation.AuctionCreationDTO;
import com.auction.auctions.dto.representation.AuctionDTO;
import com.auction.auctions.dto.representation.AuctionPhotoDTO;
import com.auction.auctions.entity.Auction;
import com.auction.auctions.entity.AuctionState;
import com.auction.auctions.repository.AuctionRepository;
import com.auction.auctions.repository.UserRepository;
import com.auction.auctions.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    // TODO: could not work, because object is removed
    @Override
    public Long createNewAuction(String userEmail, AuctionCreationDTO auction) {
        log.info("Creating new auction with params: {}", auction);
        return auctionRepository.save(
                Auction.builder()
                        .title(auction.getTitle())
                        .description(auction.getDescription())
                        .startPrice(auction.getStartPrice())
                        .startTime(auction.getStartTime())
                        .state(AuctionState.PREPARING)
                        .user(userRepository.getUserByEmail(userEmail))
                        .build()
        ).getId();
//        return temp.getId();
    }

    @Override
    public AuctionDTO getAuctionById(Long id) {
        log.info("Obtaining auction information by its id {}", id);
        return convertAuctionToDTO(auctionRepository.findById(id).orElse(null));
    }

    @Override
    public List<AuctionDTO> getAllUserAuction(String userEmail) {
        log.info("Gaining all auctions, where creator has email {}", userEmail);
        return auctionRepository.findAllByUserId(userRepository.getUserByEmail(userEmail).getId())
                .stream()
                .map(this::convertAuctionToDTO)
                .toList();
    }

    @Override
    public List<AuctionDTO> getAllAuctions() {
        log.info("Getting all auctions");
        return auctionRepository.findAll().stream()
                .map(this::convertAuctionToDTO)
                .toList();
    }

    @Override
    public Long updateAuction(Auction auction) {
        log.info("Data to update auction: {}", auction);
        Auction alreadyStoredAuction = auctionRepository.findById(auction.getId()).get();
        auction.setUser(alreadyStoredAuction.getUser());
        auction.setState(alreadyStoredAuction.getState());
        return auctionRepository.save(auction).getId();
    }

    private AuctionDTO convertAuctionToDTO(Auction auction) {
        return AuctionDTO.builder()
                .id(auction.getId())
                .title(auction.getTitle())
                .description(auction.getDescription())
                .startPrice(auction.getStartPrice())
                .startTime(auction.getStartTime())
                .state(auction.getState().name())
                .photos(auction.getPhotos()
                        .stream()
                        .map(photo -> {
                            try {
                                return AuctionPhotoDTO.builder().id(photo.getId())
                                        .image(Arrays.toString(Files.readAllBytes(new File(photo.getPhoto_ref()).toPath())))
                                        .build();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList())
                .build();
    }
}
