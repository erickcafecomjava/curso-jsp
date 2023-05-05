package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Livro;
import repository.entity.RepositoryLivroDao;
import utilitario.Generic;

/**
 * Servlet implementation class ControlePagginas
 */
@WebServlet(urlPatterns = { "/novousuario", "/voltar", "/novolivro", "/pesquisarlivro", "/biblioteca", "/relatorio" })
public class ControlePaginas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlePaginas() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = request.getServletPath();
		String destino = "principal/home.jsp";
		try {
			if (acao.equals("/novousuario")) {
				destino = "principal/usuario.jsp";

			} else if (acao.equals("/novolivro")) {
				destino = "principal/livro.jsp";

			} else if (acao.equals("/biblioteca")) {

				destino = "principal/minhabiblioteca.jsp";

			} else if (acao.equals("/pesquisarlivro")) {
				Integer totalPaginas = new RepositoryLivroDao()
						.totalLinhasRegistros(new Generic().idUsuarioResponssavel(request));
				request.setAttribute("total", totalPaginas);
				destino = "principal/listalivros.jsp";

			} else if (acao.equals("/relatorio")) {
				destino = "principal/relatoriousuarios.jsp";
			}
			request.getRequestDispatcher(destino).forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
