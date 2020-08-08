<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/cadastro.css">
<link rel="stylesheet" href="resources/css/lista_registros.css">

<title>Insert title here</title>
</head>
<body>
	<h1 class="title">CADASTRO DE PRODUTO</h1>
	<h2 style="text-align: center;">${msg}</h2>
	<form action="salvarProduto" method="post" id="formProud">


		<table class="form-style-1">
			<tbody>
				<tr>
					<td>Código:</td>
					<td><input type="text" id="id" name="id" value="${prod.id}"></td>
				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${prod.nome}"></td>
				</tr>
				<tr>
					<td>Quantidade:</td>
					<td><input type="text" id="quantidade" name="quantidade"
						value="${prod.quantidade}"></td>


				</tr>
				<tr>
					<td>Valor:</td>
					<td><input type="text" id="valor" name="valor"
						value="${prod.valor}"></td>


				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Salvar"><input
						type="submit" value="Cancelar"
						onclick="document.getElementById('formProud').action = 'salvarProduto?acao=reset'"></td>
				</tr>
			</tbody>
		</table>


	</form>
	<table class="table-style">
		<thead>
			<tr>
				<th colspan="12">LISTA DE PRODUTOS</th>
			</tr>
			<tr>
				<th colspan="2">NOME</th>
				<th colspan="2">QUANTIDADE</th>
				<th colspan="2">VALOR</th>
				<th colspan="2">ID</th>
				<th colspan="2">EXCLUIR</th>
				<th colspan="2">EDITAR</th>
			</tr>
		</thead>
		<c:forEach items="${produtos}" var="prod">

			<tr>
				<td style="width: 150px"><c:out value="${prod.nome}"></c:out>
				<td>
				<td style="width: 150px"><c:out value="${prod.quantidade}"></c:out>
				<td>
				<td style="width: 150px"><c:out value="${prod.valor}"></c:out>
				<td>
				<td style="width: 150px"><c:out value="${prod.id}"></c:out>
				<td>
				<td style="width: 150px; text-align: center;"><a
					href="salvarProduto?acao=delete&id=${prod.id}"><img
						src="resources/img/icon_delete.png" width="20px" height="20px"
						alt="Exluir" title="Excluir"></a>
				<td>
				<td style="width: 150px; text-align: center;"><a
					href="salvarProduto?acao=editar&id=${prod.id}"><img
						src="resources/img/icon_edit.png" width="20px" height="20px"
						alt="Editar" title="Editar"></a>
				<td>
			</tr>


		</c:forEach>
	</table>




</body>
</html>