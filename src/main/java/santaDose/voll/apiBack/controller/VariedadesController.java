package santaDose.voll.apiBack.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import santaDose.voll.apiBack.produtos.*;

@RestController
@RequestMapping("variedades")
public class VariedadesController {
    @Autowired //Autowired para mostrar pro SpringBoot que seria este o atributo responsavel por passar a interface na classe do controller
    private VariedadesRepository repositorioVariedades;

    @PostMapping
    @Transactional
    //Transactional vem do Spring, pois este método é um de escrita para fazer um insert no banco de dados
    //@Valid para validar as mudanças de requisição do beenvalidation que colocamos no CadastroProdutos como o @NotBlank
    public void cadastrarProdutos(@RequestBody @Valid DadosCadastroVariedades dadosVariedade){ //@RequestBody para mostrar que queremos pegar o valor do json da requisição que estamos recebendo
        repositorioVariedades.save(new Variedades(dadosVariedade));
    }
    @GetMapping
    public Page<DadosListagemVariedades> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable ordenacaoV){ //Pageable para colocar a listagem dos itens em ordem alfabetica
        return repositorioVariedades.findAllByAtivoTrue(ordenacaoV).map(DadosListagemVariedades::new);
        //Se for retornar tudo sem frescura do que ta vindo, o codigo poderia acabar no repositorioProdutos.findAll()
        //Se quiser escolher dos dados, o que retornar, vai ter que criar um record de listagem, depois criar um construtor, e por fim usar essa linha que eu fiz.

    }

    //PutMapping seria para a parte de atualização, para mapear aonde a att vai
    //Tranasactional desta vez será usada também pois vai reescrever algo no banco de dados, então tem que ser usado
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoVariedades dadosVariedade){
        var variedade = repositorioVariedades.getReferenceById(dadosVariedade.id());
        variedade.atualizarInformacoesVariedades(dadosVariedade);
    }

    //@DeleteMapping com o ID, está dizendo que vai seguir e deletar o qual ID for informado
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        //repositorioProdutos.deleteById(id) para excluir tdo de vez do banco
        //Do jeito que tá, esta tornando apenas o produto como inativo
        var produtosVariadade = repositorioVariedades.getReferenceById(id);
        produtosVariadade.excluir();

    }
}
