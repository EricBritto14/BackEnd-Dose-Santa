package santaDose.voll.apiBack.pedidos.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santaDose.voll.apiBack.pedidos.DadosPedidoProdutos;
import santaDose.voll.apiBack.pedidos.Pedido;
import santaDose.voll.apiBack.produtos.ProdutoRepository;

@Component
public class ValidadorProdutoAtivo implements ValidadorPedidoProduto {

    @Autowired
    private ProdutoRepository produtoRepository;


    public void validar(DadosPedidoProdutos dados) {
        //Escolha do produto opcional
        if (dados.idProduto() == null) {
            return;
        }
        if (dados.idVariedades() == null) {
            return;
        }

        var produtoEstaAtivo = produtoRepository.findAtivoById(dados.idProduto());
        if (!produtoEstaAtivo) {
            throw new ValidationException("Pedido não pode se realizado");

        }
    }
}
