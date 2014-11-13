package br.com.siconect.agenda.persistence;

import java.util.List;

import br.com.siconect.agenda.models.Contato;

public interface ContatoDAO {
  
  public Contato buscarPorId(Integer id);
  
  public List<Contato> buscarTodos();
  
  public List<Contato> buscarPorNome(String nome);
  
  public void inserir(Contato contato);
  
  public void atualizar(Contato contato);
  
  public void remover(Contato contato);
  
}
