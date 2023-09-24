package santaDose.voll.apiBack.produtosVariedade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariedadesRepository extends JpaRepository<Variedades, Long> {
    Page<Variedades> findAllByAtivoTrue(Pageable ordenacaoV);
}
