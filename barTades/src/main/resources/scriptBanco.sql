create database bartades;

use bartades;

create table produtos(
id int not null auto_increment,
nome varchar(255),
descricao varchar(255),
categoria varchar(255),
preco_venda numeric(65, 2),
preco_compra numeric(65,2),
id_fornecedor int,
primary key(id),
foreign key (id_fornecedor) references fornecedores(id));

create table fornecedores(
id int not null auto_increment,
nome varchar(255),
cnpj varchar (14),
telefone varchar(13),
endereco varchar(255),
primary key (id));

create table usuarios(
id int not null auto_increment,
nome varchar(255),
email varchar(255),
telefone varchar(255),
cpf varchar(15),
sexo varchar(1),
senha varchar(30),
nivel_acesso int(3),
unidade_atuacao int,
primary key (id));

create table funcoes(
id int not null auto_increment,
nome varchar(255),
nivel_acesso int(3),
primary key (id));

create table pedidos(
id int not null auto_increment,
comanda varchar(15),
cliente_nome varchar(255),
cliente_cpf varchar(15),
acrescimo numeric(65,2),
desconto numeric(65,2),
unidade_venda int(3),
primary key (id));

create table produtos_pedidos(
id int not null auto_increment,
id_pedido int,
id_produto int,
quantidade int,
primary key (id));

create table unidades(
id int not null auto_increment,
nome varchar(255),
estado varchar(2),
endereco varchar(255),
primary key (id));

