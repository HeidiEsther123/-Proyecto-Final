package mx.tecdesoftware.streaming_backend.domain.service;

import mx.tecdesoftware.streaming_backend.domain.Season;
import mx.tecdesoftware.streaming_backend.persistence.crud.TemporadaCrudRepository;
import mx.tecdesoftware.streaming_backend.persistence.entity.Temporada;
import mx.tecdesoftware.streaming_backend.persistence.mapper.SeasonMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeasonService {

    private final TemporadaCrudRepository temporadaCrudRepository;
    private final SeasonMapper seasonMapper;

    public SeasonService(TemporadaCrudRepository temporadaCrudRepository, SeasonMapper seasonMapper) {
        this.temporadaCrudRepository = temporadaCrudRepository;
        this.seasonMapper = seasonMapper;
    }

    public List<Season> findAll() {
        List<Season> result = new ArrayList<>();
        temporadaCrudRepository.findAll().forEach(entity -> result.add(seasonMapper.toDomain(entity)));
        return result;
    }

    public Optional<Season> findById(Integer id) {
        return temporadaCrudRepository.findById(id)
                .map(seasonMapper::toDomain);
    }

    public boolean existsBySeriesAndNumber(Integer seriesId, Integer seasonNumber) {
        return temporadaCrudRepository.existsBySerie_IdSerieAndNumeroTemporada(seriesId, seasonNumber);
    }

    public Optional<Season> save(Season season) {
        if (season.getSeries() != null && season.getSeries().getId() != null
                && existsBySeriesAndNumber(season.getSeries().getId(), season.getSeasonNumber())) {
            return Optional.empty();
        }
        Temporada entity = seasonMapper.toEntity(season);
        Temporada saved = temporadaCrudRepository.save(entity);
        return Optional.of(seasonMapper.toDomain(saved));
    }

    public boolean deleteById(Integer id) {
        if (!temporadaCrudRepository.existsById(id)) {
            return false;
        }
        temporadaCrudRepository.deleteById(id);
        return true;
    }
}