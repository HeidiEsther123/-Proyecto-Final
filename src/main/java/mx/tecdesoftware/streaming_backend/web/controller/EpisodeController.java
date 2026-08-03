package mx.tecdesoftware.streaming_backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.tecdesoftware.streaming_backend.domain.Episode;
import mx.tecdesoftware.streaming_backend.domain.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
@Tag(name = "Episode", description = "Manage episodes belonging to a season")
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping
    @Operation(summary = "Get all episodes", description = "Return a list of all registered episodes")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of episodes")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Episode>> findAll() {
        return ResponseEntity.ok(episodeService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an episode by ID", description = "Return a single episode matching the given ID")
    @ApiResponse(responseCode = "200", description = "Episode found")
    @ApiResponse(responseCode = "404", description = "No episode found with the provided ID")
    public ResponseEntity<Episode> findById(
            @Parameter(description = "ID of the episode to retrieve", example = "1", required = true)
            @PathVariable Integer id) {
        return episodeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a new episode",
            description = "Register a new episode and return it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Episode",
                                    value = """
                                        {
                                          "title": "Pilot",
                                          "episodeNumber": 1,
                                          "durationMinutes": 45,
                                          "season": { "id": 1 }
                                        }
                                        """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Episode created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid episode data")
    @ApiResponse(responseCode = "409", description = "This episode number already exists for the given season")
    public ResponseEntity<Episode> create(@RequestBody Episode episode) {
        return episodeService.save(episode)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an episode", description = "Delete an episode by its ID")
    @ApiResponse(responseCode = "204", description = "Episode deleted successfully")
    @ApiResponse(responseCode = "404", description = "No episode found with the provided ID")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the episode to delete", example = "1", required = true)
            @PathVariable Integer id) {
        boolean deleted = episodeService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}