package AndresFloresRecuperacion.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Entity
@Getter@Setter
@ToString@EqualsAndHashCode
@Table(name = "peliculas")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_peliculas_id")
    @SequenceGenerator(sequenceName = "seq_peliculas_id", name = "seq_peliculas_id", allocationSize = 1)
    @Column(name = "IDPELICULA")
    private Long Id;
    @Column(name = "TITULONOMBRE")
    private String TiTULO;
    @Column(name = "DIRECTOR")
    private String DIRECTOR;
    @Column(name = "Año")
    private Long Año;
    @Column(name = "GENERO")
    private String GENERO;
    @Column(name = "PUNTUACION")
    private Long PUNTUACION;
}
