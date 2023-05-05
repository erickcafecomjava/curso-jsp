package repository.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import intefaces.repository.entity.IRepositoryDaoEntity;
import model.Usuario;
import repository.conexao.ConexaoBancoBiblioteca;

public class RepositoryUsuarioDao implements IRepositoryDaoEntity<Usuario> {

	private Connection connection;

	public RepositoryUsuarioDao() {
		connection = ConexaoBancoBiblioteca.getConexao();
	}

	@Override
	public Usuario salvar(Usuario usuario  ,Long idAdmin ) throws SQLException {
		if (usuario.usuarioNovo()) {
			String sqlInsert = "insert  into usuario (nome , email ,  senha , permissao  , id_admin  ,datanascimento) values(?,?,?,?,?,?)";
			PreparedStatement executaInsert = connection.prepareStatement(sqlInsert);
			executaInsert.setString(1, usuario.getNome());
			executaInsert.setString(2, usuario.getEmail());
			executaInsert.setString(3, usuario.getSenha());
			executaInsert.setBoolean(4, usuario.getPermissao());
			executaInsert.setLong(5, idAdmin);
			executaInsert.setDate(6, usuario.getDataNascimento());

			executaInsert.execute();
			connection.commit();

			if (usuario.getFoto() != null) {
				sqlInsert = "UPDATE usuario SET  foto=?  WHERE email=?";
				executaInsert = connection.prepareStatement(sqlInsert);
				executaInsert.setString(1, usuario.getFoto());
				executaInsert.setString(2, usuario.getEmail());
				executaInsert.execute();
				connection.commit();
			}
		} else {
			String sqlUpdate = "UPDATE usuario SET  nome=?, email=?, senha=? ,datanascimento=?  WHERE id=?";

			PreparedStatement executaUpadete = connection.prepareStatement(sqlUpdate);
			executaUpadete.setString(1, usuario.getNome());
			executaUpadete.setString(2, usuario.getEmail());
			executaUpadete.setString(3, usuario.getSenha());
			executaUpadete.setDate(4, usuario.getDataNascimento());
			executaUpadete.setLong(5, usuario.getId());

			executaUpadete.executeUpdate();
			connection.commit();

			if (usuario.getFoto() != null ) {
				sqlUpdate = "UPDATE usuario SET  foto=?  WHERE id=?";
				executaUpadete = connection.prepareStatement(sqlUpdate);
				executaUpadete.setString(1, usuario.getFoto());
				executaUpadete.setLong(2, usuario.getId());
				executaUpadete.executeUpdate();
				connection.commit();
			}
		}

		return this.pesquisarUsuarioPorEmail(usuario.getEmail());
	}

	public Boolean verificaUsuarioExiste(Usuario usuario) throws SQLException {
		String sqlExiste = "select count(1)>0   existe from usuario where  email=? or senha=? ";
		PreparedStatement executaContagem = connection.prepareStatement(sqlExiste);
		executaContagem.setString(1, usuario.getEmail());
		executaContagem.setString(2, usuario.getSenha());

		ResultSet isResult = executaContagem.executeQuery();
		if (isResult.next())
			;
		return isResult.getBoolean("existe");

	}

	@Override
	public Usuario deletar(Usuario usuario) throws SQLException {

		String sqlDelet = "delete from usuario where id =?";
		PreparedStatement executaDelete = connection.prepareStatement(sqlDelet);
		executaDelete.setFloat(1, usuario.getId());
		int deletado = executaDelete.executeUpdate();
		connection.commit();
		
		return pesquisarPorIdentificador(usuario.getId());
	}

	private Usuario pesquisarUsuarioPorEmail(String email) throws SQLException {

		String sqlPesquisa = "select * from usuario where email=?";
		PreparedStatement executaBusca = connection.prepareStatement(sqlPesquisa);
		executaBusca.setString(1, email);
		ResultSet result = executaBusca.executeQuery();
		Usuario usuario = new Usuario();
		if (result.next()) {
			usuario.setId(result.getInt("id"));
			usuario.setEmail(result.getString("email"));
			usuario.setNome(result.getString("nome"));
			usuario.setSenha(result.getString("senha"));
			usuario.setFoto(result.getString("foto"));
			usuario.setDataNascimento(result.getDate("datanascimento"));
		}
		return usuario;

	}

	public Long getIdResponssavel(String email) throws SQLException {

		String sqlPesquisa = "select id from usuario where email=?";
		PreparedStatement executaBusca = connection.prepareStatement(sqlPesquisa);
		executaBusca.setString(1, email);
		ResultSet result = executaBusca.executeQuery();
		Long  id = (long) 0;
		while (result.next()) {
			id =  result.getLong("id");
		}

		return id;
		

	}

	
	public Usuario existeEmail(String email) throws SQLException {

		String sqlPesquisa = "select * from usuario where email=?";
		PreparedStatement executaBusca = connection.prepareStatement(sqlPesquisa);
		executaBusca.setString(1, email);
		ResultSet result = executaBusca.executeQuery();
		Usuario usuario = new Usuario();
		if (result.next()) {
			usuario.setId(result.getInt("id"));
			usuario.setEmail(result.getString("email"));
			usuario.setNome(result.getString("nome"));

		}
		return usuario;

	}
	
	@Override
	public Usuario pesquisarPorIdentificador(Integer id) throws SQLException {

		String sqlPesquisa = "select * from usuario where id=?";
		PreparedStatement executaBusca = connection.prepareStatement(sqlPesquisa);
		executaBusca.setLong(1, id);
		ResultSet result = executaBusca.executeQuery();
		Usuario usuario = new Usuario();
		if (result.next()) {
			usuario.setId(result.getInt("id"));
			usuario.setEmail(result.getString("email"));
			usuario.setNome(result.getString("nome"));
			usuario.setSenha(result.getString("senha"));
			usuario.setFoto(result.getString("foto"));
			usuario.setDataNascimento(result.getDate("datanascimento"));
		}
		return usuario;

	}

	@Override
	public List<Usuario> pesquisar(String nome , Long idAdmin) throws SQLException {

		String busca = "select * from usuario where   upper(nome) like upper(?)  and id_admin =?  and    permissao= false";
		PreparedStatement executaBuscar = connection.prepareStatement(busca);
		executaBuscar.setString(1, "" + nome + "%");
		executaBuscar.setLong(2, idAdmin);
		ResultSet resultSet = executaBuscar.executeQuery();
		List<Usuario> LISTA = new ArrayList<Usuario>();
		while (resultSet.next()) {
			Usuario user = new Usuario();
			user.setId(resultSet.getInt("id"));
			user.setNome(resultSet.getString("nome"));
			user.setEmail(resultSet.getString("email"));
			
			LISTA.add(user);
		}
		return LISTA;
	}

	public Usuario atualisarSenha(Usuario usuario) throws SQLException {
		String sqlUpdateSenha = "UPDATE usuario SET  senha=? WHERE id=?";
		PreparedStatement executaUpadete = connection.prepareStatement(sqlUpdateSenha);
		executaUpadete.setString(1, usuario.getSenha());
		executaUpadete.setInt(2, usuario.getId());
		executaUpadete.executeUpdate();
		connection.commit();
		return this.pesquisarPorIdentificador(usuario.getId());
	}
}
