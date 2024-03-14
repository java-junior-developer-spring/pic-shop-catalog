package com.picshop.catalog.configuration;

import com.picshop.catalog.converters.PictureReadConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CatalogConfiguration {
    @Bean
    public R2dbcCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new PictureReadConverter());
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters);
    }
}
