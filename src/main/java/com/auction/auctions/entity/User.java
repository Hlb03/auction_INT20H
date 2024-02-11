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
@Table(name = "user", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "user_id_seq", name = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    @Column(name = "activity_rate")
    private Integer activityRate;
    @Column(name = "photo_url")
    private String photoURL;
}
