package com.picshop.catalog.converters;

import com.picshop.catalog.model.Genre;
import com.picshop.catalog.model.Picture;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDate;

@ReadingConverter
public class PictureReadConverter implements Converter<Row, Picture> {

    @Override
    public Picture convert(Row source) {
        Genre genre = new Genre();
        genre.setId(source.get("gnr_id", Integer.class));
        genre.setName(source.get("genre_name", String.class));
        genre.setUrl(source.get("url", String.class));

        Picture picture = new Picture();
        picture.setId(source.get("pic_id", Integer.class));
        picture.setDescription(source.get("picture_description", String.class));
        picture.setPrice(source.get("price", Integer.class));
        picture.setName(source.get("picture_name", String.class));
        picture.setTag(source.get("tag", String.class));
        picture.setImgPath(source.get("img_path", String.class));
        picture.setCreatedAt(source.get("created_at", LocalDate.class));
        picture.setInArchive(source.get("in_archive", Boolean.class));
        picture.setGenre(genre);
        return picture;
     }
}