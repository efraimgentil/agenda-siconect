<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agenda</title>
</head>
<body>
	Agenda online, contatos da galera do siconect \o/
	
	
	<a href="${linkTo[ContatoController].form()}">Novo</a>
	
	<table>
		<thead>
			<tr>
				<td>Nome</td>
				<td>Telefone</td>
				<td>Celular</td>
				<td>E-Mail</td>
				<td>Ações</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contatos}" var="contato">
				<tr>
					<td>${contato.nome}</td>
					<td>${contato.telefone}</td>
					<td>${contato.celular}</td>
					<td></td>
					<td>
						<a href="${linkTo[ContatoController].editar(contato.id)}">Editar</a>
						<a href="#">Remover</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>