package mx.tecdesoftware.streaming_backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.tecdesoftware.streaming_backend.domain.Series;
import mx.tecdesoftware.streaming_backend.domain.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@Tag(name = "Series", description = "Manage TV series in the catalog")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping
    @Operation(summary = "Get all series", description = "Return a list of all registered series")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of series")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Series>> findAll() {
        return ResponseEntity.ok(seriesService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a series by ID", description = "Return a single series matching the given ID")
    @ApiResponse(responseCode = "200", description = "Series found")
    @ApiResponse(responseCode = "404", description = "No series found with the provided ID")
    public ResponseEntity<Series> findById(
            @Parameter(description = "ID of the series to retrieve", example = "1", required = true)
            @PathVariable Integer id) {
        return seriesService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a new series",
            description = "Register a new series in the catalog and return it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Series",
                                    value = """
                                            {
                                              "title": "Lucifer",
                                              "genre": "Misterio",
                                              "releaseYear": 2015
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Series created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid series data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Series> create(@RequestBody Series series) {
        Series saved = seriesService.save(series);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a series", description = "Delete a series by its ID")
    @ApiResponse(responseCode = "204", description = "Series deleted successfully")
    @ApiResponse(responseCode = "404", description = "No series found with the provided ID")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the series to delete", example = "1", required = true)
            @PathVariable Integer id) {
        boolean deleted = seriesService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}