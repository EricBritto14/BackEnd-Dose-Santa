package santaDose.voll.apiBack.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import santaDose.voll.apiBack.produtos.*;

import java.util.List;

//Como é uma API Rest, para mostrar para a classe e para a main que está classe é um controller, no começo dela usar @RestController
//@RequestMapping para dizer qual mapeamento este controller vai responder, sempre que digitar a url /hello vai aparecer este controller
@RestController
@RequestMapping("produtos")
public class ProdutosController {
    @Autowired //Autowired para mostrar pro SpringBoot que seria este o atributo responsavel por passar a interface na classe do controller
    private ProdutoRepository repositorioProdutos;
    //Criando uma interface para ser possível salvar e pegar os valores para colocar no banco de dados

    //O PostMapping funciona para caso haver algum requisito tentando fazer um post no /Produtos, chamar este metodo
    @PostMapping
    @Transactional //Transactional vem do Spring, pois este método é um de escrita para fazer um insert no banco de dados
    //@Valid para validar as mudanças de requisição do beenvalidation que colocamos no CadastroProdutos como o @NotBlank
    public void cadastrarProdutos(@RequestBody @Valid DadosCadastroProdutos dados){ //@RequestBody para mostrar que queremos pegar o valor do json da requisição que estamos recebendo
        repositorioProdutos.save(new Produto(dados));
    }

    //Metodo para listagem, não pode ser void pois fará um retorno para um usuário
    //Não precisa do @Transactional pois não esta criando nem escrevendo no banco de dados, apenas lendo e listando
    @GetMapping
    public List<DadosListagemProdutos> listar(){
        return repositorioProdutos.findAll().stream().map(DadosListagemProdutos::new).toList();
        //Se for retornar tudo sem frescura do que ta vindo, o codigo poderia acabar no repositorioProdutos.findAll()
        //Se quiser escolher dos dados, o que retornar, vai ter que criar um record de listagem, depois criar um construtor, e por fim usar essa linha que eu fiz.

    }

}
