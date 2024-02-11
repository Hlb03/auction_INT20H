package com.auction.auctions.service;

import com.auction.auctions.dto.representation.AuctionPhotoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AuctionPhotosService {

//    byte[] getPhotoViaId(Long id) throws IOException;
    AuctionPhotoDTO getPhotoViaId(Long id) throws IOException;

    List<AuctionPhotoDTO> getPhotosOfAuction(Long auctionId);

    String uploadPhoto(Long auctionId, List<MultipartFile> images) throws IOException;
}
