package mx.tecdesoftware.streaming_backend.domain.service;

import mx.tecdesoftware.streaming_backend.domain.Series;
import mx.tecdesoftware.streaming_backend.persistence.crud.SerieCrudRepository;
import mx.tecdesoftware.streaming_backend.persistence.entity.Serie;
import mx.tecdesoftware.streaming_backend.persistence.mapper.SeriesMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    private final SerieCrudRepository serieCrudRepository;
    private final SeriesMapper seriesMapper;

    public SeriesService(SerieCrudRepository serieCrudRepository, SeriesMapper seriesMapper) {
        this.serieCrudRepository = serieCrudRepository;
        this.seriesMapper = seriesMapper;
    }

    public List<Series> findAll() {
        List<Series> result = new ArrayList<>();
        serieCrudRepository.findAll().forEach(entity -> result.add(seriesMapper.toDomain(entity)));
        return result;
    }

    public Optional<Series> findById(Integer id) {
        return serieCrudRepository.findById(id)
                .map(seriesMapper::toDomain);
    }

    public boolean existsByTitle(String title) {
        return serieCrudRepository.findAll().stream()
                .anyMatch(s -> s.getTitulo().equalsIgnoreCase(title));
    }

    public Optional<Series> save(Series series) {
        if (existsByTitle(series.getTitle())) {
            return Optional.empty();
        }
        Serie entity = seriesMapper.toEntity(series);
        Serie saved = serieCrudRepository.save(entity);
        return Optional.of(seriesMapper.toDomain(saved));
    }

    public boolean deleteById(Integer id) {
        if (!serieCrudRepository.existsById(id)) {
            return false;
        }
        serieCrudRepository.deleteById(id);
        return true;
    }
}
