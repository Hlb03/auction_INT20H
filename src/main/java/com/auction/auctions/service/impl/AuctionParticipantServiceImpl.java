package com.auction.auctions.service.impl;

import com.auction.auctions.dto.representation.AuctionDTO;
import com.auction.auctions.dto.representation.AuctionPhotoDTO;
import com.auction.auctions.dto.representation.ParticipateInAuctionDTO;
import com.auction.auctions.entity.Auction;
import com.auction.auctions.entity.AuctionParticipant;
import com.auction.auctions.entity.User;
import com.auction.auctions.entity.composite_key.AuctionUserKey;
import com.auction.auctions.repository.AuctionParticipantRepository;
import com.auction.auctions.repository.UserRepository;
import com.auction.auctions.service.AuctionParticipantService;
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
public class AuctionParticipantServiceImpl implements AuctionParticipantService {

    private final UserRepository userRepository;
    private final AuctionParticipantRepository participantRepository;

    @Override
    public String participateInAuction(ParticipateInAuctionDTO participate) {
        log.info("Data to participate in: {}", participate);
        User participant = userRepository.getUserByEmail(participate.getUserEmail());
        AuctionParticipant auctionParticipant = AuctionParticipant.builder()
                .id(AuctionUserKey.builder()
                        .auctionId(participate.getAuctionId())
                        .userId(participant.getId())
                        .build())
                .auction(Auction.builder()
                        .id(participate.getAuctionId())
                        .build())
                .user(participant)
                .build();
        log.info("Data to save: {}", auctionParticipant);
        participantRepository.save(auctionParticipant);

        return String.format("%s was successfully registered", participate.getUserEmail());
    }

    @Override
    public List<AuctionDTO> getAllUserParticipateAuctions(String userEmail) {
        log.info("Gaining all auction, which {} participated in", userEmail);
        return participantRepository.findAllByUserId(userRepository.getUserByEmail(userEmail).getId()).stream()
                .map(participant -> convertAuctionToDTO(participant.getAuction()))
                .toList();
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
