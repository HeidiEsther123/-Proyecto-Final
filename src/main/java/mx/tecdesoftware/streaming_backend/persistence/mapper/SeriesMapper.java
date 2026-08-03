package mx.tecdesoftware.streaming_backend.persistence.mapper;

import mx.tecdesoftware.streaming_backend.domain.Series;
import mx.tecdesoftware.streaming_backend.persistence.entity.Serie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, SeasonMapper.class})
public interface SeriesMapper {

    @Mapping(source = "idSerie", target = "id")
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "genero", target = "genre")
    @Mapping(source = "anioLanzamiento", target = "releaseYear")
    @Mapping(source = "categoria", target = "category")
    @Mapping(target = "seasons", ignore = true)
    Series toDomain(Serie entity);

    @Mapping(source = "id", target = "idSerie")
    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "genre", target = "genero")
    @Mapping(source = "releaseYear", target = "anioLanzamiento")
    @Mapping(target = "categoria", ignore = true)
    @Mapping(source = "seasons", target = "temporadas")
    Serie toEntity(Series domain);
}