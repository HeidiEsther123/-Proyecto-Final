package mx.tecdesoftware.streaming_backend.persistence.mapper;

import mx.tecdesoftware.streaming_backend.domain.Series;
import mx.tecdesoftware.streaming_backend.persistence.entity.Serie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeriesMapper {

    @Mapping(source = "idSerie", target = "id")
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "genero", target = "genre")
    @Mapping(source = "anioLanzamiento", target = "releaseYear")
    @Mapping(target = "seasons", ignore = true)
    Series toDomain(Serie entity);

    @Mapping(source = "id", target = "idSerie")
    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "genre", target = "genero")
    @Mapping(source = "releaseYear", target = "anioLanzamiento")
    @Mapping(target = "temporadas", ignore = true)
    Serie toEntity(Series domain);
}