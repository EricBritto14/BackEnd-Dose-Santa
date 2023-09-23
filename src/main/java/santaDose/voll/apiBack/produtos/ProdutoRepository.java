package santaDose.voll.apiBack.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

//Crianbdo uma interface para ser possível salvar e pegar os valores para colocar no banco de dados
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
