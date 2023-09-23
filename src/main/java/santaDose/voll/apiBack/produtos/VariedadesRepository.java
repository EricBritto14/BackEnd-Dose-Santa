package santaDose.voll.apiBack.produtos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VariedadesRepository extends JpaRepository<Variedades, Long> {
    Page<Variedades> findAllByAtivoTrue(Pageable ordenacaoV);
}
