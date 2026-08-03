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

    public boolean existsBySeasonAndNumber(Integer seasonId, Integer episodeNumber) {
        return episodioCrudRepository.existsByTemporada_IdTemporadaAndNumeroEpisodio(seasonId, episodeNumber);
    }

    public Optional<Episode> save(Episode episode) {
        if (episode.getSeason() != null && episode.getSeason().getId() != null
                && existsBySeasonAndNumber(episode.getSeason().getId(), episode.getEpisodeNumber())) {
            return Optional.empty();
        }
        Episodio entity = episodeMapper.toEntity(episode);
        Episodio saved = episodioCrudRepository.save(entity);
        return Optional.of(episodeMapper.toDomain(saved));
    }

    public boolean deleteById(Integer id) {
        if (!episodioCrudRepository.existsById(id)) {
            return false;
        }
        episodioCrudRepository.deleteById(id);
        return true;
    }
}

