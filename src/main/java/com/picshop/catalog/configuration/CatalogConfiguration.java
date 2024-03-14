package com.picshop.catalog.configuration;

import com.picshop.catalog.converters.PictureReadConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CatalogConfiguration {

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    /*@Bean
    public WebClient webClient(){
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)
                .build();
    }*/

    @Bean // Converter<Row, Picture>
    public R2dbcCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new PictureReadConverter());
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters);
    }



}
