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
                .GET("/api/pictures", pictureHandler::pictureById)
                .GET("/api/pictures/filtered", pictureHandler::pictureByParams)
                .build();
    }
}
