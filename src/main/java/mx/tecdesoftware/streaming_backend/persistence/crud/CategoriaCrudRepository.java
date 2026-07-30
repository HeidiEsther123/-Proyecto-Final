package mx.tecdesoftware.streaming_backend.persistence.crud;

import mx.tecdesoftware.streaming_backend.persistence.entity.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
}