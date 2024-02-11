package com.auction.auctions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "auction_photos", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class AuctionPhotos implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "auction_photos_id_seq", name = "auction_photos_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "auction_photos_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "image_path")
    private String photo_ref;
    @Column(name = "photo_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;
}
