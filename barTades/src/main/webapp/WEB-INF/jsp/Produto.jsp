<%-- 
    Document   : VisualizarProdutos
    Created on : Apr 6, 2019, 9:56:38 PM
    Author     : ELuna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/produtoScript.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>


<link rel="stylesheet" href="css/styleUsuario.css" type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Visualizar produtos</title>
</head>

<body>

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
                                <input id="campoBusca" class="form-control mr-sm-2" type="text"
                                       placeholder="Buscar produto" aria-label="Search">
                            </form>
                             
                  
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <br>
        <h3>Visualização de produtos</h3>
	
		
        <table class="table table-hover" action="visualizarProdutos" >
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Preço venda</th>
                    <th scope="col">Preço compra</th>
                    <th scope="col">Fornecedor</th>
                    <th scope="col">Descrição</th>
                    <th scope="col">Quantidade em estoque</th>
                    <th scope="col">Unidade</th>
                    <th scope="col">Habilitado</th>
                    <th scope="col">Opções</th>
                </tr>
            </thead>
            
            <tbody id="tabelaProdutos">
                    
                  
              <c:forEach var="produtos" items="${listaDeProdutos}">
                <tr> 	
                    <th scope="row"> ${produtos['id']} </th>
                    <td>${produtos['nome']}</td>
                    <td>${produtos['categoria']}</td>
                    <td>${produtos['precoVenda']}</td>
                    <td>${produtos['precoCompra']}</td>
                    <td>${produtos['fornecedor']}</td>
                    <td>${produtos['descricao']}</td>
                    <td>${produtos['quantidade']}</td>
                    <td>${produtos['unidade']}</td>
                    <td>
                    <c:if test="${produtos['disponibilidade'] == true}">Habilitado</c:if>
                    
                    <c:if test="${produtos['disponibilidade'] == false}">Desabilitado</c:if>
                    
                    </td>
                    <td><a href="editarProduto?idProduto=${produtos['id']}"><button type="button" class="btn btn-outline-dark">Editar</button></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
		
<script>
$(document).ready(function(){
  $("#campoBusca").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#tabelaProdutos tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

</body>

</html>
