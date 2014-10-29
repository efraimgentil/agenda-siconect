Agenda Siconect
========

Projeto introdutório ao uso do framework VRaptor 4. Porjeto usado no workshop ministrado no evento SICONECT 2014 - Estacio FIC - Fortaleza/CE

## VRaptor

VRaptor é um java para framework web, criado em 2003 no IME-USP, hoje atualizado e mantido principalmente pela Caelum, porem é um framework de codigo aberto e qualquer um pode contribuir.

Surgiu com a ideia de facilitar o desenvolvimento de java para web, onde na epoca os frameworks do mercado eram "coisas" como Struts e JSF
 
Algumas caracteristicas
- Alta produtividade
- Testabilidade
- REST
- CDI
- Baixa curva de aprendizado
- Convenções sobre Configurações 

## Criando um projeto

### Maven
Projeto utiliza o maven para gerenciar suas dependencias, para aqueles que não conhecem o maven ainda, o maven é uma ferramenta de gerenciamento do projeto
onde é possivel controlar as dependencias, tarefas de build e destribuição do projeto. Para saber mais: http://maven.apache.org/

As versões mais novas do eclipse já vem com o plugin do maven instalado, mas caso no seu eclipse não possua basta instalar atraves do market place 

No arquivo pom.xml você encontrara todas as dependencias necessárias para rodar o projeto

### Sem maven
Caso não esteja usando maven pode baixar o arquivo zip com as bibliotecas necessárias

//TODO lib.zip 
 
## Configurando

Usando o tomcat, é necessário configurar o listener do WELD, no arquivo WEB-INF\web.xml é necessário colocar as seguintes linhas

```xml
<listener>
	<listener-class>org.jboss.weld.environment.servlet.Listener</listener-class>
</listener>
```

Dentro da pasta WEB-INF, também é necessário criar o arquivo beans.xml, que abilitara o CDI
esse necessaŕio, pois o VRaptor4 foi criado em cima da especificação do CDI

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
							http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
	version="1.1" bean-discovery-mode="all">
</beans>
```
Com isso já é possivel rodar o projeto no Tomcat7 sem problemas, porem você ainda não possui nenhum controller criado.

## Primeiro Controller 

O VRaptor é um framework MVC - Model View Controller, logo temos controllers ( du ? ) que são responsaveis pela interação com a view  e a comunicação com o modelo, para criação de um controller com vraptor basta utilizar a anotação @Controller e a notar a classe que desejamos que seja stereotipada como um Controller

```java
@Controller
public class HelloController {
}
```
Apenas com isso já temos um controller que responde pela rota "/hello" , mas veja que em momento algum foi especificado que caminho o controller iria responder! o VRaptor trabalha fortemente em cima de convenções  logo seguindo a convenção todo controller responde pela rota com o nome do controller sem o sufixo controller, veja: 
{Hello}Controller, e por convenção também a primeira letra sera convertida para lowercase e o restante do nome do controller segue a convensão do camelCase normalmente. 

Exemplo:
```java
@Controller //Responde para /helloWorld
public class HelloWorldController{}

@Controller //Responde para /hello
public class HelloController{}

@Controller //Responde para /nomeQualquerComplicado
public class NomeQualquerComplicadoController{}

@Controller //Responde para /hello
public class Hello{}
```
Porem veja que isso não é suficiente para o controller responder uma requisição, o controller por si so não executa nenhuma ação, mas ele é necessário para que se possa definir os metodos das classes

```java
@Controller //Responde para /hello
public class HelloController{
	
	public void index(){
	}
	
}
```

Mais uma vez por convensão todos os meotods publicos do controller são considerados rotas, logo se meu controller response a /hello meu metodo responde a /index logo tempos /hello/index 

### JSP's

Ao executar o controller acima, o metodo sera executado ( caso a rota esteja correta ) porem no browser é retornado o erro 404, pois não foi encontrada a JSP do metodo executado, mais uma vez o VRaptor possui convensções para isso, cada metodo publico do controller deve possuir sua respectiva jsp (caso o mesmo deva responder como jsp) 
e todo controler deve possuir sua pasta raiz de jsps, por convensão o essa pasta deve ficar localizada dentro do de "WEB-INF/jsp", logo se eu possui o controller HelloController, devo criar a pasta "WEB-INF/jsp/hello", como meu controller possui o metodo index devo criar a jsp "index.jsp", por fim tempos "WEB-INF/jsp/hello/index.jsp", lembrando, a minha JSP DEVE ter o mesmo nome do metodo a qual responde!

Pode parecer confuso, então para recaptular
-Pasta default para as jsps: "WEB-INF/jsp"
-Todo controller tem uma pasta na pasta defaul: "WEB-INF/jsp/{nomeDoController}" (seguindo as convenções a palavra controller não é adicionada no nome da pasta)
-Para todo metodo public que deve responder jsp deve ser criada uma jsp com o mesmo nome do metodo!

### Recebendo parametros no controller

Para receber um parametro em um metodo do controller, basta especificar tipo do parametro e o nome do parametro veja:
```java
@Controller
public class HelloController{
	
	public void say(String phrase){
		System.out.println(phrase);
	}
	
}
```

Se durante o request a rota "hello/say" existir o parametro phrase, o vraptor ira preencher a referencia "phrase" com o valor vindo do request, e no caso do exemplo também exibira no console.

Mas caso eu queira preencher um objeto completo? Uma das facilidade do vraptor é a leitura e preenchimento dos objetos no seu controller, imagine um formulário complexo e a leitura parametro por parametro para preencher o objeto! ( Em um senario somente com JSP e Servlets ), você teria varias linhas de codigo apenas para preencher o objeto e so então comessaria a utilizar o objeto. O VRaptor mais uma vez traz uma convensão bem interessante para preencher os objetos, veja a classe

```java
public class Contato{
	private String nome;
	private String telefone;
	private String celular;
	//Gets and Sets
}
```

Agora queremos preencher um cadastro para criar uma novo contato, logo teremos o seguinte controller e formulario html

```java
@Controller
public class ContatoController{
	
	public void form(){}
	
	public void create(Contato contato){
		//Cadastra contato
		System.out.println(contato);
	}
	
}
```

```xml
<!-- WEB-INF/contato/form.jsp -->
<form action="${linkTo[ContatoController].create()}">
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
```

Então no nosso controller temos o a rota form ( "contato/form" ) que ira exibir o formulário e a rota "contato/create" que ira receber o 
formulário e preencher o objeto, importante observar que, no formulario deve serguir a convensão estipulado pelo vraptor onde {nomeDoParametro}.{nomeDoAtributo}
logo se eu tenho o parametro "contato" e quero preencher o atributo nome dentro dele devo ter enviar um parametro no formulario com o name "contato.nome". 
Se no meu controller eu o nome do parametro do metodo fosse "con", no meu formulario eu teria "con.nome", sim o nome do parametro do metodo no controller é importante!

Você pode notar também o objeto "linkTo" sendo usado para refernciar um controller, esse objeto é um helper injetado pelo vraptor e todas as paginas jsp, atraves dele você pode referenciar de forma facil as rotas do controller atraves dos metodos do mesmo.

### Regras de navegação

Veja que no exemplo acima meu metodo create responde tanto a uma requisição POST como uma requisição GET "contato/create?contato.nome=Efraim&contato.telefone=99009900&contato.celular=00990099" também chama o metodo create, isso muitas vezes não é o desejado, se vamos tratar um formulário não é interessante que os parametros do meu formulário saiam na url da pagina, é interessate que o metodo create responda apenas para requisições POST, o vraptor no permite isso de forma bem simples, basta anotar o metodo com a anottação @Post
```java
@Post
public void create(Contato contato){
	//Cadastra contato
	System.out.println(contato);
}
```
Seguindo essa logica, o VRaptor também nos fornece a anotação @Get, assim podemos anotar nosso meotod "form" para responder apenas a requisições GET
```java
@Get
public void form(){
}
```
O VRaptor também nos fornece duas outras anotações @Put e @Delete, essas que servem para que possamos criar uma aplicação seguindo a arquitetura REST, porem não iremos abordar seu uso nesse projeto.
Usando essas anotações nos podemos alterar também a rota caso não queiramos usar o nome do metodo para a arota, basta adicionar no corpo da anotação o caminho desejano, mas é necessário informar o caminho completo.
```java
@Get("contato/novo")
public void form(){
}
```
Importante lembrar que essa alteração não altera a convensão de localização da pagina jsp, altera apenas a rota logica do controller

### Redirect e Forward

Muitas vezes precisamos que uma determinada URI aponte para outro metodo (forward) ou redirecione para outra URI (redirect), isso também pode ser atingido de forma simples com o VRaptor, veja o exemplo

```java
@Get("contato/")
public void index(Result result){
	result.forwardTo(this).form()
}
@Get("contato/novo")
public void form(){
}
```

Da mesma maneira é possivel fazer o redirect vjea:
```java
@Get("contato/redirect")
public void redirect(Result result){
	result.redirectTo(this).form()
}
@Get("contato/novo")
public void form(){
}
```
Veja que uma das grandes diferenças é que o forward não altera a URI da pagina, já o redirect altera








