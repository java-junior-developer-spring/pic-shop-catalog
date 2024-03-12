package com.picshop.catalog.repository;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface PictureRepository extends R2dbcRepository<Picture, Integer> {
    // price_from price_to date_from date_to


    Flux<Picture> findPictureByPriceBetweenAndCreatedAtBetween(
            int priceFrom, int priceTo,
            LocalDate dateFrom, LocalDate dateTo);

    @Query("SELECT * FROM pic_catalog_pictures WHERE price > :priceFrom AND in_archive = false")
    Flux<Picture> findWithFilter(int priceFrom);

    @Query("SELECT * FROM pic_catalog_pictures WHERE price > :priceFrom AND price < :priceTo AND in_archive = false")
    Flux<Picture> findWithFilter(int priceFrom, int priceTo);

    @Query("SELECT * FROM pic_catalog_pictures " +
            "WHERE (:priceFrom = -1 OR price > :priceFrom) AND (:priceTo = -1 OR price < :priceTo) " +
            "AND created_at > :dateFrom AND created_at < :dateTo")
    Flux<Picture> findWithFilter(int priceFrom, int priceTo, LocalDate dateFrom, LocalDate dateTo);
}

// Каталог
// запрос - id пользователя
// ответ - id картин [1, 3]
// Заказы

// findAll + filter (price > and price <)
// where price > and price <
// Pagable()