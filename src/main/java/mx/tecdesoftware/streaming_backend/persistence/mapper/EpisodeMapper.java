package mx.tecdesoftware.streaming_backend.persistence.mapper;

import mx.tecdesoftware.streaming_backend.domain.Episode;
import mx.tecdesoftware.streaming_backend.persistence.entity.Episodio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SeasonMapper.class})
public interface EpisodeMapper {

    @Mapping(source = "idEpisodio", target = "id")
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "numeroEpisodio", target = "episodeNumber")
    @Mapping(source = "duracionMinutos", target = "durationMinutes")
    @Mapping(source = "temporada", target = "season")
    Episode toDomain(Episodio entity);

    @Mapping(source = "id", target = "idEpisodio")
    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "episodeNumber", target = "numeroEpisodio")
    @Mapping(source = "durationMinutes", target = "duracionMinutos")
    @Mapping(target = "temporada", ignore = true)
    Episodio toEntity(Episode domain);
}