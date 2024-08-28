package com.natanxds.url_shortener.service;

import com.natanxds.url_shortener.dto.ShortenerUrlRequest;
import com.natanxds.url_shortener.entity.UrlEntity;
import com.natanxds.url_shortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String createShortenerUrl(ShortenerUrlRequest urlRequest, HttpServletRequest servletRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, urlRequest.url(), LocalDateTime.now().plusMinutes(1)));

        return servletRequest.getRequestURL().toString().replace("urls", id);
    }

    public Optional<UrlEntity> findById(String id) {
        return urlRepository.findById(id);
    }
}
