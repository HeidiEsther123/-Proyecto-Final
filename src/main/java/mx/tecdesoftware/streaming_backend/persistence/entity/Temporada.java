package mx.tecdesoftware.streaming_backend.persistence.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "temporada")
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_temporada")
    private Integer idTemporada;

    @Column(name = "numero_temporada", nullable = false)
    private Integer numeroTemporada;

    @Column(name = "anio")
    private Integer anio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serie", nullable = false)
    private Serie serie;

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episodio> episodios = new ArrayList<>();

    public Temporada() {}

    public Integer getIdTemporada() { return idTemporada; }
    public void setIdTemporada(Integer idTemporada) { this.idTemporada = idTemporada; }

    public Integer getNumeroTemporada() { return numeroTemporada; }
    public void setNumeroTemporada(Integer numeroTemporada) { this.numeroTemporada = numeroTemporada; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Serie getSerie() { return serie; }
    public void setSerie(Serie serie) { this.serie = serie; }

    public List<Episodio> getEpisodios() { return episodios; }
    public void setEpisodios(List<Episodio> episodios) { this.episodios = episodios; }
}