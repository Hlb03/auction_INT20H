package com.auction.auctions.controller;

import com.auction.auctions.dto.creation.CreateMessageDTO;
import com.auction.auctions.dto.representation.MessageRepresentationDTO;
import com.auction.auctions.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/write")
    @ResponseStatus(HttpStatus.CREATED)
    public void writeNewMessage(@RequestBody CreateMessageDTO message) {
        messageService.saveMessage(message);
    }

    @GetMapping("/all/{auctionId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageRepresentationDTO> gainAllAuctionMessages(@PathVariable Long auctionId) {
        return messageService.getAllAuctionMessages(auctionId);
    }
}
