package com.picshop.catalog.funccotroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class GenreRouterConfiguration {
    @Bean
    public RouterFunction<ServerResponse> getBookByUrl(GenreHandler genreHandler) {
        return RouterFunctions.route()
                .GET("/api/genres/{url}", // Mono<T> handle(ServerRequest request)
                        serverRequest -> genreHandler.bookByUrl(serverRequest))
                .build();
    }
}
