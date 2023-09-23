package santaDose.voll.apiBack.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santaDose.voll.apiBack.produtos.DadosCadastroVariedades;
import santaDose.voll.apiBack.produtos.Produto;
import santaDose.voll.apiBack.produtos.Variedades;
import santaDose.voll.apiBack.produtos.VariedadesRepository;

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
}
