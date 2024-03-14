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
                // Реализовать следующий функционал:
                // 1. по запросу /api/pictures/recommended клиенту должен вернуться список картин
                // 2. первыми в списке передаются картины, похожие на купленные пользователем ранее
                // (совпадения искать по атрибуту tag)
                // 3. список с идентификаторами картин, которые покупал пользователь
                // приходит по запросу на сервис заказов (класс CatalogToOrderService)
                .GET("/api/pictures/recommended", pictureHandler::recommendedPictures)
                .build();
    }
}
