package com.picshop.catalog.repository;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PictureRepository extends R2dbcRepository<Picture, Integer> {
}
