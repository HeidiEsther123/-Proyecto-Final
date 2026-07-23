package mx.tecdesoftware.streaming_backend.persistence.mapper;

import mx.tecdesoftware.streaming_backend.domain.Season;
import mx.tecdesoftware.streaming_backend.persistence.entity.Temporada;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    @Mapping(source = "idTemporada", target = "id")
    @Mapping(source = "numeroTemporada", target = "seasonNumber")
    @Mapping(source = "anio", target = "year")
    Season toDomain(Temporada entity);

    @Mapping(source = "id", target = "idTemporada")
    @Mapping(source = "seasonNumber", target = "numeroTemporada")
    @Mapping(source = "year", target = "anio")
    @Mapping(target = "serie", ignore = true)
    Temporada toEntity(Season domain);
}