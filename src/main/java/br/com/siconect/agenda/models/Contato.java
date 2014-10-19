package br.com.siconect.agenda.models;

public class Contato {
  
  private String nome;
  private String telefone;
  private String celular;
  
  @Override
  public String toString() {
    return "Contato [nome=" + getNome() + ", telefone=" + telefone + ", celular=" + celular + "]";
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getTelefone() {
    return telefone;
  }
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
  public String getCelular() {
    return celular;
  }
  public void setCelular(String celular) {
    this.celular = celular;
  }
  
}
