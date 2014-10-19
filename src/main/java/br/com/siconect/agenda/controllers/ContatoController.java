package br.com.siconect.agenda.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.siconect.agenda.models.Contato;

@Controller
public class ContatoController {
  
  @Get
  public void form(){}
  
  @Post
  public void create(Contato contato){
    System.out.println( contato );
  }
  
}
