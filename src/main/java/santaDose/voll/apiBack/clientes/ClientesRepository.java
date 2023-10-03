package santaDose.voll.apiBack.clientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import santaDose.voll.apiBack.produtos.Produto;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAllByAtivoTrue(Pageable ordenacao);

}
