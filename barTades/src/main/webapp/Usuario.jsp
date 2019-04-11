<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/produtoScript.js"></script>
<link rel="stylesheet" href="css/styleUsuario.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<title>Usuarios</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<a class="navbar-brand" href="#">HOME</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> Usu&aacute;rios </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="cadastroUsuarios">Cadastro</a> <a
										class="dropdown-item" href="visualizarUsuarios">Visualizar</a>
								</div></li>
							<li class="nav-item"><a class="nav-link" href="#">Fornecedores</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Franquias</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> Produtos </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="cadastroProduto">Cadastro</a> <a
										class="dropdown-item" href="visualizarProdutos">Visualizar</a>
								</div></li>
							<li class="nav-item"><a class="nav-link" href="#">Contate-nos</a></li>
						</ul>
						<form class="form-inline my-2 my-lg-0">
							<input class="form-control mr-sm-2" type="search"
								placeholder="Buscar" aria-label="Search">
							<button class="btn btn-outline-success my-2 my-sm-0"
								type="submit">Buscar</button>
						</form>
					</div>
				</nav>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h3>USU&Aacute;RIOS</h3>
			</div>


			<table class="table table-hover" action="visualizarUsuarios">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Nome</th>
						<th scope="col">CPF</th>
						<th scope="col">E-mail</th>
						<th scope="col">Telefone</th>
						<th scope="col">Sexo</th>
						<th scope="col">Unidade de Atua&ccedil;&atilde;o</th>
						<th scope="col">Cargo</th>
						<th scope="col">Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuarios" items="${listaDeUsuarios}">
						<p>${listaDeUsuarios}</p>
						<tr>
							<th scope="row">${usuarios['cpf']}</th>
							<td>${usuarios['nome']}</td>
							<td>${usuarios['email']}</td>
							<td>${usuarios['telefone']}</td>
							<td>${usuarios['sexo']}</td>
							<td>${usuarios['unidadeAtuacao']}</td>
							<td>${usuarios['cargo']}</td>
							<td><a href="editarProduto?idProduto=${usuarios['cpf']}"><button
										type="button" class="btn btn-outline-dark">Editar</button></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
		</div>
	</div>
	<br>
	<footer class="section footer-classic context-dark bg-image"
		style="background: #2d3246;">
		<br>
		<div class="container">
			<div class="row row-30">
				<div class="col-md-4 col-xl-5">
					<div class="pr-xl-4">
						<a class="brand" href="index.html"><img
							src="https://img.icons8.com/color/50/000000/bar.png"></a>
						<p>N&oacute;s somos uma empresa focada no melhor atendimento e
							experi&ecirc;ncia do nosso cliente.</p>
						<!-- Rights-->
						<p class="rights">
							<span>&copy;</span><span class="copyright-year">2019</span><span></span><span>
								BarTades</span><span> All Rights Reserved.</span>
						</p>
					</div>
				</div>
				<div class="col-md-4">
					<dl class="contact-list">
						<dt>Endere&ccedil;o:</dt>
						<dd>798 South Park Avenue, Jaipur, Raj</dd>
					</dl>
					<dl class="contact-list">
						<dt>E-mail:</dt>
						<dd>
							<a href="mailto:#">bartades@contato.com</a>
						</dd>
					</dl>
					<dl class="contact-list">
						<dt>Telefones:</dt>
						<dd>
							<a href="tel:#">+91 7568543012</a> <span>or</span> <a
								href="tel:#">+91 9571195353</a>
						</dd>
					</dl>
				</div>
				<div class="col-md-4 col-xl-3">
					<ul class="nav-list">
						<li><a href="Usuario.jsp">Usu&aacute;rios </a></li>
						<li><a href="#">Fornecedores</a></li>
						<li><a href="#">Franquias</a></li>
						<li><a href="#">Produtos</a></li>
						<li><a href="#">Contate-nos</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

</body>
</html>