package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//extendemos la interface desde JPA indicando la entidad y el tipo de dato del ID
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //genero metodo por defecto
    Page<Medico> findAllByActivoTrue(Pageable paginacion);
}