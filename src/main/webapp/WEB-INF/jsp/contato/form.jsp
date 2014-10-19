<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agenda</title>
</head>
<body>
	Agenda online, contatos da galera do siconect \o/
	
	<form action="${linkTo[ContatoController].create()}" >
		<div>
			<label>Nome</label>
			<input type="text" name="contato.nome" value="" />
		</div>
		<div>
			<label>Telefone</label>
			<input type="text" name="contato.telefone" value="" />
		</div>
		<div>
			<label>Celular</label>
			<input type="text" name="contato.celular" value="" />
		</div>
		<button type="submit"  >Cadastrar</button>
	</form>
</body>
</html>