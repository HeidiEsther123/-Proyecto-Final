package mx.tecdesoftware.streaming_backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.tecdesoftware.streaming_backend.domain.Category;
import mx.tecdesoftware.streaming_backend.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "Manage genre categories used to classify series")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Return a list of all registered categories")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of categories")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by ID", description = "Return a single category matching the given ID")
    @ApiResponse(responseCode = "200", description = "Category found")
    @ApiResponse(responseCode = "404", description = "No category found with the provided ID")
    public ResponseEntity<Category> findById(
            @Parameter(description = "ID of the category to retrieve", example = "1", required = true)
            @PathVariable Integer id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a new category",
            description = "Register a new genre category and return it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Category",
                                    value = """
                                            {
                                              "name": "Terror",
                                              "description": "Series con suspenso que te deja aferrado a la silla "
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid category data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category saved = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by its ID")
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    @ApiResponse(responseCode = "404", description = "No category found with the provided ID")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the category to delete", example = "1", required = true)
            @PathVariable Integer id) {
        boolean deleted = categoryService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}