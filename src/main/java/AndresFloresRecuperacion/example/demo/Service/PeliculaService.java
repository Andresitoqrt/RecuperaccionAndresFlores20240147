package AndresFloresRecuperacion.example.demo.Service;

import AndresFloresRecuperacion.example.demo.Exeption.ExceptionPeliculaDontRegister;
import AndresFloresRecuperacion.example.demo.Models.DTO.PeliculaDTO;
import AndresFloresRecuperacion.example.demo.Repository.PeliculaRepository;
import AndresFloresRecuperacion.example.demo.entities.PeliculaEntity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Service
@CrossOrigin
public class PeliculaService {

    @Autowired
    private PeliculaRepository repo;

    public Page<PeliculaDTO> getAllPeliculas(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<PeliculaEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::ConvertirADTO);
    }

    public PeliculaDTO insert(@Valid PeliculaDTO json) {
        if (json == null){
            throw new IllegalArgumentException("La info es nula");
        }try{
            PeliculaEntity objData = ConvertirAEntity(json);
            PeliculaEntity PeliculaGuardada = repo.save(objData);
            return ConvertirADTO(PeliculaGuardada);
        }catch (Exception e){
            log.error("Error al registrar la Pelicula" + e.getMessage());
            throw new ExceptionPeliculaDontRegister("Pelicula no pudo ser registrada");
        }
    }
    public PeliculaDTO update(Long id, @Valid PeliculaDTO json) {

    }

    private PeliculaEntity ConvertirAEntity(@Valid PeliculaDTO json) {
    }

    private PeliculaDTO ConvertirADTO(PeliculaEntity objEntity) {
    PeliculaDTO dto = new PeliculaDTO();
    dto.

    }


    public boolean delete(Long id) {
    }
}
