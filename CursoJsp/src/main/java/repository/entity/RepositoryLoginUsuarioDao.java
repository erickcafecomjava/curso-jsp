package repository.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import intefaces.repository.entity.IRepositoryLoginUsuarioDao;
import model.Usuario;

public class RepositoryLoginUsuarioDao implements IRepositoryLoginUsuarioDao<Usuario> {
	private Connection connection = null;

	public RepositoryLoginUsuarioDao() {
		connection = repository.conexao.ConexaoBancoBiblioteca.getConexao();
	}

	@Override
	public Usuario validarLogin(Usuario usuario) throws SQLException {
		String sql = "select * from usuario where email =? and senha=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, usuario.getEmail());
        preparedStatement.setString(2, usuario.getSenha());

        Usuario user = new Usuario();
        
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()) {
         user.setNome(result.getString("nome"));
         user.setEmail(result.getString("email"));
         user.setSenha(result.getString("senha"));
         user.setFoto(result.getString("foto"));
         user.setPermissao(result.getBoolean("permissao"));
        }
		return user;
	}

}
