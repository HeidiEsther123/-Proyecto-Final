package mx.tecdesoftware.streaming_backend.web.controller;

import mx.tecdesoftware.streaming_backend.domain.Episode;
import mx.tecdesoftware.streaming_backend.domain.service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping
    public ResponseEntity<List<Episode>> findAll() {
        return ResponseEntity.ok(episodeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Episode> findById(@PathVariable Integer id) {
        return episodeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Episode> create(@RequestBody Episode episode) {
        Episode saved = episodeService.save(episode);
        return ResponseEntity.status(201).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = episodeService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
