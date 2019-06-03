create database bartades;

use bartades;

create table fornecedores(
id int not null auto_increment,
nome varchar(150),
cnpj varchar (18),
telefone varchar(14),
endereco varchar(200),
numero varchar(12),
complemento varchar(50),
cep varchar(9),
bairro varchar(50),
cidade varchar(50),
estado varchar(3),
enabled boolean,
primary key (id));

create table usuarios(
id int not null auto_increment,
nome varchar(255),
email varchar(255),
telefone varchar(255),
cpf varchar(18),
sexo varchar(1),
senha varchar(30),
unidade_atuacao int(3),
cargo int(3),
primary key (id));

create table funcoes(
id int not null auto_increment,
nome varchar(255),
nivel_acesso int(3),
primary key (id));

create table pedidos(
id int not null auto_increment,
cliente_nome varchar(255),
unidade_venda int(3),
tipo_pagamento varchar(255),
primary key (id));

create table produtos_pedidos(
id int not null auto_increment,
id_pedido int,
id_produto int,
quantidade int,
primary key (id));

create table unidades(
id int not null auto_increment,
nome varchar(150),
endereco varchar(200),
numero varchar(12),
complemento varchar(50),
cep varchar(9),
bairro varchar(50),
cidade varchar(50),
estado varchar(3),
enabled boolean,
primary key (id));

create table produtos(
id int not null auto_increment,
nome varchar(255),
descricao varchar(255),
categoria int (3),
preco_venda numeric(65, 2),
preco_compra numeric(65,2),
id_fornecedor int,
quantidade_disponivel int (4),
disponibilidade boolean,
id_franquia int(4),
primary key(id),
foreign key (id_fornecedor) references fornecedores(id));

create table categoria (
id int not null auto_increment,
nome varchar(100),
primary key (id));

insert into categoria (nome) values ('Bebidas');
insert into categoria (nome) values ('Porções');
insert into categoria (nome) values ('Aperitivos');

create table estado (
id int not null auto_increment,
nome varchar(100),
primary key (id));

insert into estado (nome) values ('AC');
insert into estado (nome) values ('AL');
insert into estado (nome) values ('AP');
insert into estado (nome) values ('AM');
insert into estado (nome) values ('BA');
insert into estado (nome) values ('CE');
insert into estado (nome) values ('DF');
insert into estado (nome) values ('ES');
insert into estado (nome) values ('GO');
insert into estado (nome) values ('MA');
insert into estado (nome) values ('MT');
insert into estado (nome) values ('MS');
insert into estado (nome) values ('MG');
insert into estado (nome) values ('PA');
insert into estado (nome) values ('PB');
insert into estado (nome) values ('PR');
insert into estado (nome) values ('PE');
insert into estado (nome) values ('PI');
insert into estado (nome) values ('RJ');
insert into estado (nome) values ('RN');
insert into estado (nome) values ('RS');
insert into estado (nome) values ('RO');
insert into estado (nome) values ('RR');
insert into estado (nome) values ('SC');
insert into estado (nome) values ('SP');
insert into estado (nome) values ('SE');
insert into estado (nome) values ('TO');

create table pedidos_compra(
id int auto_increment not null,
qtde_produtos int (2),
valor_total_compra double,
data_pedido date,
usuario_pedido int,
unidade_pedido int,
primary key (id));

create table produtos_pedidos_compra (
id int auto_increment not null,
id_pedido int,
id_produto int,
quantidade int,
valor_total double,
primary key (id));

insert into fornecedores (nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) values ('Fornecedor Bonzão', '9812662000130','11960940481','Rua Vicente Decara Neto', '77', '-', '05819000', 'Santa José Fina', 'São Paulo', 'SP', 1);

insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Coca Cola Lata','Refrigerante',1,5,3,1,100,1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Fanta Laranja Lata','Refrigerante','1','4','2','1','100',1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Fanta Uva Lata','Refrigerante','1','4','2','1','100',1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Frango','Fritas','2','12','8','1','100',1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Batata Frita','Fritas','2','13','9','1','100',1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Polenta','Fritas','2','15','10','1','100',1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Calabresa','Carne','3','20','8','1','100',1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Queijo','Frios','3','18','9','1','100',1,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade, id_franquia) values ('Presunto','Frios','3','17','10','1','100',1,1);



insert into funcoes (nome, nivel_acesso) values ('Analista', 1); -- pode somente visualizar fornecedores, pedidos e produtos da sua região
insert into funcoes (nome, nivel_acesso) values ('Analista/estoquista regional', 2); -- pode criar pedidos de compra de produto, criar e editar produtos, visualizar e criar fornecedores para sua unidade
insert into funcoes (nome, nivel_acesso) values ('Analista/estoquista/vendedor regional', 3); -- pode fazer tudo acima e criar pedidos de venda para sua unidade
insert into funcoes (nome, nivel_acesso) values ('Gerente regional', 4); -- tem acesso irrestrito a sua unidade
insert into funcoes (nome, nivel_acesso) values ('Diretor', 5); -- tem acesso irrestrito a todas as unidades
insert into funcoes (nome, nivel_acesso) values ('Administrador de sistema', 6); -- tem acesso full, podendo criar usuarios



insert into unidades (nome, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) values ('unidade1', 'a' , 'a', '12', '13', '14', 'sao paulo', '1', 1);
insert into unidades (nome, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) values ('unidade2', 'a' , 'a', '12', '13', '14', 'sao paulo', '2', 1);
insert into unidades (nome, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) values ('unidade3', 'a' , 'a', '12', '13', '14', 'sao paulo', '3', 1);


insert into usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values ('Vitor Analista1', 'a1@bartades.com', '11960940481', '437', 'M', 'a1', 1,1);
insert into usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values ('Eduardo Analista2', 'a2@bartades.com', '11960940482', '438', 'M', 'a2', 1,2);
insert into usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values ('Victor Analista3', 'a3@bartades.com', '11960940483', '439', 'M', 'a3', 1,3);
insert into usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values ('Antonio Analista4', 'a4@bartades.com', '11960940484', '440', 'M', 'a4', 1,4);
insert into usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values ('Renan diretor', 'a5@bartades.com', '11960940485', '441', 'M', 'a5', 1,5);
insert into usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values ('Adm', 'adm@bartades.com', '11960940486', '442', 'M', 'adm', 1,6);


