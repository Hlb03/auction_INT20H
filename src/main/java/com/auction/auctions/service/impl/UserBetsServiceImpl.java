package com.auction.auctions.service.impl;

import com.auction.auctions.dto.creation.CreateBetDTO;
import com.auction.auctions.dto.representation.RepresentUserBetDTO;
import com.auction.auctions.entity.Auction;
import com.auction.auctions.entity.User;
import com.auction.auctions.entity.UserBets;
import com.auction.auctions.repository.UserBetsRepository;
import com.auction.auctions.repository.UserRepository;
import com.auction.auctions.service.UserBetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserBetsServiceImpl implements UserBetsService {

    private final UserBetsRepository betsRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RepresentUserBetDTO saveUserBet(CreateBetDTO bet) {
        log.info("Saving bet with the following information: {}", bet);

        UserBets bets = betsRepository.save(UserBets.builder()
                .bid(bet.getBid())
                .betTime(LocalDateTime.now())
                .user(User.builder()
                        .id(userRepository.getUserByEmail(bet.getUserEmail()).getId())
                        .build())
                .auction(Auction.builder()
                        .id(bet.getAuctionId())
                        .build())
                .build());

        // TODO: change it to getOneById method
//        User user = userRepository.getUserById(bets.getUser().getId());
        User user = userRepository.getReferenceById(bets.getUser().getId());
        RepresentUserBetDTO betRepresentation = convertToDTO(bets);
        betRepresentation.setUserEmail(user.getEmail());
        betRepresentation.setUserPhotoURL(user.getPhotoURL());
        log.info("Return bet to the websocket: {}", betRepresentation);
        return betRepresentation;
    }

    @Override
    public List<RepresentUserBetDTO> obtainAllBetsByAuction(Long auctionId) {
        log.info("Obtaining all bets dedicated to auction with id {}", auctionId);
        return betsRepository.findAllByAuctionIdOrderByBetTimeDesc(auctionId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private RepresentUserBetDTO convertToDTO(UserBets bets) {
        return RepresentUserBetDTO.builder()
                .id(bets.getBetId())
                .bid(bets.getBid())
                .betTime(bets.getBetTime())
                .userEmail(bets.getUser().getEmail())
                .userPhotoURL(bets.getUser().getPhotoURL())
                .build();
    }
}
