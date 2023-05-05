package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Livro;
import repository.entity.RepositoryLivroDao;
import utilitario.Generic;

/**
 * Servlet implementation class ControleLivroPesquisa
 */
@WebServlet(urlPatterns = { "/livro" })
public class ControleLivroPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RepositoryLivroDao dao = new RepositoryLivroDao();

	public ControleLivroPesquisa() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String nomeLivroPesquisado = req.getParameter("livroPesquisado");
			String url = req.getParameter("url");
			List<Livro> livros = null;
			if (url.equals("pesquisarlivropornome")) {
				if (nomeLivroPesquisado != null && !nomeLivroPesquisado.isEmpty()) {
					// trago os dados filtrados

					livros = dao.pesquisar(nomeLivroPesquisado, new Generic().idUsuarioResponssavel(req));
					
				} else {
					livros = dao.listaTodosLivros(new Generic().idUsuarioResponssavel(req));

				}
			} else if (url.equals("paginacao")) {
				String numeroPaginas = req.getParameter("numeroPagina");
				livros = dao.listaTodosLivrosPorPagina(new Generic().idUsuarioResponssavel(req),
						Integer.parseInt(numeroPaginas));
			}

			Integer totalPaginas = new RepositoryLivroDao()
					.totalLinhasRegistros(new Generic().idUsuarioResponssavel(req));

			req.setAttribute("total", totalPaginas);
			req.setAttribute("livros", livros);
			req.getRequestDispatcher("principal/listalivros.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
