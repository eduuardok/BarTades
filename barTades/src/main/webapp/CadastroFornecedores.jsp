<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
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
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <!-- Input para teste -->
        <input type="hidden" class="teste" value="" />
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
                                <li class="nav-item"><a class="nav-link"
                                                        href="CadastroUsuario.jsp">Usu&aacute;rios </a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Fornecedores
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="CadastroFornecedores.jsp">Cadastro</a>
                                        <a class="dropdown-item" href="BuscarFornecedor.jsp">Buscar</a>
                                    </div>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="#">Franquias</a></li>
                                <li class="nav-item"><a class="nav-link" href="#">Produtos</a></li>
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
                    <h3>CADASTRO DE FORNECEDORES</h3>
                    <!-- 				FORMULARIO DE CADASTRO -->
                    <form role="form" action="${pageContext.request.contextPath}/FornecedoresServlet" method="post">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label >Raz�o Social</label> <input
                                        type="text" name="nome" class="form-control" placeholder="Digite sua raz�o social...">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label >CNPJ</label> <input type="text" name="cnpj" placeholder="Digite seu CNPJ..." class="form-control" maxlength="18">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Telefone</label> <input type="text" name="telefone" class="form-control" placeholder="Digite seu telefone..." maxlength="14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label >Rua</label> <input type="text" name="endereco" class="form-control" placeholder="Digite a rua..." maxlength="14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>N�mero</label> <input type="text" name="numero" class="form-control" placeholder="Digite o n�mero..." maxlength="14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Complemento</label> <input type="text" name="complemento" class="form-control" placeholder="Digite o complemento..." maxlength="14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Cep</label> <input type="text" name="cep" class="form-control" placeholder="Digite seu CEP..." maxlength="14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Bairro</label> <input type="text" name="bairro" class="form-control" placeholder="Digite seu Bairro..." maxlength="14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Cidade</label> <input type="text" name="cidade" class="form-control" placeholder="Digite sua cidade..." maxlength="14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Estado</label> 
                                    <select class="form-control" name="estado">
                                        <option>Selecione...</option>
                                        <option value="AC">Acre</option>
                                        <option value="AL">Alagoas</option>
                                        <option value="AP">Amap�</option>
                                        <option value="AM">Amazonas</option>
                                        <option value="BA">Bahia</option>
                                        <option value="CE">Cear�</option>
                                        <option value="DF">Distrito Federal</option>
                                        <option value="ES">Esp�rito Santo</option>
                                        <option value="GO">Goi�s</option>
                                        <option value="MA">Maranh�o</option>
                                        <option value="MT">Mato Grosso</option>
                                        <option value="MS">Mato Grosso do Sul</option>
                                        <option value="MG">Minas Gerais</option>
                                        <option value="PA">Par�</option>
                                        <option value="PB">Para�ba</option>
                                        <option value="PR">Paran�</option>
                                        <option value="PE">Pernambuco</option>
                                        <option value="PI">Piau�</option>
                                        <option value="RJ">Rio de Janeiro</option>
                                        <option value="RN">Rio Grande do Norte</option>
                                        <option value="RS">Rio Grande do Sul</option>
                                        <option value="RO">Rond�nia</option>
                                        <option value="RR">Roraima</option>
                                        <option value="SC">Santa Catarina</option>
                                        <option value="SP">S�o Paulo</option>
                                        <option value="SE">Sergipe</option>
                                        <option value="TO">Tocantins</option>
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
                            <!-- Rights -->
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
