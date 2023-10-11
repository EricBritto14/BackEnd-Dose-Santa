package santaDose.voll.apiBack.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import santaDose.voll.apiBack.clientes.*;


import java.util.List;

//Como é uma API Rest, para mostrar para a classe e para a main que está classe é um controller, no começo dela usar @RestController
//@RequestMapping para dizer qual mapeamento este controller vai responder, sempre que digitar a url /hello vai aparecer este controller
@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClientesController {
    @Autowired //Autowired para mostrar pro SpringBoot que seria este o atributo responsavel por passar a interface na classe do controller
    private ClientesRepository repositorioClientes;
    //Criando uma interface para ser possível salvar e pegar os valores para colocar no banco de dados

    //O PostMapping funciona para caso haver algum requisito tentando fazer um post no /Produtos, chamar este metodo
    @PostMapping
    @Transactional //Transactional vem do Spring, pois este método é um de escrita para fazer um insert no banco de dados
    //@Valid para validar as mudanças de requisição do beenvalidation que colocamos no CadastroProdutos como o @NotBlank
    public ResponseEntity cadastrarClientes(@RequestBody @Valid DadosCadastroClientes dados, UriComponentsBuilder uriBuilder){ //@RequestBody para mostrar que queremos pegar o valor do json da requisição que estamos recebendo
        var cli = new Cliente(dados);
        repositorioClientes.save(cli);

        //Fazendo tudo isso para na hora do retorno retornar o codigo 201, devolver o cabeçalho location com a URI, e devolver no corpo da resposta uma representação do recurso criado
        //Fazendo isso apenas por boa pratica.
        //Fazendo uma variavel para retornar o id do medico que foi criado, e os valores no dto
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cli.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cli));//new DadosDetalhamentoProduto(produtos) DTO criado para retornar no atualizar, não retornar uma JPA
    }

    //Metodo para listagem, não pode ser void pois fará um retorno para um usuário
    //Não precisa do @Transactional pois não esta criando nem escrevendo no banco de dados, apenas lendo e listando
    @GetMapping
    public ResponseEntity<Page<DadosListagemClientes>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){ //Pageable para colocar a listagem dos itens em ordem alfabetica
        var page = repositorioClientes.findAllByAtivoTrue(paginacao).map(DadosListagemClientes::new);
        return ResponseEntity.ok(page); //Para retornar o codigo 200 com Ok e a listagem do findAll
        //Se for retornar tudo sem frescura do que ta vindo, o codigo poderia acabar no repositorioProdutos.findAll()
        //Se quiser escolher dos dados, o que retornar, vai ter que criar um record de listagem, depois criar um construtor, e por fim usar essa linha que eu fiz.

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        //repositorioProdutos.deleteById(id) para excluir tdo de vez do banco
        //Do jeito que tá, esta tornando apenas o produto como inativo
        var cliente = repositorioClientes.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente)); //ResponseEntity para retornar codigo 204 ao excluir algum conteudo, boa pratica.

    }


    //PutMapping seria para a parte de atualização, para mapear aonde a att vai
    //Tranasactional desta vez será usada também pois vai reescrever algo no banco de dados, então tem que ser usado
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoClientes dados){
        var clientes = repositorioClientes.getReferenceById(dados.id());
        clientes.atualizarInformacoesClientes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(clientes));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        //repositorioProdutos.deleteById(id) para excluir tdo de vez do banco
        //Do jeito que tá, esta tornando apenas o produto como inativo
        var clientes = repositorioClientes.getReferenceById(id);
        clientes.excluir();

        return ResponseEntity.noContent().build(); //ResponseEntity para retornar codigo 204 ao excluir algum conteudo, boa pratica.

    }

}

