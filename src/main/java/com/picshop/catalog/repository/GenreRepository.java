package com.picshop.catalog.repository;

import com.picshop.catalog.model.Genre;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface GenreRepository extends R2dbcRepository<Genre, Integer> {
    // Mono - производитель от 0 до 1
    // Flux - производитель от 0 до n
    // метод получения жанра по url
    Mono<Genre> findByUrl(String url);
}
