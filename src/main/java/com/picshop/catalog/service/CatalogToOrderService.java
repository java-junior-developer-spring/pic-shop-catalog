package com.picshop.catalog.service;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.picshop.catalog.dto.OrderResponse01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class CatalogToOrderService {
    private WebClient webClient;

    @Autowired
    public CatalogToOrderService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://127.0.0.1:8082/order/api")
                .build();
    }

    // public Flux<OrderResponse01> ... bodyToFlux(OrderResponse01.class)
    // public Mono<Order> ... bodyToMono(Order.class)
    // Mono<List<OrderResponse01>> bodyToMono(OrderResponse01.class)
    //           toEntity(Order.class) статус, заголовки, тело(Order)
    //           toEntity(ParameterizedTypeReference) статус, заголовки, тело(List<OrderResponse01>)

    public Mono<List<OrderResponse01>> getPicturesIdsByUserId(int userId) {
        return webClient.get() // http method
                .uri("/user/pictures/{userId}", userId) // "/user/pictures/" + userId
                .retrieve()
                /*.toEntity()
                .flatMap(objectResponseEntity -> {
                    objectResponseEntity.getStatusCode();
                    objectResponseEntity.getHeaders();
                    objectResponseEntity.getBody();
                })*/
                .bodyToMono(new ParameterizedTypeReference<List<OrderResponse01>>() {
                })
                .timeout(Duration.ofSeconds(5)) // TimeOutException
                .onErrorReturn(Collections.emptyList());

    }


}
