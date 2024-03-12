package com.picshop.catalog.controller;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

//@RestController
//@RequestMapping("/api/genres")
public class GenreController {

    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // только объявление методов и описание входящих данных
    // все жанры /api/genres
    public Flux<Genre> getAllGenres(){
        return null;
    }
    /*public Mono<List<Genre>> getAllGenres(){
        return null;
    }

    public Flux<List<Genre>> getAllGenres(){
        return null;
    }
*/
    // жанр по url /api/genres/URL жанра
    @GetMapping("/{url}")
    public Mono<Genre> getGenreByUrl(@PathVariable String url){
        return genreService.genreMonoByUrl(url) // void accept(T t);
                .doOnError(throwable -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            throwable.getMessage());
                });
        // return null;
    }


}
