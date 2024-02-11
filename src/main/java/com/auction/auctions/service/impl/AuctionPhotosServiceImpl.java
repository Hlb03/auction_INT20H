package com.auction.auctions.service.impl;

import com.auction.auctions.dto.representation.AuctionPhotoDTO;
import com.auction.auctions.entity.Auction;
import com.auction.auctions.entity.AuctionPhotos;
import com.auction.auctions.repository.AuctionPhotosRepository;
import com.auction.auctions.service.AuctionPhotosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionPhotosServiceImpl implements AuctionPhotosService {

    @Value("${file.path}")
    private String FILE_PATH;

    private final AuctionPhotosRepository auctionPhotosRepository;

    @Override
    public AuctionPhotoDTO getPhotoViaId(Long id) throws IOException {
        log.info("Getting photo via id {}", id);
        AuctionPhotos auctionPhotos = auctionPhotosRepository.findById(id).get();
        String filePath = auctionPhotos.getPhoto_ref();
        byte[] imageBytes = Files.readAllBytes(new File(filePath).toPath());

        return AuctionPhotoDTO.builder()
                .id(auctionPhotos.getId())
                .image(Arrays.toString(imageBytes))
                .build();
    }

    @Override
    public List<AuctionPhotoDTO> getPhotosOfAuction(Long auctionId) {
        log.info("Gaining all photos dedicated to auction with id {}", auctionId);
        return auctionPhotosRepository.getAuctionPhotosByAuctionId(auctionId)
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
                .toList();
    }

    @Override
    public String uploadPhoto(Long auctionId, List<MultipartFile> images) throws IOException {
        StringBuilder returnMessage = new StringBuilder("File(s) was/were successfully upload to ");
        log.info("FILE PATH IS: {}", FILE_PATH);
        for (MultipartFile mf : images) {
            log.info("Trying to save image with name {} to the auction with id {}", mf.getOriginalFilename(), auctionId);
            String filePath = FILE_PATH + UUID.randomUUID() + mf.getOriginalFilename();
            log.info("NEW IMAGE NAME: " + filePath);


            auctionPhotosRepository.save(
                    AuctionPhotos.builder()
                            .description(mf.getOriginalFilename())
                            .photo_ref(filePath)
                            .auction(Auction.builder()
                                    .id(auctionId)
                                    .build())
                            .build()
            );

            mf.transferTo(new File(filePath));
            returnMessage.append(filePath).append("; ");
        }

        return returnMessage.toString();
    }
}
