package com.natanxds.url_shortener.controller;

import com.natanxds.url_shortener.dto.ShortenerUrlRequest;
import com.natanxds.url_shortener.dto.ShortenerUrlResponse;
import com.natanxds.url_shortener.entity.UrlEntity;
import com.natanxds.url_shortener.repository.UrlRepository;
import com.natanxds.url_shortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping("/urls")
    public ResponseEntity<ShortenerUrlResponse> createShortUrl(@RequestBody ShortenerUrlRequest urlRequest,
                                                               HttpServletRequest servletRequest) {
        var redirectUrl = urlService.createShortenerUrl(urlRequest, servletRequest);

        return ResponseEntity.ok(new ShortenerUrlResponse(redirectUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        Optional<UrlEntity> optionalUrlEntity = urlService.findById(id);

        if (optionalUrlEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(optionalUrlEntity.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
