package com.picshop.catalog.funccotroller;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import com.picshop.catalog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PictureHandler {
    private final PictureService pictureService;

    @Autowired
    public PictureHandler(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    // /{url} serverRequest.pathVariable
    // ?id=1& serverRequest.queryParam
    public Mono<ServerResponse> pictureById(ServerRequest serverRequest) {
        return serverRequest.queryParam("id")
                .map(Integer::parseInt)
                .map(value -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(pictureService.getPictureById(value), Picture.class)
                )
                .orElse(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> pictureByParams(ServerRequest serverRequest) {
        int priceFrom = serverRequest.queryParam("price_from")
                .map(Integer::parseInt)
                .orElse(-1); // значение считается не переданным,
        // поиск по данному параметру не будет использоваться в select запросе
        int priceTo = serverRequest.queryParam("price_to")
                .map(Integer::parseInt)
                .orElse(-1); // значение считается не переданным,
        // поиск по данному параметру не будет использоваться в select запросе
        LocalDate dateFrom = serverRequest.queryParam("date_from")
                .map(LocalDate::parse)
                .orElse(LocalDate.EPOCH); // значение по умолчанию
        LocalDate dateTo = serverRequest.queryParam("date_to")
                .map(LocalDate::parse)
                .orElse(LocalDate.MAX); // значение по умолчанию
        System.out.println(priceTo);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pictureService.getPictureByFilter(priceFrom, priceTo, dateFrom, dateTo), Picture.class);
    }
}
