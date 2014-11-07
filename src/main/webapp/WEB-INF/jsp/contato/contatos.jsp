<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agenda</title>
<style>
	.error{
		color: red;
	}
</style>
</head>
<body>
	
	<h1>Agenda online, contatos da galera do siconect \o/</h1>
	
	<form id="formBusca" action="${linkTo[ContatoController].buscar()}" method="get">
		<div>
			<span class="error">${errors.from('nome')}</span>
		</div>
		<div>
			<label for="nome">Nome</label>
			<input id="nome" name="nome" value="${nome}" />
			<button type="submit">Buscar</button>
		</div>
		
	</form>
		
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
			<c:if test="${empty contatos}">
				<tr>
					<td colspan="5" class="error">Nenhum registro encontrado</td>
				</tr>
			</c:if>
			<c:forEach items="${contatos}" var="contato">
				<tr>
					<td>${contato.nome}</td>
					<td>${contato.telefone}</td>
					<td>${contato.celular}</td>
					<td></td>
					<td>
						<a href="${linkTo[ContatoController].editar(contato.id)}">Editar</a>
						<a href="${linkTo[ContatoController].remover(contato)}" onclick="return confirm('Tem certeza?')" >Remover</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>