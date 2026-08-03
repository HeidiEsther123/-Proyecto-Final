package mx.tecdesoftware.streaming_backend.domain.service;

import mx.tecdesoftware.streaming_backend.domain.Series;
import mx.tecdesoftware.streaming_backend.persistence.crud.CategoriaCrudRepository;
import mx.tecdesoftware.streaming_backend.persistence.crud.SerieCrudRepository;
import mx.tecdesoftware.streaming_backend.persistence.entity.Categoria;
import mx.tecdesoftware.streaming_backend.persistence.entity.Episodio;
import mx.tecdesoftware.streaming_backend.persistence.entity.Serie;
import mx.tecdesoftware.streaming_backend.persistence.entity.Temporada;
import mx.tecdesoftware.streaming_backend.persistence.mapper.SeriesMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    private final SerieCrudRepository serieCrudRepository;
    private final CategoriaCrudRepository categoriaCrudRepository;
    private final SeriesMapper seriesMapper;

    public SeriesService(SerieCrudRepository serieCrudRepository,
                         CategoriaCrudRepository categoriaCrudRepository,
                         SeriesMapper seriesMapper) {
        this.serieCrudRepository = serieCrudRepository;
        this.categoriaCrudRepository = categoriaCrudRepository;
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
        return serieCrudRepository.existsByTituloIgnoreCase(title);
    }

    public Optional<Series> save(Series series) {
        if (existsByTitle(series.getTitle())) {
            return Optional.empty();
        }

        Serie entity = seriesMapper.toEntity(series);

        // La Categoria no se crea en cascada: debe existir ya y se vincula por ID
        if (series.getCategory() != null && series.getCategory().getId() != null) {
            Categoria categoria = categoriaCrudRepository.findById(series.getCategory().getId())
                    .orElse(null);
            entity.setCategoria(categoria);
        }

        linkChildren(entity);
        Serie saved = serieCrudRepository.save(entity);
        return Optional.of(seriesMapper.toDomain(saved));
    }

    private void linkChildren(Serie serie) {
        if (serie.getTemporadas() != null) {
            for (Temporada temporada : serie.getTemporadas()) {
                temporada.setSerie(serie);
                if (temporada.getEpisodios() != null) {
                    for (Episodio episodio : temporada.getEpisodios()) {
                        episodio.setTemporada(temporada);
                    }
                }
            }
        }
    }

    public boolean deleteById(Integer id) {
        if (!serieCrudRepository.existsById(id)) {
            return false;
        }
        serieCrudRepository.deleteById(id);
        return true;
    }
}