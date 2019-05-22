create database bartades;

use bartades;

create table fornecedores(
id int not null auto_increment,
nome varchar(255),
cnpj varchar (18),
telefone varchar(13),
endereco varchar(255),
numero varchar(12),
complemento varchar(50),
cep varchar(9),
bairro varchar(50),
cidade varchar(50),
estado varchar(50),
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

insert into estado (nome) values ('Acre');
insert into estado (nome) values ('Alagoas');
insert into estado (nome) values ('Amapá');
insert into estado (nome) values ('Amazonas');
insert into estado (nome) values ('Bahia');
insert into estado (nome) values ('Ceará');
insert into estado (nome) values ('Distrito Federal');
insert into estado (nome) values ('Espírito Santo');
insert into estado (nome) values ('Goiás');
insert into estado (nome) values ('Maranhão');
insert into estado (nome) values ('Mato Grosso');
insert into estado (nome) values ('Mato Grosso do Sul');
insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('Pará');
insert into estado (nome) values ('Paraíba');
insert into estado (nome) values ('Paraná');
insert into estado (nome) values ('Pernambuco');
insert into estado (nome) values ('Piauí');
insert into estado (nome) values ('Rio de Janeiro');
insert into estado (nome) values ('Rio Grande do Norte');
insert into estado (nome) values ('Rio Grande do Sul');
insert into estado (nome) values ('Rondônia');
insert into estado (nome) values ('Roraima');
insert into estado (nome) values ('Santa Catarina');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Sergipe');
insert into estado (nome) values ('Tocantins');

create table pedidos_compra(
id int auto_increment not null,
qtde_produtos int (2),
valor_total_compra double,
primary key (id));

create table produtos_pedidos_compra (
id int auto_increment not null,
id_pedido int,
id_produto int,
quantidade int,
valor_total double,
primary key (id));

insert into fornecedores (nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) values ('Fornecedor Bonzão', '9812662000130','11960940481','Rua Vicente Decara Neto', '77', '-', '05819000', 'Santa José Fina', 'São Paulo', 'SP', 1);

insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Coca Cola Lata','Refrigerante',1,5,3,1,100,1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Fanta Laranja Lata','Refrigerante','1','4','2','1','100',1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Fanta Uva Lata','Refrigerante','1','4','2','1','100',1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Frango','Fritas','2','12','8','1','100',1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Batata Frita','Fritas','2','13','9','1','100',1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Polenta','Fritas','2','15','10','1','100',1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Calabresa','Carne','3','20','8','1','100',1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Queijo','Frios','3','18','9','1','100',1);
insert into produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, quantidade_disponivel, disponibilidade) values ('Presunto','Frios','3','17','10','1','100',1);

insert into funcoes (nome, nivel_acesso) values ('Diretor',1);
insert into funcoes (nome, nivel_acesso) values ('Gerente',2);

insert into unidades (nome, estado, endereco) values ('UND SP', 'SP', 'x');
insert into unidades (nome, estado, endereco) values ('UND RJ', 'RJ', 'x');

insert into usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values ('Vitor', 'admin@admin.com', '11960940481', '437', 'M', '123456', 1,1);