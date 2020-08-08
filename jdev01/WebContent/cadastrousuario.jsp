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
	<h1 class="title">CADASTRO DE USUARIO</h1>
	<h2 style="text-align: center;">${msg}</h2>
	<form action="salvarUsuario" method="post" id="formUser">


		<table class="form-style-1">
			<tbody>
				<tr>
					<td>Código:</td>
					<td><input type="text" id="id" name="id" value="${user.id}"></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.login}"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" id="password" name="password"
						value="${user.senha}"></td>


				</tr>
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${user.nome}"></td>


				</tr>
				<tr>
					<td>Fone:</td>
					<td><input type="text" id="fone" name="fone"
						value="${user.fone}"></td>


				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Salvar"><input
						type="submit" value="Cancelar"
						onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
				</tr>
			</tbody>
		</table>


	</form>
	<table class="table-style">
		<thead>
			<tr>
				<th colspan="12">LISTA DE USUARIOS</th>
			</tr>
			<tr>
				<th colspan="2">LOGIN</th>
				<th colspan="2">NOME</th>
				<th colspan="2">FONE</th>
				<th colspan="2">ID</th>
				<th colspan="2">EXCLUIR</th>
				<th colspan="2">EDITAR</th>
			</tr>
		</thead>
		<c:forEach items="${usuarios}" var="user">

			<tr>
				<td style="width: 150px"><c:out value="${user.login}"></c:out>
				<td>
				<td style="width: 150px"><c:out value="${user.nome}"></c:out>
				<td>
				<td style="width: 150px"><c:out value="${user.fone}"></c:out>
				<td>
				<td style="width: 150px"><c:out value="${user.id}"></c:out>
				<td>
				<td style="width: 150px; text-align: center;"><a
					href="salvarUsuario?acao=delete&id=${user.id}"><img
						src="resources/img/icon_delete.png" width="20px" height="20px"
						alt="Exluir" title="Excluir"></a>
				<td>
				<td style="width: 150px; text-align: center;"><a
					href="salvarUsuario?acao=editar&id=${user.id}"><img
						src="resources/img/icon_edit.png" width="20px" height="20px"
						alt="Editar" title="Editar"></a>
				<td>
			</tr>


		</c:forEach>
	</table>




</body>
</html>