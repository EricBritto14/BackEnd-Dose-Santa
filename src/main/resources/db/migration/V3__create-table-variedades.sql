create table variedades(

    id bigint not null auto_increment,
    tipo varchar(100) not null,
    nome varchar(100) not null,
    valor varchar(100) not null,
    datavalidade varchar(100) not null,
    datacadastro varchar(100) not null,
    quantidade varchar(100) not null,

    primary key(id)

);
