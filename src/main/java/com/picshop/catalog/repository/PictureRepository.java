package com.picshop.catalog.repository;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface PictureRepository extends R2dbcRepository<Picture, Integer> {
    // price_from price_to date_from date_to
    @Query("UPDATE pic_catalog_pictures SET in_archive = true WHERE id IN (:picturesIds) RETURNING id")
    @Modifying
    @Transactional
    Flux<Integer> archivePictures(List<Integer> picturesIds);

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

    @Query("select pic.id as pic_id, picture_name, picture_description, tag, img_path, price, created_at, in_archive, genre_id, gnr.id as gnr_id, genre_name, url " +
            "from pic_catalog_pictures pic join pic_catalog_genres gnr on pic.genre_id = gnr.id WHERE pic.id = :id")
    Mono<Picture> findById(int id);


}


// Каталог
// запрос - id пользователя
// ответ - id картин [1, 3]
// Заказы

// findAll + filter (price > and price <)
// where price > and price <
// Pagable()