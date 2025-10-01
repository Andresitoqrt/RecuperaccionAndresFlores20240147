package AndresFloresRecuperacion.example.demo.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter
@ToString@EqualsAndHashCode
@Table(name = "peliculas")
public class PeliculaEntity {

    @Column(name = "IDPELICULA")
    public Long Id;
    @Column(name = "TITULONOMBRE")
    public String TiTULO;
    @Column(name = "DIRECTOR")
    public String DIRECTOR;
    @Column(name = "Año")
    public Long Año;
    @Column(name = "GENERO")
    public String GENERO;
}
