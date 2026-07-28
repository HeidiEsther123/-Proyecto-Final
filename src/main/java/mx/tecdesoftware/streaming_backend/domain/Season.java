package mx.tecdesoftware.streaming_backend.domain;

import java.util.List;

public class Season {

    private Integer id;
    private Integer seasonNumber;
    private Integer year;
    private List<Episode> episodes;
    private Series series; // <-- Agregar la referencia a la serie

    public Season() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getSeasonNumber() { return seasonNumber; }
    public void setSeasonNumber(Integer seasonNumber) { this.seasonNumber = seasonNumber; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public List<Episode> getEpisodes() { return episodes; }
    public void setEpisodes(List<Episode> episodes) { this.episodes = episodes; }

    public Series getSeries() { return series; }
    public void setSeries(Series series) { this.series = series; } 
}