package com.natanxds.url_shortener.repository;

import com.natanxds.url_shortener.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
