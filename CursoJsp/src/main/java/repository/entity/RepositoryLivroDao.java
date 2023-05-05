package repository.entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import intefaces.repository.entity.IRepositoryDaoEntity;
import model.AnexoPdf;
import model.Livro;
import model.Usuario;
import repository.conexao.ConexaoBancoBiblioteca;

public class RepositoryLivroDao implements IRepositoryDaoEntity<Livro>{

	private Connection connection;

	public RepositoryLivroDao() {
		// TODO Auto-generated constructor stub
		connection = ConexaoBancoBiblioteca.getConexao();
	}

	@Override
	public Livro salvar(Livro livro, Long idResponssavelCadastro) throws SQLException {

		if (livro.livroNovo()) {
			String insert = "insert into livro (nomelivro , titulolivro , sobrelivro ,  idresponssavel , datapublicacao) values(?, ?, ?, ?, ?)";
			PreparedStatement executaInsert = connection.prepareStatement(insert);
			executaInsert.setString(1, livro.getNomeLivro());
			executaInsert.setString(2, livro.getTituloLivro());
			executaInsert.setString(3, livro.getSobreLivro());
			executaInsert.setLong(4, idResponssavelCadastro);
			executaInsert.setDate(5, livro.getDataPublicacao());
			executaInsert.execute();
			connection.commit();

			if (livro.getCapaLivro() != null) {
				insert = "update livro set    capalivro=?   where nomelivro=?";
				executaInsert = connection.prepareStatement(insert);
				executaInsert.setString(1, livro.getCapaLivro());
				executaInsert.setString(2, livro.getNomeLivro());
				executaInsert.executeUpdate();
				connection.commit();
			}
		} else {
			String update = "update livro set  nomelivro=?, titulolivro=?, sobrelivro=?,  datapublicacao=? where idlivro=?";
			PreparedStatement executaInsert = connection.prepareStatement(update);
			executaInsert.setString(1, livro.getNomeLivro());
			executaInsert.setString(2, livro.getTituloLivro());
			executaInsert.setString(3, livro.getSobreLivro());
			executaInsert.setDate(4, livro.getDataPublicacao());
			executaInsert.setLong(5, livro.getIdLivro());
			executaInsert.executeUpdate();
			connection.commit();

			if (livro.getCapaLivro() != null) {
				update = "update livro set    capalivro=?   where idlivro=?";
				executaInsert = connection.prepareStatement(update);
				executaInsert.setString(1, livro.getCapaLivro());
				executaInsert.setInt(2, livro.getIdLivro());
				executaInsert.executeUpdate();
				connection.commit();
			}
		}

		return this.buscaLivroPornome(livro.getNomeLivro());
	}

	private Livro buscaLivroPornome(String nomeLivro) throws SQLException {
		String sqlPesquisa = "select * from livro where nomelivro=?";
		PreparedStatement executaBusca = connection.prepareStatement(sqlPesquisa);
		executaBusca.setString(1, nomeLivro);
		ResultSet result = executaBusca.executeQuery();
		Livro livro = new Livro();
		if (result.next()) {
			livro.setIdLivro(result.getInt("idLivro"));
			livro.setNomeLivro(result.getString("nomeLivro"));
			livro.setTituloLivro(result.getString("tituloLivro"));
			livro.setSobreLivro(result.getString("sobrelivro"));
			livro.setCapaLivro(result.getString("capalivro"));
			livro.setDataPublicacao(result.getDate("datapublicacao"));

		}
		return livro;
	}

	@Override
	public Livro deletar(Livro entidadeId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livro> pesquisar(String nomelivro, Long idresponssavel) throws SQLException {
		String busca = "select * from livro where   upper(nomelivro) like upper(?)  and idresponssavel =?";
		PreparedStatement executaBuscar = connection.prepareStatement(busca);
		executaBuscar.setString(1, "" + nomelivro + "%");
		executaBuscar.setLong(2, idresponssavel);
		ResultSet resultSet = executaBuscar.executeQuery();
		List<Livro> LISTA = new ArrayList<Livro>();
		while (resultSet.next()) {
			Livro livro = new Livro();
			livro.setIdLivro(resultSet.getInt("idlivro"));
			livro.setNomeLivro(resultSet.getString("nomelivro"));
			livro.setDataPublicacao(resultSet.getDate("datapublicacao"));

			LISTA.add(livro);
		}

		return LISTA;
	}

	@Override
	public Livro pesquisarPorIdentificador(Integer idlivro) throws SQLException {
		String sqlPesquisa = "select * from livro where idlivro=?";
		PreparedStatement executaBusca = connection.prepareStatement(sqlPesquisa);
		executaBusca.setInt(1, idlivro);
		ResultSet result = executaBusca.executeQuery();

		Livro livro = new Livro();
		if (result.next()) {
			livro.setIdLivro(result.getInt("idlivro"));
			livro.setNomeLivro(result.getString("nomelivro"));
			livro.setTituloLivro(result.getString("tituloLivro"));
			livro.setSobreLivro(result.getString("sobrelivro"));
			livro.setCapaLivro(result.getString("capalivro"));
			livro.setDataPublicacao(result.getDate("datapublicacao"));

		}
		return livro;
	}


	public int totalLinhasRegistros(Long idResponssavel) throws SQLException {

		Integer Linhas = 0;
		Integer totalPaginas = 0;
		String contaQuantasLinha = "select count(1) as total from livro where    idresponssavel=?  ";
		PreparedStatement executaContagem = connection.prepareStatement(contaQuantasLinha);
		executaContagem.setLong(1, idResponssavel);
		ResultSet resultSet = executaContagem.executeQuery();
		if (resultSet.next())
			;
		Linhas = resultSet.getInt("total");

		totalPaginas = (Linhas + (5 - 1)) / 5;
		return (int) Math.ceil(totalPaginas);
	}

	public List<Livro> listaTodosLivrosPorPagina(Long idUsuarioResponssavel , int offset) throws SQLException {
		String busca = "select * from livro where   idresponssavel = "+idUsuarioResponssavel+ " limit 5  offset " +offset+ "";
		PreparedStatement executaBuscar = connection.prepareStatement(busca);

		ResultSet resultSet = executaBuscar.executeQuery();
		List<Livro> LISTA = new ArrayList<Livro>();
		while (resultSet.next()) {
			Livro livro = new Livro();
			livro.setIdLivro(resultSet.getInt("idlivro"));
			livro.setNomeLivro(resultSet.getString("nomelivro"));
			livro.setDataPublicacao(resultSet.getDate("datapublicacao"));
			

			LISTA.add(livro);
		}

		return LISTA;
	}
	
	
	public List<Livro> listaTodosLivros(Long idUsuarioResponssavel ) throws SQLException {
		String busca = "select * from livro where   idresponssavel = "+idUsuarioResponssavel+ " limit 5  ";
		PreparedStatement executaBuscar = connection.prepareStatement(busca);

		ResultSet resultSet = executaBuscar.executeQuery();
		List<Livro> LISTA = new ArrayList<Livro>();
		while (resultSet.next()) {
			Livro livro = new Livro();
			livro.setIdLivro(resultSet.getInt("idlivro"));
			livro.setNomeLivro(resultSet.getString("nomelivro"));
			livro.setDataPublicacao(resultSet.getDate("datapublicacao"));

			LISTA.add(livro);
		}

		return LISTA;
	}
}
