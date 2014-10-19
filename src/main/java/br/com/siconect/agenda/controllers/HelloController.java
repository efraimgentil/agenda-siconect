package br.com.siconect.agenda.controllers;

import br.com.caelum.vraptor.Controller;

@Controller
public class HelloController {
  
  public void say(String phrase){
    System.out.println( phrase );
  }
  
}
