package mx.tecdesoftware.streaming_backend.web.controller;

import mx.tecdesoftware.streaming_backend.domain.Season;
import mx.tecdesoftware.streaming_backend.domain.service.SeasonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping
    public ResponseEntity<List<Season>> findAll() {
        return ResponseEntity.ok(seasonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Season> findById(@PathVariable Integer id) {
        return seasonService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Season> create(@RequestBody Season season) {
        Season saved = seasonService.save(season);
        return ResponseEntity.status(201).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = seasonService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}