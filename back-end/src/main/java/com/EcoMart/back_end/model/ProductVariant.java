package com.EcoMart.back_end.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@Table(name = "product_variants")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Integer stockQuantity;

    @NotNull
    private BigDecimal price;

    @Column(columnDefinition = "json")
    private String properties;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    private boolean inAuction = false;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    // List to hold image URLs
    @ElementCollection
    @CollectionTable(name = "product_variant_images", joinColumns = @JoinColumn(name = "variant_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    // Field to hold the primary image URL
    @NotBlank
    @Column(name = "primary_image_url")
    private String primaryImageUrl;
}
