package mx.tecdesoftware.streaming_backend.domain.service;

import mx.tecdesoftware.streaming_backend.domain.Category;
import mx.tecdesoftware.streaming_backend.persistence.crud.CategoriaCrudRepository;
import mx.tecdesoftware.streaming_backend.persistence.entity.Categoria;
import mx.tecdesoftware.streaming_backend.persistence.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoriaCrudRepository categoriaCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoriaCrudRepository categoriaCrudRepository, CategoryMapper categoryMapper) {
        this.categoriaCrudRepository = categoriaCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<Category> findAll() {
        List<Category> result = new ArrayList<>();
        categoriaCrudRepository.findAll().forEach(entity -> result.add(categoryMapper.toDomain(entity)));
        return result;
    }

    public Optional<Category> findById(Integer id) {
        return categoriaCrudRepository.findById(id)
                .map(categoryMapper::toDomain);
    }

    public Category save(Category category) {
        Categoria entity = categoryMapper.toEntity(category);
        Categoria saved = categoriaCrudRepository.save(entity);
        return categoryMapper.toDomain(saved);
    }

    public boolean deleteById(Integer id) {
        if (!categoriaCrudRepository.existsById(id)) {
            return false;
        }
        categoriaCrudRepository.deleteById(id);
        return true;
    }
}