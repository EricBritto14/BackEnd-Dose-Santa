package santaDose.voll.apiBack.pedidos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import santaDose.voll.apiBack.clientes.Cliente;
import santaDose.voll.apiBack.clientes.Endereco;
import santaDose.voll.apiBack.produtos.Produto;
import santaDose.voll.apiBack.produtosVariedade.Variedades;

import java.time.LocalDateTime;
import java.util.Optional;

//Classe JPA
@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variedade_id")
    private Variedades variedades;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //Tem que criar aqui se pá a quantidade e forma de pagamento

    private LocalDateTime data;

    /*
    //Se pá para dar certo quantidade e forma de pagamento, vai ter que criar outra classe fora dessa, e nesta de pedido apenas puxar pra cá
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quantidade")
    private Pedido quantidade;
        */

    private MotivoCancelamento motivo_cancelamento;


    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    public void cancelar(MotivoCancelamento motivo) {
        this.motivo_cancelamento = motivo;
    }

}
