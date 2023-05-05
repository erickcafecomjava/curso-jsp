package controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import repository.entity.RepositoryLoginUsuarioDao;

@WebServlet({ "/logar", "/deslogar" })
public class ControleLoginUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RepositoryLoginUsuarioDao daoRepositoryLoginUsuario = new RepositoryLoginUsuarioDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();
		String destino = "index.jsp";
		if(acao.equals("/deslogar")) {
			request.getSession().invalidate();
			
		}else {
			doPost(request, response);
		}

		request.getRequestDispatcher(destino).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String menssagem = "";
			
			String destino = "index.jsp";
			if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario = daoRepositoryLoginUsuario.validarLogin(usuario);

				if (!usuario.isNull()) {
					destino = "principal/home.jsp";
					request.getSession().setAttribute("dados", usuario);;
				
				} else {
					menssagem = "Usuario Invalido";
				}

			} else {
				menssagem = "Campo Senha E-mail Sao Obrigatorios";
			}
			request.setAttribute("menssagem", menssagem);
			request.getRequestDispatcher(destino).forward(request, response);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("menssagem", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}
}
