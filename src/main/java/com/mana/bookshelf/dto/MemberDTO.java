package com.mana.bookshelf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String address;
    private Long subscriptionTypeId;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;
    private LocalDate penaltyEndDate;
}
