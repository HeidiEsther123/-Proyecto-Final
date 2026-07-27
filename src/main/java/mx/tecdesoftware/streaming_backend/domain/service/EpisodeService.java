package mx.tecdesoftware.streaming_backend.domain.service;

import mx.tecdesoftware.streaming_backend.domain.Episode;
import mx.tecdesoftware.streaming_backend.persistence.crud.EpisodioCrudRepository;
import mx.tecdesoftware.streaming_backend.persistence.entity.Episodio;
import mx.tecdesoftware.streaming_backend.persistence.mapper.EpisodeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EpisodeService {

    private final EpisodioCrudRepository episodioCrudRepository;
    private final EpisodeMapper episodeMapper;

    public EpisodeService(EpisodioCrudRepository episodioCrudRepository, EpisodeMapper episodeMapper) {
        this.episodioCrudRepository = episodioCrudRepository;
        this.episodeMapper = episodeMapper;
    }

    public List<Episode> findAll() {
        List<Episode> result = new ArrayList<>();
        episodioCrudRepository.findAll().forEach(entity -> result.add(episodeMapper.toDomain(entity)));
        return result;
    }

    public Optional<Episode> findById(Integer id) {
        return episodioCrudRepository.findById(id)
                .map(episodeMapper::toDomain);
    }

    public Episode save(Episode episode) {
        Episodio entity = episodeMapper.toEntity(episode);
        Episodio saved = episodioCrudRepository.save(entity);
        return episodeMapper.toDomain(saved);
    }

    public boolean deleteById(Integer id) {
        if (!episodioCrudRepository.existsById(id)) {
            return false;
        }
        episodioCrudRepository.deleteById(id);
        return true;
    }
}

