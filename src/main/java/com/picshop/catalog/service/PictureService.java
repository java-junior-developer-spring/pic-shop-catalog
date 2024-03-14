package com.picshop.catalog.service;

import com.picshop.catalog.model.Picture;
import com.picshop.catalog.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Mono<Picture> getPictureById(int id) {
        return pictureRepository.findById(id)
                .defaultIfEmpty(getDefaultPicture());

    }

    public Flux<Picture> getPictureByFilter(int priceFrom, int priceTo, LocalDate dateFrom, LocalDate dateTo) {
        return pictureRepository.findWithFilter(priceFrom, priceTo, dateFrom, dateTo)
                .flatMap(picture -> {
                    System.out.println(picture.getGenre());
                    return Flux.just(picture);
                })
                .doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                })
                .onErrorReturn(getDefaultPicture());
    }

    public Picture getDefaultPicture() {
        return new Picture();
    }
}

