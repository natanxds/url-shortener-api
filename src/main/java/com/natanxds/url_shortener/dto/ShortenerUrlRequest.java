package com.natanxds.url_shortener.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record ShortenerUrlRequest(
        String url
) {
}
