package com.mana.bookshelf.controller;

import com.mana.bookshelf.dto.SubscriptionTypeDTO;
import com.mana.bookshelf.service.SubscriptionTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/subscription-types")
public class SubscriptionTypeController {
    private final SubscriptionTypeService subscriptionTypeService;

    public SubscriptionTypeController(SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionTypeDTO>> getAllSubscriptionTypes() {
        List<SubscriptionTypeDTO> subscriptionTypes = subscriptionTypeService.getSubscriptionTypes();
        return new ResponseEntity<>(subscriptionTypes, HttpStatus.OK);
    }
}
