create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    messagem text not null,
    dataCriacao timestamp not null,
    autor varchar(25) not null,
    curso varchar(50) not null,
    status_topico varchar(20) not null,
    idUsuario bigint not null,
    primary key(id)
);