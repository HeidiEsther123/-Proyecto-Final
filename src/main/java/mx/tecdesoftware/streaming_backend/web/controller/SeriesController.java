package mx.tecdesoftware.streaming_backend.web.controller;

import mx.tecdesoftware.streaming_backend.domain.Series;
import mx.tecdesoftware.streaming_backend.domain.service.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping
    public ResponseEntity<List<Series>> findAll() {
        return ResponseEntity.ok(seriesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Series> findById(@PathVariable Integer id) {
        return seriesService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Series> create(@RequestBody Series series) {
        Series saved = seriesService.save(series);
        return ResponseEntity.status(201).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = seriesService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}