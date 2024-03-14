package com.picshop.catalog.repository;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface PictureRepository extends R2dbcRepository<Picture, Integer> {
    // price_from price_to date_from date_to


    Flux<Picture> findPictureByPriceBetweenAndCreatedAtBetweenAndInArchiveFalse(
            int priceFrom, int priceTo,
            LocalDate dateFrom, LocalDate dateTo);

    @Query("SELECT * FROM pic_catalog_pictures WHERE price > :priceFrom AND in_archive = false")
    Flux<Picture> findWithFilter(int priceFrom);

    @Query("SELECT * FROM pic_catalog_pictures WHERE price > :priceFrom AND price < :priceTo AND in_archive = false")
    Flux<Picture> findWithFilter(int priceFrom, int priceTo);

    @Query("SELECT * FROM pic_catalog_pictures " +
            "WHERE (:priceFrom = -1 OR price > :priceFrom) AND (:priceTo = -1 OR price < :priceTo) " +
            "AND created_at > :dateFrom AND created_at < :dateTo AND in_archive = false")
    Flux<Picture> findWithFilter(int priceFrom, int priceTo, LocalDate dateFrom, LocalDate dateTo);

    @Query("select * from pic_catalog_pictures join pic_catalog_genres on pic_catalog_pictures.genre_id = pic_catalog_genres.id WHERE pic_catalog_pictures.id = :id")
    Mono<Picture> findById(int id);
}


// Каталог
// запрос - id пользователя
// ответ - id картин [1, 3]
// Заказы

// findAll + filter (price > and price <)
// where price > and price <
// Pagable()