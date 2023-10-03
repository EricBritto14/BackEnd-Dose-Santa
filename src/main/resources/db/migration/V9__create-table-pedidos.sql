create table pedidos(

    id bigint not null auto_increment,
    produto_id bigint not null,
    variedade_id bigint not null,
    cliente_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_pedidos_produto_id foreign key(produto_id) references produtos(id),
    constraint fk_pedidos_variedade_id foreign key(variedade_id) references variedades(id),
    constraint fk_pedidos_cliente_id foreign key(cliente_id) references clientes(id)

);