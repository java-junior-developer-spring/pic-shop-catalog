package com.picshop.catalog.service;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import com.picshop.catalog.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GenreService {
    private GenreRepository genreRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Autowired
    public GenreService(GenreRepository genreRepository, R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.genreRepository = genreRepository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    public Mono<Genre> genreMonoByUrl(String url) {
        return genreRepository.findByUrl(url)
                .doOnError(throwable -> {
                    throw new RuntimeException();
                })
                .flatMap(genre -> {
                    // Mono.just()
                    // Mono.error()
                    // Mono.empty()
                    if (genre == null) return Mono.error(new Throwable());
                    return Mono.just(genre);
                });

    }



}
