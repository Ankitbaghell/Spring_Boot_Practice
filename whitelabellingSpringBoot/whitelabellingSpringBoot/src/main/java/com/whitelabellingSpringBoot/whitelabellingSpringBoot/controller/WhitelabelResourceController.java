package com.whitelabellingSpringBoot.whitelabellingSpringBoot.controller;

import com.whitelabellingSpringBoot.whitelabellingSpringBoot.entity.WhitelabelStyling;
import com.whitelabellingSpringBoot.whitelabellingSpringBoot.service.WhitelabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whitelabel-resources")
public class WhitelabelResourceController {

    @Autowired
    private WhitelabelService whitelabelService;

    @GetMapping("/logo.png")
    private ResponseEntity getLogo(
                                   @RequestParam(name = "key", required = false) Long key) {
        WhitelabelStyling styling = getWhitelabelStyling(key);
        byte[] logo = styling.getLogo();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
//                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(logo);
    }

    @GetMapping("/style.css")
    private ResponseEntity getStyle(@RequestParam(name = "key", required = false) Long key) {

        WhitelabelStyling styling = getWhitelabelStyling( key);
        String css = styling.getCss();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("text/css"))
//                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(css);
    }

    private WhitelabelStyling getWhitelabelStyling( Long key) {
        WhitelabelStyling styling;

//        if (token != null && token.getUser() != null && token.getUser().getWhitelabelStyling() != null) {
//            styling = token.getUser().getWhitelabelStyling();
//        } else

        if (key != null) {
            styling = whitelabelService.getWhitelabelStyling(key)
                    .orElseThrow(() -> new IllegalArgumentException("No styling found for key " + key));
        } else {
            throw new IllegalArgumentException("Neither current user's organization has whitelabeling, nor a key was passed");
        }

        return styling;
    }
}
