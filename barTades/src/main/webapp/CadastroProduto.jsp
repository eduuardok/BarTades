<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar novo produto</title>
</head>
<body>
<h1>Cadastro de produtos</h1><br><br>
<form>
Nome: <input type="text" name="nomeProduto" method="POST"/>
<br>
<br>
Categoria: <select name="categoriaProduto">
    <option value="Teste1">CategoriaTeste</option>
    <option value="Teste2">CategoriaTesteDois</option>
</select>
<br><br>
Fornecedor: <select name="fornecedorProduto">
    <option value="testeFornecedor1">FornecedorTeste</option>
    <option value="testeFornecedor2">FornecedorTesteDois</option>
</select>
<br><br>
Valor de compra: <input type="number" name="valorCompraProduto"/>
<br><br>
Valor de venda: <input type="number" name="valorVendaProduto"/>

<br><br>
Descrição: <input type="text" size="255" name="descricaoProduto"/>
<br><br>
<input type="submit"/>
</form>

</body>
</html>