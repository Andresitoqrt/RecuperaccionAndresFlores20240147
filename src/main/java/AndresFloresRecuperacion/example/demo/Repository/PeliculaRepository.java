package AndresFloresRecuperacion.example.demo.Repository;

import AndresFloresRecuperacion.example.demo.entities.PeliculaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {
    Page<PeliculaEntity> findAll(Pageable pageable);
}
