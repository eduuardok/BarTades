<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

<script src="js/produtoScript.js"></script>
<link rel="stylesheet" href="css/styleUsuario.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script type="text/javascript" charset="ISO-8859-1">
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
							var id = resposta[i].split(':')[0];
							var nome = resposta[i].split(':')[1];
									if (resposta[i] != ''){
										$('select[name=produto'+testeTar+']').append('<option value = "' + id + '">'+ nome + '</option')
										}
									}
								}
							}
						})
					})
	})
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
			if(qtdProdutos < 10){
				console.log('qtde produtos: ' + qtdProdutos)
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
			} else {
				$('#maxProdutos').modal('show');
			}
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

	//verifica duplicidade de produto dinamico
	
	$(document).ready(function(){
		$('select[name=produto0]').on('change', function(){
			
			var produto = $('select[name=produto'+testeTar+']').val();
			var quantidade = qtdProdutos;
			
			for(var i = 0; i <= quantidade; i++){
				if(i != testeTar){
				if(produto == $('select[name=produto'+i+']').val()){
					$('select[name=produto'+testeTar+']').val("");
					$('#valorCompraProduto'+testeTar).val("");
					$('#dupProdutos').modal('show');
				}
			}
		}
	})
})
</script>

<title>Compra de produtos</title>
</head>
<body>
	
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
                                <li class="nav-item dropdown"><a
                                        class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                        role="button" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false"> Usu&aacute;rios </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="cadastroUsuario">Cadastro</a>
                                        <a class="dropdown-item" href="visualizarUsuarios">Visualizar</a>
                                    </div></li>
                                <li class="nav-item dropdown"><a
                                        class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                        role="button" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false"> Fornecedores </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="FornecedoresServlet">Cadastro</a>
                                        <a class="dropdown-item" href="visualizarFornecedor">Visualizar</a>
                                    </div></li>
                                <li class="nav-item dropdown"><a
                                        class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                        role="button" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false"> Franquias </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="cadastroFranquia">Cadastro</a>
                                        <a class="dropdown-item" href="visualizarFranquia">Visualizar</a>
                                    </div></li>
                                <li class="nav-item dropdown"><a
                                        class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                                        role="button" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false"> Produtos </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="cadastroProduto">Cadastro</a> 
                                        <a	class="dropdown-item" href="visualizarProdutos">Visualizar</a>
                                        <a class="dropdown-item" href="compraProduto">Comprar</a>
                                        <a class="dropdown-item" href="visualizarPedidosCompra">Visualizar Compras</a>
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
				
				<form name="compraProduto" id="compraProduto" action="${action}"
					method="POST" >
					
					<div class="row" id="formProduto0">
					
						<div class="col-md-3">
							<div class="form-group">
								<label for="categoriaProduto">Categoria</label> <select
									class="form-control" id="categoriaProduto0"
									name="categoriaProduto0" required>

									<option value="${produto.categoria}">${produto.categoria}</option>

									<c:forEach var="categorias" items="${listaCategorias}">

										<option value="${categorias['id']}">${categorias['nome']}</option>

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
					
					<!-- Modal de aviso maximo de produtos -->
					<div class="modal fade" id="maxProdutos" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Quantidade máxima adicionada</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p>Máximo de 10 produtos adicionados</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Ok</button>
								</div>
							</div>
						</div>
					</div>
					
					<!-- Modal de produto duplicado -->
					<div class="modal fade" id="dupProdutos" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Produto já selecionado</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p>Este produto já foi selecionado! Por favor selecione outro</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Ok</button>
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
						<p>Bar Tades</p>
						<!-- Rights-->
						<p class="rights">
							<span>BarTades. </span><span class="copyright-year">2019</span><span> </span><span>Avengers Group</span><span> </span><span>All
								Rights Reserved.</span>
						</p>
					</div>
				</div>
				<div class="col-md-4">
					<dl class="contact-list">
						<dt>Endereço:</dt>
						<dd>Avenida Eng. Eusébio Stevaux, 823</dd>
					</dl>
					<dl class="contact-list">
						<dt>Desenvolvido por:</dt>
						<dd>
							<a href="mailto:#">Antonio Carlos</a><br>
							<a href="mailto:#">Eduardo Luna</a><br>
							<a href="mailto:#">Renan Queiroz</a><br>
							<a href="mailto:#">Victor Rodrigues de Matos</a><br>
							<a href="mailto:#">Vitor luiz dos santos</a><br>
						</dd>
					</dl>
				</div>
				<div class="col-md-4 col-xl-3">
					
				</div>
			</div>
		</div>
		
	</footer>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

</body>


  

</html>
