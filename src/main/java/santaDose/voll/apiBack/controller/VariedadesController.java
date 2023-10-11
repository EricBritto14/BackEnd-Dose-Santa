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
import santaDose.voll.apiBack.produtosVariedade.*;

@RestController
@RequestMapping("variedades")
@SecurityRequirement(name = "bearer-key")
public class VariedadesController {
    @Autowired //Autowired para mostrar pro SpringBoot que seria este o atributo responsavel por passar a interface na classe do controller
    private VariedadesRepository repositorioVariedades;

    @PostMapping
    @Transactional
    //Transactional vem do Spring, pois este método é um de escrita para fazer um insert no banco de dados
    //@Valid para validar as mudanças de requisição do beenvalidation que colocamos no CadastroProdutos como o @NotBlank
    public ResponseEntity cadastrarProdutos(@RequestBody @Valid DadosCadastroVariedades dadosVariedade, UriComponentsBuilder uriBuilder){ //@RequestBody para mostrar que queremos pegar o valor do json da requisição que estamos recebendo
        var variedadesP = new Variedades(dadosVariedade);
        repositorioVariedades.save(variedadesP);

        //Fazendo tudo isso para na hora do retorno retornar o codigo 201, devolver o cabeçalho location com a URI, e devolver no corpo da resposta uma representação do recurso criado
        //Fazendo isso apenas por boa pratica.
        //Fazendo uma variavel para retornar o id do medico que foi criado, e os valores no dto
        var uri = uriBuilder.path("/variedades/{id}").buildAndExpand(variedadesP.getId()).toUri();
        return ResponseEntity.created(uri).body(new Variedades(dadosVariedade));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemVariedades>> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable ordenacaoV){ //Pageable para colocar a listagem dos itens em ordem alfabetica
        var listar = repositorioVariedades.findAllByAtivoTrue(ordenacaoV).map(DadosListagemVariedades::new);
        return ResponseEntity.ok(listar);
        //Se for retornar tudo sem frescura do que ta vindo, o codigo poderia acabar no repositorioProdutos.findAll()
        //Se quiser escolher dos dados, o que retornar, vai ter que criar um record de listagem, depois criar um construtor, e por fim usar essa linha que eu fiz.

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        //repositorioProdutos.deleteById(id) para excluir tdo de vez do banco
        //Do jeito que tá, esta tornando apenas o produto como inativo
        var variedadesP = repositorioVariedades.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoVariedade(variedadesP)); //ResponseEntity para retornar codigo 204 ao excluir algum conteudo, boa pratica.

    }

    //PutMapping seria para a parte de atualização, para mapear aonde a att vai
    //Tranasactional desta vez será usada também pois vai reescrever algo no banco de dados, então tem que ser usado
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoVariedades dadosVariedade){
        var variedade = repositorioVariedades.getReferenceById(dadosVariedade.id());
        variedade.atualizarInformacoesVariedades(dadosVariedade);

        return ResponseEntity.ok(new DadosDetalhamentoVariedade(variedade)); //new DadosDetalhamentoProduto(produtos) DTO criado para retornar no atualizar, não retornar uma JPA
    }

    //@DeleteMapping com o ID, está dizendo que vai seguir e deletar o qual ID for informado
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        //repositorioProdutos.deleteById(id) para excluir tdo de vez do banco
        //Do jeito que tá, esta tornando apenas o produto como inativo
        var produtosVariadade = repositorioVariedades.getReferenceById(id);
        produtosVariadade.excluir();

        return ResponseEntity.noContent().build(); //ResponseEntity para retornar codigo 204 ao excluir algum conteudo, boa pratica.

    }
}
