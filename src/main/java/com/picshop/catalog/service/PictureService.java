package com.picshop.catalog.service;

import com.picshop.catalog.model.Picture;
import com.picshop.catalog.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Mono<Picture> getPictureById(int id){
        return pictureRepository.findById(id)
                .flatMap(picture -> {
                    if (picture == null) return pictureRepository.findById(1);
                    return Mono.just(picture);
                });
                // .defaultIfEmpty(getDefaultPicture())

    }

    public Picture getDefaultPicture(){
        return new Picture();
    }
}

