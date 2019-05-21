<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script src="js/produtoScript.js"></script>
<link rel="stylesheet" href="css/styleUsuario.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!--  <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	-->



<script>
//metodo dinamico

$(document).ready(function(){
	$('#maxProdutos').hide();
})

var testeTar = '';

$(document).ready(function(){
	$('#compraProduto').on('click', function(e){
		console.log('elemento: ', e.target);
			testeTar = e.target.id.substring(e.target.id.length-1, e.target.id.length);
	})
})

$(document).ready(function(){
	$('#compraProduto').on('click', function(){
		console.log('id da div: ', testeTar);
	})
})


var qtdProdutos = 1;

function returnQtdeProdutos(){
	return qtdProdutos -1;
}

function returnQtdeTotalProdutos(){
	return qtdProdutos;
}
//pega a listagem de produtos dinamica
	
	$(document).ready(function() {
			$('select[name=categoriaProduto0]').on('change', function() {
				console.log(testeTar);
				$('select[name=produto'+testeTar+']').empty();
				$('#quantidadeProduto'+testeTar).val("");
				$('#valorCompraProduto'+testeTar).val("");
				$('#valorTotalPedido'+testeTar).val("");
				$('select[name=produto'+testeTar+']').append('<option value = ""></option>');
				$.ajax({
					type : 'GET',
					url : 'ProdutoAjaxServlet',
					data : 'categoria='+ $('select[name=categoriaProduto'+testeTar+']').val(),
					statusCode : {
						200 : function(responseText) {
							var resposta = responseText.split(',');
								for (i in resposta) {
									if (resposta[i] != '')
										$('select[name=produto'+testeTar+']').append('<option value = "' + resposta[i] + '">'+ resposta[i]+ '</option')
										}
									}
								}
							});
						})
					});

//pega valor compra produto dinamico
$(document).ready(function(){
	$('select[name=produto0]').on('change', function(){
		$('#valorCompraProduto'+testeTar).val("");
		$('#quantidadeProduto'+testeTar).val("");
		$('#valorTotalPedido'+testeTar).val("");
		$.ajax({
			type: 'GET',
			url: 'ProdutoAjaxServlet',
			data: 'produto='+$('select[name=produto'+testeTar+']').val(),
			statusCode: {
				200: function(responseText){
					var resposta = responseText;
					$('#valorCompraProduto'+testeTar).val(resposta);
				}
			}
		})
	})
})


//duplica as divs dinamicas

$(document).ready(function() {
	
		$('#addLinha').on('click', function(){			
			var f = $('#formProduto0'), c = f.clone(true, true);
			c.attr('id', 'formProduto' + qtdProdutos);
			c.find("#categoriaProduto0").attr('name', 'categoriaProduto'+ qtdProdutos);
			c.find("#categoriaProduto0").attr('id', 'categoriaProduto'+ qtdProdutos);
			c.find("#produto0").attr('name', 'produto'+ qtdProdutos);
			c.find("#produto0").attr('id', 'produto'+ qtdProdutos);
			c.find("#quantidadeProduto0").attr('name', 'quantidadeProduto'+ qtdProdutos);
			c.find("#quantidadeProduto0").attr('id', 'quantidadeProduto'+ qtdProdutos);
			c.find("#valorCompraProduto0").attr('name', 'valorCompraProduto'+ qtdProdutos);
			c.find("#valorCompraProduto0").val("");
			c.find("#valorCompraProduto0").attr('id', 'valorCompraProduto'+ qtdProdutos);
			c.find("#valorTotalProduto0").val("");
			c.find("#valorTotalProduto0").attr('name', 'valorTotalProduto'+ qtdProdutos);
			c.find("#valorTotalProduto0").attr('id', 'valorTotalProduto'+ qtdProdutos);
			
			if(qtdProdutos == 1){
			c.insertAfter('#formProduto0');
		} else {
			c.insertAfter('#formProduto'+returnQtdeProdutos());
		}
			qtdProdutos++;
			$('#qtdeProdutos').val(qtdProdutos);
		})
	}) 
	

//multiplicacao dos valores dinamica
$(document).ready(function() {
		$('#quantidadeProduto0').on('change', function() {
			var quantidade = $('#quantidadeProduto'+testeTar).val();
			var valor = $('#valorCompraProduto'+testeTar).val();
			$('#valorTotalProduto'+testeTar).val(quantidade * valor);
		})
	})

</script>

<title>Compra de produtos</title>
</head>
<body>
	<!-- Input para teste -->
	<input type="hidden" class="teste" value="" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<a class="navbar-brand" href="home">HOME</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item"><a class="nav-link"
								href="cadastroUsuario">Usu&aacute;rios </a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> Fornecedores </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="FornecedoresServlet">Cadastro</a>
									<a class="dropdown-item" href="buscarFornecedor">Buscar</a>
								</div></li>
							<li class="nav-item"><a class="nav-link"
								href="cadastroFranquia">Franquias</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> Produtos </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="cadastroProduto">Cadastro</a> <a
										class="dropdown-item" href="visualizarProdutos">Visualizar</a>
									<a class="dropdown-item" href="compraProduto">Compra</a>
								</div></li>

							<li class="nav-item"><a class="nav-link" href="#">Bem
									vindo, ${sessionScope.usuario.nome}</a></li>
							<li class="nav-item"><a class="nav-link" href="logout">Fazer
									logout</a></li>

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
				<h3>${pagina}</h3>
				<!-- FORMULARIO DE CADASTRO -->
				<!--  
				<div name="checagemQuantidade" id="checagemQuantidade">
				<label for="categoriaProduto">Selecione a quantidade de produtos que deseja comprar</label>
				<select name="quantidadeProdutos" id="quantidadeProdutos" class="form-control" required> 
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				</select>
				
				</div>
				-->
				<form name="compraProduto" id="compraProduto" action="${action}"
					method="POST">
					
					<div name="maxProdutos" id="maxProdutos">Só é possível adicionar até 10 produtos</div>
					
					<div class="row" id="formProduto0">
						
						<div class="col-md-3">
							<div class="form-group">
								<label for="categoriaProduto">Categoria</label> <select
									class="form-control" id="categoriaProduto0"
									name="categoriaProduto0" required>

									<option value="${produto.categoria}">${produto.categoria}</option>

									<c:forEach var="categorias" items="${listaCategorias}">

										<option value="${categorias['nome']}">${categorias['nome']}</option>

									</c:forEach>

								</select>
							</div>
						</div>



						<div class="col-md-3">
							<div class="form-group">
								<label for="produto">Produto</label> <select
									class="form-control" id="produto0" name="produto0" required>

								</select>
							</div>
						</div>

						<div class="col-md-3">
							<div class="form-group">
								<label for="quantidadeProduto">Quantidade</label> <input
									type="text" class="form-control" id="quantidadeProduto0"
									name="quantidadeProduto0"
									placeholder="Quantidade a ser comprada">
							</div>
						</div>


						<div class="col-md-3">
							<div class="form-group">
								<label for="valorCompraProduto">Valor unitário</label> <input
									type="text" readonly="true" class="form-control"
									id="valorCompraProduto0" name="valorCompraProduto0">
							</div>
						</div>



						<div class="col-md-3">
							<div class="form-group">
								<label for="valorTotalPedido">Valor total do produto</label> <input
									type="text" readonly="true" class="form-control"
									id="valorTotalProduto0" value="" name="valorTotalProduto0"
									placeholder="Valor total">
							</div>
						</div>

					</div>
							
						<div class="col-md-12">
							<button type="button" class="btn btn-success" data-toggle="modal"
								data-target="#produtoModal" id="btnCadastrar">Cadastrar</button>
						</div>
					
					<!-- Modal de cadastro -->
					<div class="modal fade" id="produtoModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Produto</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p>Deseja realmente salvar?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Cancelar</button>
									<button type="submit" class="btn btn-primary" name="qtdeProdutos" id="qtdeProdutos"
										>Sim</button>
								</div>
							</div>
						</div>
					</div>

				</form>
					 <br>
					<div class="col-md-3">

						<button type="button" class="btn btn-success" data-toggle="modal"
								id="addLinha">Adicionar produto</button>
					</div>
					
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
						<!-- Rights -->
						<p class="rights">
							<span>ï¿½ï¿½ </span><span class="copyright-year">2019</span><span>ï¿½</span><span>BarTades</span><span>.ï¿½</span><span>All
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
