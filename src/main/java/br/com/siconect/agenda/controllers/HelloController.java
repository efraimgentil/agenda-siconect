package br.com.siconect.agenda.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class HelloController {
  
  public void index(Result result){
  }
  
  
  public void say(String phrase){
    System.out.println( phrase );
  }
  
}
