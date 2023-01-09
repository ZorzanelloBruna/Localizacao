create table tb_cidade(
 id_cidade bigint not null primary key,
 nome varchar(50) not null,
 habitantes bigint
);

insert into tb_cidade (id_cidade, nome, habitantes)
values(1, "Chuvisqueiro", 500),
    (2, "Estância Velha", 50672);