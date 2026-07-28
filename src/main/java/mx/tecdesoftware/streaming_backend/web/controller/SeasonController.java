package mx.tecdesoftware.streaming_backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.tecdesoftware.streaming_backend.domain.Season;
import mx.tecdesoftware.streaming_backend.domain.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seasons")
@Tag(name = "Season", description = "Manage seasons belonging to a series")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping
    @Operation(summary = "Get all seasons", description = "Return a list of all registered seasons")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of seasons")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Season>> findAll() {
        return ResponseEntity.ok(seasonService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a season by ID", description = "Return a single season matching the given ID")
    @ApiResponse(responseCode = "200", description = "Season found")
    @ApiResponse(responseCode = "404", description = "No season found with the provided ID")
    public ResponseEntity<Season> findById(
            @Parameter(description = "ID of the season to retrieve", example = "1", required = true)
            @PathVariable Integer id) {
        return seasonService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a new season",
            description = "Register a new season and return it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Season",
                                    value = """
                                            {
                                              "seasonNumber": 1,
                                              "year": 2008,
                                              "series": {
                                                "id": 1
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Season created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid season data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Season> create(@RequestBody Season season) {
        Season saved = seasonService.save(season);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a season", description = "Delete a season by its ID")
    @ApiResponse(responseCode = "204", description = "Season deleted successfully")
    @ApiResponse(responseCode = "404", description = "No season found with the provided ID")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the season to delete", example = "1", required = true)
            @PathVariable Integer id) {
        boolean deleted = seasonService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}