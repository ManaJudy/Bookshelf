package com.mana.bookshelf.converter.entitytodto;

import com.mana.bookshelf.dto.SubscriptionTypeDTO;
import com.mana.bookshelf.entity.SubscriptionType;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SubscriptionTypeToSubscriptionTypeDTO implements Function<SubscriptionType, SubscriptionTypeDTO> {
    @Override
    public SubscriptionTypeDTO apply(SubscriptionType subscriptionType) {
        if (subscriptionType == null) return null;
        SubscriptionTypeDTO dto = new SubscriptionTypeDTO();
        dto.setId(subscriptionType.getId());
        dto.setName(subscriptionType.getName());
        dto.setQuota(subscriptionType.getQuota());
        return dto;
    }
}
