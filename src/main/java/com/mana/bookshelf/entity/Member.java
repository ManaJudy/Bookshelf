package com.mana.bookshelf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String address;
    @ManyToOne
    @JoinColumn(name = "subscription_type_id")
    private SubscriptionType subscriptionType;

    private String email;
    private String password;

    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;

    private Boolean isAdmin;

}
