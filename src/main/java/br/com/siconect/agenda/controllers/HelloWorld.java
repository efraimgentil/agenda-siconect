package br.com.siconect.agenda.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;

/*
 * Todo controller deve ser anotado com a anotação
 * @Controller
 * Por convenção o controller ira responder ao caminho 
 * {Raiz}/{NomeDoController}/{nomeDoMetodo}
 * Onde se {Raiz} = "localhost:8080/agenda"
 * então o caminho do controller é: http://localhost:8080/agenda/helloWorld/{nomeDoMetodo}
 * Veja que o nome do controller tem a primeira letra com letra minuscula e o restante 
 * segue a regra do cammelCase 
 */
@Controller
public class HelloWorld {
  /*
   * Todos os metodos publicos dessa classe serão considerados como rotas ou seja cada metodo pode
   * ser considerado uma pagina diferente.
   * por convenção todos os metodos tem caminho com o prefixo {nomeDoController}/
   * logo o metodo index() tera o caminho: helloWorld/index
   */
  public void index() {

  }
  
  /*
   * Result é uma classe do VRaptor que serve de controle para resposta no controller 
   * por exemplo para incluir um parametro a view basta usar o metodo include como exemplo abaixo
   */
  public void sayHello(Result result) {
    result.include("hello", "Hello Wolrd!");
  }
  /*
   * Result pode ser usado para dar forward apontando para outro controller 
   * ou o mesmo controller e chamar a rota desejada
   */
  public void forward(Result result){
    result.forwardTo(this).sayHello(result);
  }
  /*
   * Result pode ser usado para redirecinar para outro controller 
   * ou o mesmo controller e chamar a rota desejada
   */
  public void redirect(Result result){
    result.redirectTo(this).sayHello(result);
  }
  

}
