package mx.tecdesoftware.streaming_backend.persistence.crud;

import mx.tecdesoftware.streaming_backend.persistence.entity.Serie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieCrudRepository extends CrudRepository<Serie, Integer> {
    boolean existsByTituloIgnoreCase(String titulo);
}