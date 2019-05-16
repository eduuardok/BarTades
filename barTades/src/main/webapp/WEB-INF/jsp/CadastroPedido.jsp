
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="ISO-8859-1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/pedidoScript.js"></script>
        <link rel="stylesheet" href="css/styleUsuario.css" type="text/css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cadastro de Produtos</title>
    </head>

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
                                        <a class="dropdown-item" href="visualizarProdutos">Visualizar</a>
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
                                            <option value="${unidades['nome']}">${unidades['nome']}</option>
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
                            <div class="col-md-12" id="container">
                                <div class="row" id="form_produto1">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="categoriaProduto">Categoria</label> <select class="form-control"
                                                                                                    id="categoriaProduto" name="categoriaProduto">
                                                <c:forEach var="categorias" items="${listaCategorias}">
                                                    <option value="${categorias['nome']}">${categorias['nome']}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="produto">Produto</label>
                                            <input type="text" class="form-control" id="produto" value="${produto}"
                                                   name="produto" placeholder="Produto">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label for="valorDesconto">Valor de Desconto</label> <input type="text"
                                                                                                        class="form-control" id="valorDesconto" value="${valorDesconto}"
                                                                                                        name="valorDesconto" placeholder="Valor de Desconto"
                                                                                                        onKeyPress="return(moeda(this, '.', ',', event))">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label for="quantidade">Quantidade</label>
                                            <input type="text" class="form-control" id="quantidade" value="${quantidade}"
                                                   name="quantidade" placeholder="Quantidade"
                                                   onKeyup="return(formatQtde(this))">
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
        <br>
        <footer class="section footer-classic context-dark bg-image" style="background: #2d3246;">
            <br>
            <div class="container">
                <div class="row row-30">
                    <div class="col-md-4 col-xl-5">
                        <div class="pr-xl-4">
                            <a class="brand" href="index.html"><img
                                    src="https://img.icons8.com/color/50/000000/bar.png"></a>
                            <p>Nós somos uma empresa focada no melhor atendimento e
                                experiência do nosso cliente.</p>
                            <!-- Rights -->
                            <p class="rights">
                                <span>ï¿½ï¿½ </span><span
                                    class="copyright-year">2019</span><span>ï¿½</span><span>BarTades</span><span>.&iuml;&iquest;&frac12;</span><span>All
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
                                <a href="tel:#">+91 7568543012</a> <span>or</span> <a href="tel:#">+91 9571195353</a>
                            </dd>
                        </dl>
                    </div>
                    <div class="col-md-4 col-xl-3">
                        <ul class="nav-list">
                            <li><a href="#">Usuários </a></li>
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
                    <a class="social-inner" href="#"><span class="icon mdi mdi-facebook"></span><span>Facebook</span></a>
                </div>
                <div class="col">
                    <a class="social-inner" href="#"><span class="icon mdi mdi-instagram"></span><span>instagram</span></a>
                </div>
                <div class="col">
                    <a class="social-inner" href="#"><span class="icon mdi mdi-twitter"></span><span>twitter</span></a>
                </div>
                <div class="col">
                    <a class="social-inner" href="#"><span class="icon mdi mdi-youtube-play"></span><span>google</span></a>
                </div>
            </div>
        </footer>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/?/pop?/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/?/4.?/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

    </body>

</html>