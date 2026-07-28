package mx.tecdesoftware.streaming_backend.domain;

public class Episode {

    private Integer id;
    private String title;
    private Integer episodeNumber;
    private Integer durationMinutes;
    private Season season; // <-- 1. Agregar el atributo

    public Episode() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(Integer episodeNumber) { this.episodeNumber = episodeNumber; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    // <-- 2. Agregar Getter y Setter obligatorios para MapStruct
    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}