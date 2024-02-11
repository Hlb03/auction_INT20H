package com.auction.auctions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auction", schema = "public")
public class Auction implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "auction_id_seq", name = "auction_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "auction_id_seq",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    @Column(name = "start_price")
    private BigDecimal startPrice;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Enumerated(EnumType.STRING)
    private AuctionState state;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
    @OneToMany(mappedBy = "auction")
    List<AuctionPhotos> photos;
}
