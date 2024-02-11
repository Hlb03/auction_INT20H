package com.auction.auctions.controller;

import com.auction.auctions.dto.representation.AuctionPhotoDTO;
import com.auction.auctions.service.AuctionPhotosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class AuctionPhotosController {

    private final AuctionPhotosService auctionPhotosService;

    @GetMapping(value = "/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public AuctionPhotoDTO obtainPhotoViaId(@PathVariable Long imageId) throws IOException {
        return auctionPhotosService.getPhotoViaId(imageId);
    }

    @GetMapping("/auction/{auctionId}")
    public List<AuctionPhotoDTO> obtainAllPhotosOfAuction(@PathVariable Long auctionId) {
        return auctionPhotosService.getPhotosOfAuction(auctionId);
    }

    @PostMapping("/upload/image/{auctionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadAuctionImage(@PathVariable Long auctionId, @RequestPart List<MultipartFile> image) throws IOException {
        return auctionPhotosService.uploadPhoto(auctionId, image);
    }
}
