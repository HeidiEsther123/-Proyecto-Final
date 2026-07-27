package mx.tecdesoftware.streaming_backend.persistence.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_serie")
    private Integer idSerie;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "genero", length = 80)
    private String genero;

    @Column(name = "anio_lanzamiento")
    private Integer anioLanzamiento;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Temporada> temporadas = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Serie() {}

    public Integer getIdSerie() { return idSerie; }
    public void setIdSerie(Integer idSerie) { this.idSerie = idSerie; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Integer getAnioLanzamiento() { return anioLanzamiento; }
    public void setAnioLanzamiento(Integer anioLanzamiento) { this.anioLanzamiento = anioLanzamiento; }

    public List<Temporada> getTemporadas() { return temporadas; }
    public void setTemporadas(List<Temporada> temporadas) { this.temporadas = temporadas; }
}