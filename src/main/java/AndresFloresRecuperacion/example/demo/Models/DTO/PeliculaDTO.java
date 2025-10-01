package AndresFloresRecuperacion.example.demo.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PeliculaDTO {

    private Long ID;
    @NotBlank(message = "El titulo tiene que ser obligatorio")
    private String TiTULO;
    @NotBlank(message = "El Director tiene que se obligatorio")
    private String DIRECTOR;
    private Long Año;
    @NotBlank(message = "El genro tiene que ser obligatorio")
    private String GENERO;
    private Long PUNTUACION;




}
