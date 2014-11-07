package br.com.siconect.agenda.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.siconect.agenda.models.Contato;

@Controller
public class ContatoController {
  
  private static List<Contato> contatos = new ArrayList<Contato>();
  private static AtomicInteger ids = new AtomicInteger(0);
  @Inject
  private Result result;
  @Inject Validator validator;
  
  @Get("contato/")
  public void index(Result result){
    result.forwardTo(this).form();
  }
  
  @Get("contato/redirect")
  public void redirect(Result result){
    result.redirectTo(this).form();
  }
  
  @Get("/")
  public void contatos(){
    result.include("contatos" , contatos );
  }
  
  @Get("/buscar")
  public void buscar(String nome , Validator validator){
    validator.addIf( nome == null || "".equals(nome.trim()) , new SimpleMessage("nome", "Nome é de preenchimento obrigatório") );
    validator.onErrorUsePageOf(this).contatos();
    List<Contato> contatosFiltrados = new ArrayList<Contato>();
    for (Contato contato : contatos) {
      if(contato.getNome().startsWith( nome ) )
        contatosFiltrados.add(contato);
    }
    result.include(nome);
    result.include("contatos" , contatosFiltrados );
    result.of(this).contatos();
  }
  
  @Get("/novo")
  public void form(){ }
  
  @Get("/editar/{id}")
  public void editar(Integer id){
    Contato contato = null;
    for (Contato c : contatos) {
      if(c.getId().equals(id)){
        contato = c; break;
      }
    }
    result.include("contato" , contato);
    result.forwardTo(this).form();
  }
  
  @Post("/editar/{contato.id}")
  public void atualizar(Contato contato , Validator validator){
    validator.onErrorForwardTo(this).form();
    
    contatos.set( contatos.indexOf(contato) , contato );
    result.include("msg", "Contato atualizado com sucesso");
    result.redirectTo(this).contatos();
  }
  
  @Post("/novo")
  public void criar(@Valid Contato contato, Validator validator ){
    validator.onErrorForwardTo(this).form();
    contato.setId( ids.incrementAndGet() );
    contatos.add(contato);
    result.include("msg", "Contato cadastrado com sucesso");
    result.redirectTo(this).contatos();
  }
  
  @Get("/remover/{contato.id}")
  public void remover(Contato contato){
    contatos.remove( contatos.indexOf(contato) );
    result.include("msg", "Contato removido com sucesso");
    result.redirectTo(this).contatos();
  }
  
}
