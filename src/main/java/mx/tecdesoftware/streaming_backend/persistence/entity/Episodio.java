package mx.tecdesoftware.streaming_backend.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "episodio")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_episodio")
    private Integer idEpisodio;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "numero_episodio")
    private Integer numeroEpisodio;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_temporada", nullable = false)
    private Temporada temporada;

    public Episodio() {}

    public Integer getIdEpisodio() { return idEpisodio; }
    public void setIdEpisodio(Integer idEpisodio) { this.idEpisodio = idEpisodio; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getNumeroEpisodio() { return numeroEpisodio; }
    public void setNumeroEpisodio(Integer numeroEpisodio) { this.numeroEpisodio = numeroEpisodio; }

    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public Temporada getTemporada() { return temporada; }
    public void setTemporada(Temporada temporada) { this.temporada = temporada; }
}