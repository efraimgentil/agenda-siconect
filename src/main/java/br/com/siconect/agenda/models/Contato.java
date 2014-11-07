package br.com.siconect.agenda.models;

import org.hibernate.validator.constraints.NotEmpty;

public class Contato {
  
  private Integer id;
  @NotEmpty
  private String nome;
  
  @NotEmpty
  private String telefone;
  
  private String celular;
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Contato [id=").append(id).append(", nome=").append(nome).append(", telefone=")
        .append(telefone).append(", celular=").append(celular).append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Contato other = (Contato) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
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
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  
}
