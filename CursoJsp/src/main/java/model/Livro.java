package model;

import java.sql.Date;
import java.util.Objects;


public class Livro {

	private Integer idLivro;
	private String nomeLivro;
	private String tituloLivro;
	private String  sobreLivro;
    private String capaLivro;
    private Date dataPublicacao;
	private AnexoPdf anexoPdf;
	
	
	
	
	public AnexoPdf getAnexoPdf() {
		return anexoPdf;
	}

	public void setAnexoPdf(AnexoPdf anexoPdf) {
		this.anexoPdf = anexoPdf;
	}

	@Override
	public String toString() {
		return "Livro [idLivro=" + idLivro + ", nomeLivro=" + nomeLivro + ", tituloLivro=" + tituloLivro
				+ ", sobreLivro=" + sobreLivro + ", capaLivro=" + capaLivro + ", dataPublicacao=" + dataPublicacao
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(capaLivro, dataPublicacao, idLivro, nomeLivro, sobreLivro, tituloLivro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(capaLivro, other.capaLivro) && Objects.equals(dataPublicacao, other.dataPublicacao)
				&& Objects.equals(idLivro, other.idLivro) && Objects.equals(nomeLivro, other.nomeLivro)
				&& Objects.equals(sobreLivro, other.sobreLivro) && Objects.equals(tituloLivro, other.tituloLivro);
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getTituloLivro() {
		return tituloLivro;
	}

	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}

	public String getSobreLivro() {
		return sobreLivro;
	}

	public void setSobreLivro(String sobreLivro) {
		this.sobreLivro = sobreLivro;
	}

	public String getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(String capaLivro) {
		this.capaLivro = capaLivro;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public boolean livroNovo() {

		if (this.idLivro == null) {
			return true;
		} else if (this.idLivro != null && this.idLivro > 0) {
			return false;
		}
		return this.idLivro == null;

	}
  
    
    
    
}
