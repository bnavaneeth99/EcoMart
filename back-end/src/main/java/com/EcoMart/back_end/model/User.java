package com.EcoMart.back_end.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    @Column(unique = true,nullable = false)
    private String email;


    @NotBlank
    private String passwordHash;

    @NotBlank
    private String passwordSalt;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is mandatory")
    @Column(nullable = false)
    private Role role;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    @Column(nullable = false, unique = true)
    private String phone_number;

    private String profileImageUrl;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @CreationTimestamp
    private ZonedDateTime created_at;

    @UpdateTimestamp
    private ZonedDateTime updated_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToOne
    @JoinColumn(name = "primary_address_id",nullable = false)
    private Address primaryAddress;

    public enum AccountStatus {
        ACTIVE,
        SUSPENDED,
        BANNED
    }

    public enum Role {
        BUYER,
        SELLER,
        ADMIN
    }
}
