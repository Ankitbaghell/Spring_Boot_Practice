package com.whitelabellingSpringBoot.whitelabellingSpringBoot.service;

import com.whitelabellingSpringBoot.whitelabellingSpringBoot.WhitelabelRepository;
import com.whitelabellingSpringBoot.whitelabellingSpringBoot.entity.WhitelabelStyling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhitelabelService {
    @Autowired
    private WhitelabelRepository whitelabelRepository;
    public Optional<WhitelabelStyling> getWhitelabelStyling(Long key) {
        return whitelabelRepository.findByUserId(key);
    }
}
