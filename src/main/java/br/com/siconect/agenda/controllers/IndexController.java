package br.com.siconect.agenda.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@Controller
public class IndexController {
  
  @Path({"/" , ""})
  public void index(){
    
  }
  
  @Path({"/hello500"})
  public void hello500(Result result){
    throw new RuntimeException("Runtime exception for 500 error");
  }
  
}
