create table usuario(
	id bigint primary key not null auto_increment,
    nome varchar(200) not null,
	email varchar(200) not null unique,
    senha varchar(200) not null
);