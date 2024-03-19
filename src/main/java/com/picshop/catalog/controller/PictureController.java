package com.picshop.catalog.controller;

import com.picshop.catalog.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/catalog/api/pictures")
public class PictureController {
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/archive/{picturesIds}")
    public Mono<ResponseEntity<Void>> archivePictures(@PathVariable List<Integer> picturesIds) {
        System.out.println(picturesIds);
        return pictureService.archivePictures(picturesIds)
                .hasElements()
                .flatMap(value -> {
                    if (value) return Mono.just(new ResponseEntity<>(HttpStatus.OK));
                    return Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }
}
