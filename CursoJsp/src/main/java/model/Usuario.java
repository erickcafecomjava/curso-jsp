package model;

import java.sql.Date;
import java.util.Objects;

public class Usuario {

	private Integer id;
	private String nome;
	private String email;
	private String senha;
	private String foto;
	private Date dataNascimento;;
	private boolean permissao;

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setPermissao(boolean pemissao) {
		this.permissao = pemissao;
	}
	public boolean getPermissao() {
		return permissao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getFoto() {
		return foto;
	}
	public Boolean isNull() {
		if (this.email == null && this.senha == null && this.id == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean usuarioNovo() {

		if (this.id == null) {
			return true;
		} else if (this.id != null && this.id > 0) {
			return false;
		}
		return this.id == null;

	}
	
	

}
