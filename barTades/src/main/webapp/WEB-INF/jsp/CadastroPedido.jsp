<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="ISO-8859-1">
        <script src="js/pedidoScript.js"></script>
        <link rel="stylesheet" href="css/styleUsuario.css" type="text/css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
              <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cadastro de Produtos</title>
    </head>

    <script>

        var container = '';
        var tar = '';
        
        function zerarProdutos(formulario) {
            $('select[name=produtos'+formulario+']').empty();
            $('select[name=produtos'+formulario+']').append('<option value = ""></option>');
        }
        
        $(document).ready(function () {
            $('#formularioPedido').on('change', function (e) {
                console.log('elemento: ', e.target);
                container = e.target;
                atribuirTar();
                if (container.getAttribute('id') == 'categoriaProduto' && tar != '') {
                    $('select[name=produtos' + tar + ']').empty();
                    $('#valorDesconto' + tar).val("");
                    $('#quantidade' + tar).val("");
                    $('select[name=produtos' + tar + ']').append('<option value = ""></option>');
                    $.ajax({
                        type: 'GET',
                        url: 'CadastroPedidoAjax',
                        data: 'categoria=' + $('select[name=categoriaProduto' + tar + ']').val(),
                        statusCode: {
                            200: function (responseText) {
                                var resposta = responseText.split(',');
                                for (i in resposta) {
                                    var id = resposta[i].split(':')[0];
                                    var nome = resposta[i].split(':')[1];
                                    if (resposta[i] != '') {
                                        $('select[name=produtos' + tar + ']').append('<option value = "' + id + '">' + nome + '</option')
                                    }
                                }
                            }
                        }
                    })
                }
                if (container.getAttribute('id') == 'produtos' && tar != '') {
                    $(document).ready(function () {
                        $('select[name=produtos' + tar + ']').on('change', function () {
                            $('[name=valorUnitario'+ tar +']').val("");
                            $.ajax({
                                type: 'GET',
                                url: 'ProdutoAjaxServlet',
                                data: 'produto=' + $('select[name=produtos' + tar + ']').val(),
                                statusCode: {
                                    200: function (responseText) {
                                        var resposta = responseText;
                                        $('[name=valorUnitario' + tar + ']').val(resposta);
                                        $('[name=valorTotal' + tar + ']').val( $('[name=quantidade' + tar + ']').val() * resposta) ;
                                    }
                                }
                            })
                        })
                    })
                }
            })

            //$('#').data('id');
        })



        function atribuirTar() {
            if (container.id == 'categoriaProduto') {
                tar = container.getAttribute('name').replace('categoriaProduto', '');
            }
        }


        $(document).ready(function () {
            $('select[name=categoriaProduto1' + tar + ']').on('change', function () {


            })
        })





    </script>

    <body>

        <!-- The Modal -->
        <div class="modal fade" id="deleteModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">BarTades</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        Deseja realmente excluir?
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteRow()">Excluir</button>
                    </div>

                </div>
            </div>
        </div>

        <!-- Input para teste -->
        <input type="hidden" class="teste" value="" />
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                        <a class="navbar-brand" href="index.jsp">HOME</a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item"><a class="nav-link" href="CadastroUsuario.jsp">Usu&aacute;rios </a>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="#">Fornecedores</a></li>
                                <li class="nav-item"><a class="nav-link" href="#">Franquias</a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Produtos
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="cadastroProduto">Cadastro</a> 
                                        <a	class="dropdown-item" href="visualizarProdutos">Visualizar</a>
                                        <a class="dropdown-item" href="compraProduto">Comprar</a>
                                        <a class="dropdown-item" href="visualizarPedidosCompra">Visualizar Compras</a>
                                    </div>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="#">Contate-nos</a></li>
                            </ul>
                            <form class="form-inline my-2 my-lg-0">
                                <input class="form-control mr-sm-2" type="search" placeholder="Buscar" aria-label="Search">
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
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
                    <form name="cadastroPedido" action="${action}" method="POST">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="tipoPagamento">Tipo de Pagamento</label> <select class="form-control"
                                                                                                 id="tipoPagamento" name="tipoPagamento">
                                        <option>Cartão</option>
                                        <option>Dinheiro</option>
                                    </select>

                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="unidade">Unidade</label>
                                    <select class="form-control" id="unidade" name="unidade">
                                        <c:forEach var="unidades" items="${listaUnidades}">
                                            <option value="${unidades['id']}">${unidades['nome']}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="nomeCliente">Nome do Cliente compra</label> <input type="text"
                                                                                                   class="form-control" id="nomeCliente" value="${nomeCliente}" name="nomeCliente"
                                                                                                   placeholder="Nome">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <h5>Produtos:</h5>
                                </div>
                            </div>
                            <div class="col-md-12" id="formularioPedido">
                                <div class="row" id="form_produto1">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="categoriaProduto">Categoria</label> 
                                            <select class="form-control" id="categoriaProduto" name="categoriaProduto1" onselect="atualizarProdutos()">
                                                <option value=""></option>
                                                <c:forEach var="categorias" items="${listaCategorias}">
                                                    <option value="${categorias['id']}">${categorias['nome']}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="produto">Produto</label>
                                            <select class="form-control" id="produtos" name="produtos1">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label for="valorUnitario">Valor Unitário</label> 
                                            <input type="text"
                                                   class="form-control" id="valorUnitario" value="${valorUnitario}"
                                                   name="valorUnitario1" placeholder="Valor Unitário"
                                                   onKeyPress="return(moeda(this, '.', ',', event))">
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <div class="form-group">
                                            <label for="quantidade">Qtde</label>
                                            <input type="text" class="form-control" id="quantidade" value="${quantidade}"
                                                   name="quantidade1" placeholder="Qtde"
                                                   onKeyup="return(formatQtde(this))">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div clas="form-group">
                                            <label for="valorTotal">Valor Total</label>
                                            <input type="text"
                                                   class="form-control" id="valorTotal" value="${valorTotal}"
                                                   name="valorTotal1" placeholder="Valor Total"
                                                   onKeyPress="return(moeda(this, '.', ',', event))"
                                                   onChange="return(moeda(this, '.', ',', event))" >
                                        </div>
                                    </div>

                                </div>

                                <div class="row" id="adicionarLinha">
                                    <div class="col-md-11">
                                    </div>
                                    <div class="col-md-1">
                                        <label>               </label>
                                        <button type="button" class="btn btn-outline-success"
                                                onClick="addRow('form_produto1')">
                                            +
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <p></p>
                            <div class="col-md-12">
                                <button type="submit" name="idProduto" value="${idProduto}"
                                        class="btn btn-success">Cadastrar</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div id="result">

        </div>
        <button type="button" class="btn btn-outline-success" onClick="deleteRow()">aaa</button>
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


        <script src="https://stackpath.bootstrapcdn.com/?/4.?/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

    </body>

</html>