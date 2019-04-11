<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/usuarioScript.js"></script>
<link rel="stylesheet" href="css/styleUsuario.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<title>Cadastro de Usu&aacute;rios</title>
</head>
<%
	String user = (String) request.getAttribute("usuario");
%>
<body>
	<!-- Input para teste -->
	<input type="hidden" class="teste" value="<%out.print(user);%>" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<a class="navbar-brand" href="index.jsp">HOME</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item"><a class="nav-link" href="CadastroUsuario.jsp">Usu&aacute;rios
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Fornecedores</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Franquias</a></li>
							<li class="nav-item"><a class="nav-link" href="CadastroProduto.jsp">Produtos</a></li>
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
				<h3>CADASTRO DE USU&Aacute;RIO</h3>
				<!-- 				FORMULARIO DE CADASTRO -->
				<form name="cadUsuario" action="cadastroUsuario"
					method="POST">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="nomeUsuario">Nome Completo</label> <input
									type="text" class="form-control" id="nomeUsuario"
									name="nomeUsuario" placeholder="Digite seu nome...">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="cpfUsuario">CPF</label> <input type="text"
									class="form-control" id="cpfUsuario" name="cpfUsuario"
									placeholder="Digite seu CPF..."
									onKeyPress="MascaraCPF(cadUsuario.cpfUsuario);" maxlength="14">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="emailUsuario">Email</label> <input type="email"
									class="form-control" id="emailUsuario" name="emailUsuario"
									aria-describedby="emailHelp" placeholder="Digite seu e-mail...">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="senhaUsuario">Senha</label> <input type="password"
									class="form-control" id="senhaUsuario" name="senhaUsuario"
									placeholder="Digite sua senha...">
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="telefoneUsuario">Telefone</label> <input type="text"
									class="form-control" id="telefoneUsuario"
									name="telefoneUsuario" placeholder="Digite seu telefone..."
									onKeyPress="MascaraTelefone(cadUsuario.telefoneUsuario);"
									maxlength="14">
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="sexoUsuario">Sexo</label> <select
									class="form-control" id="sexoUsuario" name="sexoUsuario">
									<option value="m">M</option>
									<option value="f">F</option>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="nivelAcessoUsuario">Cargo</label> <select
									class="form-control" id="nivelAcessoUsuario"
									name="nivelAcessoUsuario">
									<option value="1">Gerente</option>
									<option value="2">Pe�o</option>
									
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label for="unidadeAtuacaoUsuario">Unidade de
									Atua&ccedil;&atilde;o </label> <select class="form-control"
									id="unidadeAtuacaoUsuario" name="unidadeAtuacaoUsuario">
									<option value="1">UnidadeTeste1</option>
									<option value="2">UnidadeTeste2</option>
									
								</select>
							</div>
						</div>
						<div class="col-md-12">
							<button type="submit" class="btn btn-success">Cadastrar</button>
						</div>
					</div>
				</form>
			</div>
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
							<span>�� </span><span class="copyright-year">2019</span><span>�</span><span>BarTades</span><span>.�</span><span>All
								Rights Reserved.</span>
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
							<a href="mailto:#">dkstudioin@gmail.com</a>
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
						<li><a href="#">Usu&aacute;rios </a></li>
						<li><a href="#">Fornecedores</a></li>
						<li><a href="#">Franquias</a></li>
						<li><a href="#">Produtos</a></li>
						<li><a href="#">Contate-nos</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row no-gutters social-container">
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-facebook"></span><span>Facebook</span></a>
			</div>
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-instagram"></span><span>instagram</span></a>
			</div>
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-twitter"></span><span>twitter</span></a>
			</div>
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-youtube-play"></span><span>google</span></a>
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