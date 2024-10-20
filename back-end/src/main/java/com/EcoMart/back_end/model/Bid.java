package com.EcoMart.back_end.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ZonedDateTime bidDateTime;

    @NotNull
    private BigDecimal bidAmount;

    @NotNull // Application-level validation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)  // Database-level NOT NULL constraint
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY) //Will not load with parent DB. Will load when relation is called.
    @JoinColumn(name = "auction_id",nullable = false)
    private Auction auction;
}
