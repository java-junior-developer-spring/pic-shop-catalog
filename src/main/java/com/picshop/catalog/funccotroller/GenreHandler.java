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

// 1. получение картины по идентификатору
// 2. получение информации о картине +
//    идентификатор жанра
// 3. получение картин по идентификатору жанра
//    (жанры не передавать)
// 4. получение картин по цене - от и до
//                  по дате создания - от и до
// параметры не являются обязательными
