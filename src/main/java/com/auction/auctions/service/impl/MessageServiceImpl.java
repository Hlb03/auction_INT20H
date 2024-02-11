package com.auction.auctions.service.impl;

import com.auction.auctions.dto.creation.CreateMessageDTO;
import com.auction.auctions.dto.representation.MessageRepresentationDTO;
import com.auction.auctions.entity.Auction;
import com.auction.auctions.entity.Message;
import com.auction.auctions.repository.MessageRepository;
import com.auction.auctions.repository.UserRepository;
import com.auction.auctions.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public void saveMessage(CreateMessageDTO message) {
        log.info("Saving message with the following params: {}", message);
        messageRepository.save(Message.builder()
                        .text(message.getText())
                        .sendTime(LocalDateTime.now())
                        .auction(Auction.builder()
                                .id(message.getAuctionId())
                                .build())
                        .user(userRepository.getUserByEmail(message.getUserEmail()))
                .build());
    }

    @Override
    public List<MessageRepresentationDTO> getAllAuctionMessages(Long auctionId) {
        log.info("Gaining all messages dedicated to auction with id {}", auctionId);
        return messageRepository.getAllByAuction_IdOrderBySendTimeAsc(auctionId)
                .stream()
                .map(basicMessage -> MessageRepresentationDTO.builder()
                        .id(basicMessage.getId())
                        .text(basicMessage.getText())
                        .sendTime(basicMessage.getSendTime())
                        .userEmail(basicMessage.getUser().getEmail())
                        .userPhotoURL(basicMessage.getUser().getPhotoURL())
                        .build())
                .toList();
    }
}
