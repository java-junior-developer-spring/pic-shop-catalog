package com.picshop.catalog.funccotroller;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.repository.GenreRepository;
import com.picshop.catalog.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GenreHandler {
    private GenreService genreService;

    @Autowired
    public GenreHandler(GenreService genreService) {
        this.genreService = genreService;
    }

    public Mono<ServerResponse> bookByUrl(ServerRequest serverRequest){
        String url = serverRequest.pathVariable("url");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(genreService.genreMonoByUrl(url), Genre.class);
    }
}

