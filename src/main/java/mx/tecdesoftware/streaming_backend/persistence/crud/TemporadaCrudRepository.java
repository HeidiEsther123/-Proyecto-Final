package mx.tecdesoftware.streaming_backend.persistence.crud;

import mx.tecdesoftware.streaming_backend.persistence.entity.Temporada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporadaCrudRepository extends CrudRepository<Temporada, Integer> {
}