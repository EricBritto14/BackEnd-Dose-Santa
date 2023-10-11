package santaDose.voll.apiBack.pedidos;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santaDose.voll.apiBack.clientes.ClientesRepository;
import santaDose.voll.apiBack.pedidos.cancelamento.ValidadorCancelamentoPedido;
import santaDose.voll.apiBack.pedidos.validacoes.ValidadorPedidoProduto;
import santaDose.voll.apiBack.produtos.ProdutoRepository;
import santaDose.voll.apiBack.produtosVariedade.VariedadesRepository;

import java.util.List;


@Service //Anotação para o Spring entender que esta é uma classe de serviço
public class AgendaDePedidos {

    @Autowired //AutoWired para pedir pra ele injetar o produto neste objeto
    private PedidosRepository pedidosRepository;

    @Autowired //AutoWired para pedir pra ele injetar o produto neste objeto
    private ProdutoRepository produtoRepository;

    @Autowired //AutoWired para pedir pra ele injetar o produto neste objeto
    private VariedadesRepository variedadesRepository;

    @Autowired //AutoWired para pedir pra ele injetar o produto neste objeto
    private ClientesRepository clientesRepository;

    @Autowired
    private List<ValidadorPedidoProduto> validadores;

    @Autowired
    private List<ValidadorCancelamentoPedido> validadorCancelamento;


    //Metodo para pedido de produto
    public DadosDetalhamentoPedido pedir(DadosPedidoProdutos dados){
        if (!clientesRepository.existsById(dados.idPedinte())){ //O Repository que faz essa checagem no banco de dados
            throw new ValidationException("Id do cliente informado não existe!");

        }

        if(dados.idProduto() != null & !produtoRepository.existsById(dados.idProduto())){ //Nesta linha, ele checka para ver se está chegando algum id de produto, se sim, ele verifica se existe no banco de dados para não passar um id inexistente, ou se ele não quiser um produto e sim uma variedade, não colocar nada
            throw new ValidationException("Id do produto não existe!");
        }

        if(dados.idVariedades() != null & !variedadesRepository.existsById(dados.idVariedades())){ //Nesta linha, ele checka para ver se está chegando algum id de produto, se sim, ele verifica se existe no banco de dados para não passar um id inexistente, ou se ele não quiser um produto e sim uma variedade, não colocar nada
            throw new ValidationException("Id da variedade não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        //Validações

        var cliente = clientesRepository.findById(dados.idPedinte()).get();
        var produto = produtoRepository.findById(dados.idProduto()).get();
        var variedades = variedadesRepository.findById(dados.idVariedades()).get();
       // var quantidade = pedidosRepository.findById(dados.quantidade());
        var pedido = new Pedido(null, produto, variedades, cliente, dados.data(), null);

        pedidosRepository.save(pedido);

        return new DadosDetalhamentoPedido(pedido);

    }

    //Metodo para cancelamento do produto
    public void cancelar(DadosCancelamentoProdutos dados){
        if (!clientesRepository.existsById(dados.idPedinte())){ //O Repository que faz essa checagem no banco de dados
            throw new ValidationException("Id do cliente informado não existe!");
        }

        if(dados.idProduto() != null & !produtoRepository.existsById(dados.idProduto())){ //Nesta linha, ele checka para ver se está chegando algum id de produto, se sim, ele verifica se existe no banco de dados para não passar um id inexistente, ou se ele não quiser um produto e sim uma variedade, não colocar nada
            throw new ValidationException("Id do produto não existe!");
        }

        if(dados.idVariedades() != null & !variedadesRepository.existsById(dados.idVariedades())){ //Nesta linha, ele checka para ver se está chegando algum id de produto, se sim, ele verifica se existe no banco de dados para não passar um id inexistente, ou se ele não quiser um produto e sim uma variedade, não colocar nada
            throw new ValidationException("Id da variedade não existe!");
        }

        validadorCancelamento.forEach(v -> v.validar(dados));

        var pedido = pedidosRepository.getReferenceById(dados.idPedinte());
        pedido.cancelar(dados.motivo());

    }
}
