package mx.tecdesoftware.streaming_backend.persistence.crud;

import mx.tecdesoftware.streaming_backend.persistence.entity.Episodio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodioCrudRepository extends CrudRepository<Episodio, Integer> {
    boolean existsByTemporada_IdTemporadaAndNumeroEpisodio(Integer idTemporada, Integer numeroEpisodio);
}