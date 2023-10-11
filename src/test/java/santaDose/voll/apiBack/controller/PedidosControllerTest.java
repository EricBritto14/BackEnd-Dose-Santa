package santaDose.voll.apiBack.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import santaDose.voll.apiBack.pedidos.AgendaDePedidos;
import santaDose.voll.apiBack.pedidos.DadosDetalhamentoPedido;
import santaDose.voll.apiBack.pedidos.DadosPedidoProdutos;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PedidosControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosPedidoProdutos> dadosPedidoProdutosJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoPedido> dadosDetalhamentoConsultaJacksonTester;

    @MockBean
    private AgendaDePedidos pedidos; //Linha para dizer ao banco de dados que não é para ele ser acessado, e sim criar um local só para teste, não usar o verdadeiro

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estiver invalidasa")
            @WithMockUser //Anotação para falar pro Spring security que não é necessário o token para validar essa requisição, pois é uma requisição de teste e só quero testar o código do erro, não ter que logar e tudo
    //Classe de teste
    //Teste para validar se está retornando realmente o erro 400 ao fazer uma bad request faltando algumas informações
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/pedidos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão validas")
    @WithMockUser //Anotação para falar pro Spring security que não é necessário o token para validar essa requisição, pois é uma requisição de teste e só quero testar o código do erro, não ter que logar e tudo
        //Classe de teste
        //Teste para validar se está retornando realmente o codigo 200 para o cadastro.
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var dadosPedido = new DadosDetalhamentoPedido(null, 2l, 5l, data);

        when(pedidos.pedir(any())).thenReturn(dadosPedido );

        var response = mvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosPedidoProdutosJacksonTester.write(
                                new DadosPedidoProdutos(2l, 5l, 20l, data)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJacksonTester.write(
               dadosPedido
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

}