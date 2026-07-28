package mx.tecdesoftware.streaming_backend.persistence.mapper;

import mx.tecdesoftware.streaming_backend.domain.Season;
import mx.tecdesoftware.streaming_backend.persistence.entity.Temporada;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SeriesMapper.class})
public interface SeasonMapper {

    @Mapping(source = "idTemporada", target = "id")
    @Mapping(source = "numeroTemporada", target = "seasonNumber")
    @Mapping(source = "anio", target = "year")
    @Mapping(source = "serie", target = "series")
    @Mapping(target = "episodes", ignore = true) // Evita el ciclo con EpisodeMapper
    Season toDomain(Temporada entity);

    @InheritInverseConfiguration
    @Mapping(target = "episodios", ignore = true)
    Temporada toEntity(Season domain);
}