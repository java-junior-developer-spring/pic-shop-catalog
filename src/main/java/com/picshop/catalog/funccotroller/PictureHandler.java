package com.picshop.catalog.funccotroller;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import com.picshop.catalog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PictureHandler {
    private final PictureService pictureService;

    @Autowired
    public PictureHandler(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    // /{url} serverRequest.pathVariable
    // ?id=1& serverRequest.queryParam
    public Mono<ServerResponse> pictureById(ServerRequest serverRequest){
        Integer id = Integer.valueOf(serverRequest.queryParam("id")
                .orElse(null));
        if (id == null) return ServerResponse.badRequest().build();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pictureService.getPictureById(id), Picture.class);
    }
}
// {"id":1, "price": 2000}
// {"id":1, "price": 2000, "genreName": "Портрет"}
// PictureDTO
// picture: picture {"id":1, "price": 2000,
// "genre":{"id":3, "url":"portrait", "name": "Портрет"}}
// genreName: picture.getGenre().getName()