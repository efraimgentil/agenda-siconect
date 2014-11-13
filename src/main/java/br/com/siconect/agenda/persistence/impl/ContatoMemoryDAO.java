package br.com.siconect.agenda.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.siconect.agenda.models.Contato;
import br.com.siconect.agenda.persistence.ContatoDAO;

public class ContatoMemoryDAO implements ContatoDAO {
  
  private static List<Contato> contatos = new ArrayList<Contato>();
  private static AtomicInteger ids = new AtomicInteger(0);
  
  @Override
  public List<Contato> buscarTodos() {
    return contatos;
  }

  @Override
  public Contato buscarPorId(Integer id) {
    for (Contato c : contatos) {
      if(c.getId().equals(id)){
        return c;
      }
    }
    throw new RuntimeException("Contato não encontrado");
  }
  
  @Override
  public List<Contato> buscarPorNome(String nome) {
    List<Contato> contatosFiltrados = new ArrayList<Contato>();
    for (Contato contato : contatos) {
      if(contato.getNome().startsWith( nome ) )
        contatosFiltrados.add(contato);
    }
    return contatosFiltrados;
  }

  @Override
  public void inserir(Contato contato) {
    contato.setId( ids.incrementAndGet() );
    contatos.add(contato);
  }

  @Override
  public void atualizar(Contato contato) {
    contatos.set( contatos.indexOf(contato) , contato );
  }

  @Override
  public void remover(Contato contato) {
    contatos.remove( contatos.indexOf(contato) );
  }

}
