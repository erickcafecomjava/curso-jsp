package utilitario;

import java.net.http.HttpRequest;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import model.Usuario;
import repository.entity.RepositoryUsuarioDao;

public class Generic {
 
	private RepositoryUsuarioDao dao = new RepositoryUsuarioDao();
	
	public Long idUsuarioResponssavel(HttpServletRequest req) throws SQLException {

		Usuario  usuarioResponssavel = (Usuario) req.getSession().getAttribute("dados");
		return this.dao.getIdResponssavel(usuarioResponssavel.getEmail());
	}
}
