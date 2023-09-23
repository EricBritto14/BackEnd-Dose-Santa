package santaDose.voll.apiBack.produtos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Crianbdo uma interface para ser possível salvar e pegar os valores para colocar no banco de dados
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findAllByAtivoTrue(Pageable ordenacao);
}
