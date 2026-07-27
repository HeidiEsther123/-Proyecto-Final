package mx.tecdesoftware.streaming_backend.persistence.mapper;

import mx.tecdesoftware.streaming_backend.domain.Category;
import mx.tecdesoftware.streaming_backend.persistence.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "idCategoria", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    Category toDomain(Categoria entity);

    @Mapping(source = "id", target = "idCategoria")
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(target = "series", ignore = true)
    Categoria toEntity(Category domain);
}