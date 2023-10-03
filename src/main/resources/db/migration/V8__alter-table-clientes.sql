 alter table clientes add column ativo tinyint;
    update clientes set ativo = 1;
    alter table clientes modify ativo tinyint not null;

