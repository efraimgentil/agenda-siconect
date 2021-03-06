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

	<h1>Agenda - SICONECT</h1>
	
	<c:choose>
		<c:when test="${contato != null and contato.id != null}">
			<c:set var="action" value="${linkTo[ContatoController].atualizar(contato)}"/>
		</c:when>
		<c:otherwise>
			<c:set var="action" value="${linkTo[ContatoController].criar()}"/>
		</c:otherwise>
	</c:choose>
	
	<form action="${action}" method="POST" >
		<div>
			<label>Nome</label>
			<input type="text" name="contato.nome" value="${contato.nome}" />
			${errors.from('contato.nome')}
		</div>
		<div>
			<label>Telefone</label>
			<input type="text" name="contato.telefone" value="${contato.telefone}" />
			${errors.from('contato.telefone')}
		</div>
		<div>
			<label>E-Mail</label>
			<input type="text" name="contato.email" value="${contato.email}" />
			${errors.from('contato.email')}
		</div>
		<button type="submit"  >Cadastrar</button>
		<a href="${linkTo[ContatoController].contatos()}">Voltar</a>
	</form>
</body>
</html>