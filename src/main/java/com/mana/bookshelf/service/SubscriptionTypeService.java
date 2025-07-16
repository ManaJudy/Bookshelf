package com.mana.bookshelf.service;

import com.mana.bookshelf.converter.entitytodto.SubscriptionTypeToSubscriptionTypeDTO;
import com.mana.bookshelf.dto.SubscriptionTypeDTO;
import com.mana.bookshelf.repository.SubscriptionTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionTypeService {
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final SubscriptionTypeToSubscriptionTypeDTO subscriptionTypeToSubscriptionTypeDTO;

    public SubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository, SubscriptionTypeToSubscriptionTypeDTO subscriptionTypeToSubscriptionTypeDTO) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.subscriptionTypeToSubscriptionTypeDTO = subscriptionTypeToSubscriptionTypeDTO;
    }

    public List<SubscriptionTypeDTO> getSubscriptionTypes() {
        return subscriptionTypeRepository.findAll()
                .stream()
                .map(subscriptionTypeToSubscriptionTypeDTO)
                .toList();
    }
}
