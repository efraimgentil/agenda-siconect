package br.com.siconect.agenda.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.siconect.agenda.models.Contato;
import br.com.siconect.agenda.persistence.ContatoDAO;

@Controller
public class ContatoController {

  @Inject
  private ContatoDAO dao;

  @Inject
  private Result result;
  @Inject
  Validator validator;

  @Get({"/", "contato/", "contato"})
  public void contatos() {
    result.include("contatos", dao.buscarTodos());
  }

  @Get("/buscar")
  public void buscar(String nome, Validator validator) {
    validator.addIf(nome == null || "".equals(nome.trim()), new SimpleMessage("nome",
        "Nome é de preenchimento obrigatório"));
    validator.onErrorUsePageOf(this).contatos();

    result.include("nome" , nome);
    result.include("contatos", dao.buscarPorNome(nome));
    result.of(this).contatos();
  }

  

  @Get("/editar/{id}")
  public void editar(Integer id) {
    try {
      result.include("contato", dao.buscarPorId(id));
      result.forwardTo(this).form();
    } catch (RuntimeException re) {
      result.include("msg", re.getMessage());
      result.forwardTo(this).contatos();
    }
  }

  @Post("/editar/{contato.id}")
  public void atualizar(Contato contato, Validator validator) {
    validator.onErrorForwardTo(this).form();

    dao.atualizar(contato);
    result.include("msg", "Contato atualizado com sucesso");
    result.redirectTo(this).contatos();
  }
  
  @Get("/novo")
  public void form() {}
  
  @Post("/novo")
  public void criar(@Valid Contato contato, Validator validator) {
    validator.onErrorForwardTo(this).form();

    dao.inserir(contato);
    result.include("msg", "Contato cadastrado com sucesso");
    result.redirectTo(this).contatos();
  }

  @Get("/remover/{contato.id}")
  public void remover(Contato contato) {

    dao.remover(contato);
    result.include("msg", "Contato removido com sucesso");
    result.redirectTo(this).contatos();
  }

}