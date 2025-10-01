package AndresFloresRecuperacion.example.demo.Controller;

import AndresFloresRecuperacion.example.demo.Exeption.ExceptionPeliculaNotFound;
import AndresFloresRecuperacion.example.demo.Exeption.ExceptionColumnDuplicate;
import AndresFloresRecuperacion.example.demo.Models.DTO.PeliculaDTO;
import AndresFloresRecuperacion.example.demo.Service.PeliculaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/Api/Peliculas")
@CrossOrigin
public class PeliculaController {

    @Autowired
    private PeliculaService service;

    @GetMapping("/GetAllPeliculas")
    private ResponseEntity<Page<PeliculaDTO>>getDataPeliculas(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size)
    {
        if (size <= 0 || size > 50) {
            ResponseEntity.badRequest().body(Map.of(
                    "Status", "Eltamaño de la pagina debe estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null);
        }
        Page<PeliculaDTO> datos = service.getAllPeliculas(page,size);

        if (datos == null) {
            ResponseEntity.badRequest().body(Map.of(
                    "Status", "NO hay pelicuals registradas"
            ));
        }return ResponseEntity.ok(datos);
    }
    @PostMapping("/CreatePeliculas")
    public ResponseEntity<Map<String, Object>> crear(@Valid @RequestBody PeliculaDTO json, HttpServletRequest request){
        try {
            PeliculaDTO response = service.insert(json);
            if (response == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Error","ESTA MALO :)"
                        ,"Status","Esta Malo"
                        ,"Descripcion","Verifique los valores"
                ));
            }
            return  ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado","Completado",
                    "data",response
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "Status","Esta Malo",
                            "message","NO registra la pelicula",
                            "detail",e.getMessage()
                    ));
        }
    }

    @PutMapping("/Actualizarpeliculas){id}")
    public ResponseEntity<?> modificarPelicula(
            @PathVariable Long id,
            @Valid @RequestBody  PeliculaDTO Titulo,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try{
            PeliculaDTO PeliculaActualizado = service.update(id, Titulo);
            return ResponseEntity.ok(PeliculaActualizado);
        }
        catch (ExceptionPeliculaNotFound e){
            return ResponseEntity.notFound().build();
        }
        catch (ExceptionColumnDuplicate e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("error", "Datos duplicados",
                            "campo", e.getColumDuplicate())
            );
        }
    }
    @DeleteMapping("/deletePelicula/{id}")
    public ResponseEntity<Map<String, Object>> eliminarpelicula(@PathVariable Long id) {
        try {

            if (!service.delete(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("Mensaje-Error", "Pelicula no encontrada")
                        .body(Map.of(
                                "error", "No furual",
                                "mensaje", "La Pelicula no ha sido encontrada",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",
                    "message", "Se elimino correctamente"
            ));

        } catch (Exception e) {

            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar la Pelicula",
                    "detail", e.getMessage()
            ));
        }
    }
}
