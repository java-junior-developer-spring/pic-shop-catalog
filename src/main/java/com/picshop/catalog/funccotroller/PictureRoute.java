package com.picshop.catalog.funccotroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PictureRoute {
    @Bean
    public RouterFunction<ServerResponse> getPictureById(PictureHandler pictureHandler) {
        return RouterFunctions.route()
                .GET("/api/pictures", // Mono<T> handle(ServerRequest request)
                        serverRequest -> pictureHandler.pictureById(serverRequest))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> getPictureFilter(PictureHandler pictureHandler) {
        return RouterFunctions.route()
                .GET("/api/pictures/filtered", // Mono<T> handle(ServerRequest request)
                        serverRequest -> pictureHandler.pictureByParams(serverRequest))
                .build();
    }
}
